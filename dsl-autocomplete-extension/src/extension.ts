import * as vscode from 'vscode';
import { CompletionProvider } from './language/completionProvider';

export function activate(context: vscode.ExtensionContext) {
    console.log('Projeto DSL extension is now active!');

    // Register completion provider
    const completionProvider = new CompletionProvider();
    
    context.subscriptions.push(
        vscode.languages.registerCompletionItemProvider(
            { language: 'dsl', scheme: 'file' },
            completionProvider,
            ':',  // Trigger completion on ':'
            ' ',  // Trigger completion on space
            '?'   // Trigger completion on '?'
        )
    );

    // Register hover provider for documentation
    const hoverProvider = vscode.languages.registerHoverProvider(
        { language: 'dsl', scheme: 'file' },
        {
            provideHover(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken) {
                const range = document.getWordRangeAtPosition(position);
                const word = document.getText(range);
                
                const documentation = getDocumentation(word);
                if (documentation) {
                    return new vscode.Hover(documentation);
                }
            }
        }
    );
    
    context.subscriptions.push(hoverProvider);

    // Register document formatting provider
    const formattingProvider = vscode.languages.registerDocumentFormattingEditProvider(
        { language: 'dsl', scheme: 'file' },
        {
            provideDocumentFormattingEdits(document: vscode.TextDocument) {
                const edits: vscode.TextEdit[] = [];
                
                for (let i = 0; i < document.lineCount; i++) {
                    const line = document.lineAt(i);
                    const formattedText = formatLine(line.text);
                    
                    if (formattedText !== line.text) {
                        edits.push(vscode.TextEdit.replace(line.range, formattedText));
                    }
                }
                
                return edits;
            }
        }
    );
    
    context.subscriptions.push(formattingProvider);
}

function getDocumentation(word: string): string | undefined {
    const docs: { [key: string]: string } = {
        'service': 'A service groups related entities together',
        'entity': 'An entity represents a data structure with fields',
        'string': 'Text data type',
        'uuid': 'Universally unique identifier',
        'integer': 'Whole number data type',
        'long': 'Large whole number data type',
        'double': 'Floating point number data type',
        'boolean': 'True/false data type',
        'date': 'Date data type (without time)',
        'datetime': 'Date and time data type',
        'decimal': 'Precise decimal number data type',
        'min': 'Minimum value validation with custom error message',
        'max': 'Maximum value validation',
        'notNull': 'Field cannot be null',
        'notBlank': 'String field cannot be blank or empty'
    };
    
    return docs[word];
}

function formatLine(text: string): string {
    // Basic formatting rules
    let formatted = text;
    
    // Add spaces around colons
    formatted = formatted.replace(/(\w+):(\w+)/g, '$1: $2');
    
    // Ensure proper spacing after commas
    formatted = formatted.replace(/,(\w)/g, ', $1');
    
    // Remove extra spaces
    formatted = formatted.replace(/\s+/g, ' ');
    
    return formatted;
}

export function deactivate() {
    console.log('Projeto DSL extension deactivated');
}