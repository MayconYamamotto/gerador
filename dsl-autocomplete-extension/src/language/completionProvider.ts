import * as vscode from 'vscode';

export class CompletionProvider implements vscode.CompletionItemProvider {
    private types: string[] = [
        'string',
        'uuid',
        'integer',
        'long',
        'double',
        'boolean',
        'date',
        'datetime',
        'decimal'
    ];

    private validations: { [key: string]: string } = {
        'min': 'min(${1:value}, "${2:message}")',
        'max': 'max(${1:value})',
        'notNull': 'notNull',
        'notBlank': 'notBlank'
    };

    private keywords: string[] = [
        'service',
        'entity'
    ];

    provideCompletionItems(
        document: vscode.TextDocument,
        position: vscode.Position,
        token: vscode.CancellationToken,
        context: vscode.CompletionContext
    ): vscode.ProviderResult<vscode.CompletionItem[]> {
        const linePrefix = document.lineAt(position).text.substr(0, position.character);
        const line = document.lineAt(position).text;
        const completionItems: vscode.CompletionItem[] = [];

        // Context-aware completions
        if (this.isInServiceContext(document, position)) {
            // Inside a service, suggest entity keyword
            if (this.shouldSuggestEntity(linePrefix)) {
                const entityItem = new vscode.CompletionItem('entity', vscode.CompletionItemKind.Keyword);
                entityItem.insertText = new vscode.SnippetString('entity ${1:EntityName} {\n\tid: uuid\n\t$0\n}');
                entityItem.detail = 'Create a new entity';
                entityItem.documentation = 'Creates a new entity declaration with an id field';
                completionItems.push(entityItem);
            }
        } else {
            // At root level, suggest service keyword
            if (this.shouldSuggestService(linePrefix)) {
                const serviceItem = new vscode.CompletionItem('service', vscode.CompletionItemKind.Keyword);
                serviceItem.insertText = new vscode.SnippetString('service ${1:serviceName} {\n\t$0\n}');
                serviceItem.detail = 'Create a new service';
                serviceItem.documentation = 'Creates a new service declaration';
                completionItems.push(serviceItem);
            }
        }

        // Type completions after ':'
        if (this.shouldSuggestTypes(linePrefix)) {
            this.types.forEach(type => {
                const typeItem = new vscode.CompletionItem(type, vscode.CompletionItemKind.TypeParameter);
                typeItem.detail = `DSL type: ${type}`;
                typeItem.insertText = type;
                completionItems.push(typeItem);
            });
        }

        // Validation completions after type or '?'
        if (this.shouldSuggestValidations(linePrefix)) {
            Object.keys(this.validations).forEach(validation => {
                const validationItem = new vscode.CompletionItem(validation, vscode.CompletionItemKind.Function);
                validationItem.insertText = new vscode.SnippetString(this.validations[validation]);
                validationItem.detail = `Validation: ${validation}`;
                validationItem.documentation = this.getValidationDocumentation(validation);
                completionItems.push(validationItem);
            });
        }

        // Optional marker '?' after types
        if (this.shouldSuggestOptional(linePrefix)) {
            const optionalItem = new vscode.CompletionItem('?', vscode.CompletionItemKind.Operator);
            optionalItem.detail = 'Optional field';
            optionalItem.documentation = 'Makes the field optional';
            completionItems.push(optionalItem);
        }

        // Common field names based on type
        if (this.shouldSuggestFieldNames(linePrefix, line)) {
            const fieldSuggestions = this.getFieldNameSuggestions();
            fieldSuggestions.forEach(field => {
                const fieldItem = new vscode.CompletionItem(field.name, vscode.CompletionItemKind.Field);
                fieldItem.insertText = new vscode.SnippetString(field.snippet);
                fieldItem.detail = field.detail;
                completionItems.push(fieldItem);
            });
        }

        return completionItems;
    }

    private isInServiceContext(document: vscode.TextDocument, position: vscode.Position): boolean {
        let serviceDepth = 0;
        for (let i = 0; i <= position.line; i++) {
            const line = document.lineAt(i).text.trim();
            if (line.includes('service') && line.includes('{')) {
                serviceDepth++;
            }
            if (line.includes('}') && serviceDepth > 0) {
                serviceDepth--;
            }
        }
        return serviceDepth > 0;
    }

    private shouldSuggestEntity(linePrefix: string): boolean {
        return /^\s*$/.test(linePrefix) || /^\s*e/.test(linePrefix);
    }

    private shouldSuggestService(linePrefix: string): boolean {
        return /^\s*$/.test(linePrefix) || /^\s*s/.test(linePrefix);
    }

    private shouldSuggestTypes(linePrefix: string): boolean {
        return /:\s*$/.test(linePrefix) || /:\s+\w*$/.test(linePrefix);
    }

    private shouldSuggestValidations(linePrefix: string): boolean {
        // After a type or after '?'
        const typePattern = new RegExp(`\\b(${this.types.join('|')})\\??\\s*$`);
        return typePattern.test(linePrefix) || /\?\s*$/.test(linePrefix);
    }

    private shouldSuggestOptional(linePrefix: string): boolean {
        const typePattern = new RegExp(`\\b(${this.types.join('|')})\\s*$`);
        return typePattern.test(linePrefix);
    }

    private shouldSuggestFieldNames(linePrefix: string, line: string): boolean {
        // At the beginning of a line inside an entity
        return /^\s*$/.test(linePrefix) && this.isInEntityContext(line);
    }

    private isInEntityContext(line: string): boolean {
        // Simple check - this could be improved with better context analysis
        return !line.includes('service') && !line.includes('entity');
    }

    private getFieldNameSuggestions(): Array<{name: string, snippet: string, detail: string}> {
        return [
            {
                name: 'id',
                snippet: 'id: uuid',
                detail: 'UUID identifier field'
            },
            {
                name: 'name',
                snippet: '${1:name}: string${2: notBlank}',
                detail: 'Name field with validation'
            },
            {
                name: 'email',
                snippet: '${1:email}: string${2:?}${3: notBlank}',
                detail: 'Email field'
            },
            {
                name: 'created',
                snippet: '${1:createdAt}: datetime',
                detail: 'Creation timestamp'
            },
            {
                name: 'updated',
                snippet: '${1:updatedAt}: datetime',
                detail: 'Update timestamp'
            },
            {
                name: 'active',
                snippet: '${1:active}: boolean',
                detail: 'Active status field'
            }
        ];
    }

    private getValidationDocumentation(validation: string): string {
        switch (validation) {
            case 'min':
                return 'Minimum value validation with custom error message';
            case 'max':
                return 'Maximum value validation';
            case 'notNull':
                return 'Field cannot be null';
            case 'notBlank':
                return 'String field cannot be blank';
            default:
                return '';
        }
    }
}