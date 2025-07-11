// Generated from src/main/java/br/com/gerador/grammar/MinhaLinguagem.g4 by ANTLR 4.13.2

package br.com.gerador.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MinhaLinguagemParser}.
 */
public interface MinhaLinguagemListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MinhaLinguagemParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MinhaLinguagemParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MinhaLinguagemParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MinhaLinguagemParser.ProgramContext ctx);
}