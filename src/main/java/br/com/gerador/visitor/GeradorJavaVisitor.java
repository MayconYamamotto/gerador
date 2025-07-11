package br.com.gerador.visitor;

import br.com.gerador.grammar.ProjetoDSLBaseVisitor;
import br.com.gerador.grammar.ProjetoDSLParser;

public class GeradorJavaVisitor extends ProjetoDSLBaseVisitor<Void> {
    @Override
    public Void visitEntityDecl(ProjetoDSLParser.EntityDeclContext ctx) {
        String nome = ctx.ID().getText();
        System.out.println("Gerando entidade: " + nome);
        // Aqui você monta o .java usando StringBuilder, templates, etc
        return null;
    }
}
