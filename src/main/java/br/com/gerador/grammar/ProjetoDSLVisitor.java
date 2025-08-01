// Generated from src/main/java/br/com/gerador/grammar/ProjetoDSL.g4 by ANTLR 4.13.2

package br.com.gerador.grammar;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProjetoDSLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ProjetoDSLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ProjetoDSLParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(ProjetoDSLParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProjetoDSLParser#packageDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDecl(ProjetoDSLParser.PackageDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProjetoDSLParser#packageName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageName(ProjetoDSLParser.PackageNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProjetoDSLParser#entityDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityDecl(ProjetoDSLParser.EntityDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProjetoDSLParser#fieldDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDecl(ProjetoDSLParser.FieldDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProjetoDSLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ProjetoDSLParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProjetoDSLParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(ProjetoDSLParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProjetoDSLParser#validation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValidation(ProjetoDSLParser.ValidationContext ctx);
}