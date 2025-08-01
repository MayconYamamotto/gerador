import { ParserRuleContext } from 'antlr4ts';
import { ProjetoDSLParser } from '../grammar/ProjetoDSLParser';
import { ProjetoDSLLexer } from '../grammar/ProjetoDSLLexer';

export class DSLParser {
    private lexer: ProjetoDSLLexer;
    private parser: ProjetoDSLParser;

    constructor(input: string) {
        this.lexer = new ProjetoDSLLexer(input);
        this.parser = new ProjetoDSLParser(this.lexer);
    }

    public parse(): ParserRuleContext {
        return this.parser.file(); // Assuming 'file' is the starting rule in the grammar
    }

    public getTypes(): string[] {
        return [
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
    }

    public getValidations(): string[] {
        return [
            'min',
            'max',
            'notNull',
            'notBlank'
        ];
    }
}