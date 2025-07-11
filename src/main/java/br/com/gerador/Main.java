package br.com.gerador;

import br.com.gerador.grammar.MinhaLinguagemLexer;
import br.com.gerador.grammar.MinhaLinguagemParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = "hello mundo";
        CharStream charStream = CharStreams.fromString(input);

        MinhaLinguagemLexer lexer = new MinhaLinguagemLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MinhaLinguagemParser parser = new MinhaLinguagemParser(tokens);

        parser.program(); // Chama a regra inicial
        System.out.println("Parse concluído!");
    }
}