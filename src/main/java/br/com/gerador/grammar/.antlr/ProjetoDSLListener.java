// Generated from c:/workspace/gerador/src/main/java/br/com/gerador/grammar/ProjetoDSL.g4 by ANTLR 4.13.1

package br.com.gerador.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ProjetoDSLParser}.
 */
public interface ProjetoDSLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ProjetoDSLParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(ProjetoDSLParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjetoDSLParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(ProjetoDSLParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjetoDSLParser#packageDecl}.
	 * @param ctx the parse tree
	 */
	void enterPackageDecl(ProjetoDSLParser.PackageDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjetoDSLParser#packageDecl}.
	 * @param ctx the parse tree
	 */
	void exitPackageDecl(ProjetoDSLParser.PackageDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjetoDSLParser#entityDecl}.
	 * @param ctx the parse tree
	 */
	void enterEntityDecl(ProjetoDSLParser.EntityDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjetoDSLParser#entityDecl}.
	 * @param ctx the parse tree
	 */
	void exitEntityDecl(ProjetoDSLParser.EntityDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjetoDSLParser#fieldDecl}.
	 * @param ctx the parse tree
	 */
	void enterFieldDecl(ProjetoDSLParser.FieldDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjetoDSLParser#fieldDecl}.
	 * @param ctx the parse tree
	 */
	void exitFieldDecl(ProjetoDSLParser.FieldDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjetoDSLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ProjetoDSLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjetoDSLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ProjetoDSLParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjetoDSLParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(ProjetoDSLParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjetoDSLParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(ProjetoDSLParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProjetoDSLParser#validation}.
	 * @param ctx the parse tree
	 */
	void enterValidation(ProjetoDSLParser.ValidationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProjetoDSLParser#validation}.
	 * @param ctx the parse tree
	 */
	void exitValidation(ProjetoDSLParser.ValidationContext ctx);
}