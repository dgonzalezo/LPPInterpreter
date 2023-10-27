// Generated from /Users/eliangonzalez/Documents/Semestres/2023-02/Lenguajes/LPPInterpreter/grammar/LPP.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LPPParser}.
 */
public interface LPPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LPPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(LPPParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(LPPParser.ExprContext ctx);
}