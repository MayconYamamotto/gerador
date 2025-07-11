package br.com.gerador;

import br.com.gerador.grammar.ProjetoDSLLexer;
import br.com.gerador.grammar.ProjetoDSLParser;
import br.com.gerador.visitor.GeradorJavaVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("src/main/java/br/com/gerador/dsl/Teste.dsl");
        ProjetoDSLLexer lexer = new ProjetoDSLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ProjetoDSLParser parser = new ProjetoDSLParser(tokens);

        ParseTree tree = parser.file(); // ou parser.serviceDecl()

        GeradorJavaVisitor visitor = new GeradorJavaVisitor();
        visitor.visit(tree);
    }
}
