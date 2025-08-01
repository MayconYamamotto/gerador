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
        String fileName = args.length > 0 ? args[0] : "src/main/java/br/com/gerador/dsl/Teste.dsl";

        CharStream input = CharStreams.fromFileName(fileName);
        ProjetoDSLLexer lexer = new ProjetoDSLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ProjetoDSLParser parser = new ProjetoDSLParser(tokens);

        ParseTree tree = parser.file();

        GeradorJavaVisitor visitor = new GeradorJavaVisitor();
        visitor.visit(tree);
    }
}
