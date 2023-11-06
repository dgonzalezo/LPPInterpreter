// Generated from C:/Users/xabel/IdeaProjects/LPPInterpreter/grammar/LPP.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LPPParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LPPVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LPPParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LPPParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#registerDeclarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterDeclarations(LPPParser.RegisterDeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#registerDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterDeclaration(LPPParser.RegisterDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#funProcDeclarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunProcDeclarations(LPPParser.FunProcDeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#funcDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclaration(LPPParser.FuncDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#procDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcDeclaration(LPPParser.ProcDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(LPPParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(LPPParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#varDeclarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclarations(LPPParser.VarDeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(LPPParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#idList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdList(LPPParser.IdListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(LPPParser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#integerList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerList(LPPParser.IntegerListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#funcStmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncStmts(LPPParser.FuncStmtsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#principalBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrincipalBlock(LPPParser.PrincipalBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmts(LPPParser.StmtsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(LPPParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#printStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(LPPParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#readStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStmt(LPPParser.ReadStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#assignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(LPPParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#callStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStmt(LPPParser.CallStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(LPPParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#ifNot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfNot(LPPParser.IfNotContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#caseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStmt(LPPParser.CaseStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#caseElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseElement(LPPParser.CaseElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#listaExprOpcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaExprOpcion(LPPParser.ListaExprOpcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#exprOpcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOpcion(LPPParser.ExprOpcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#rangeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeExpr(LPPParser.RangeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#ifNotCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfNotCase(LPPParser.IfNotCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(LPPParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(LPPParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#repeatStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatStmt(LPPParser.RepeatStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(LPPParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(LPPParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(LPPParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#funCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCall(LPPParser.FunCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#arrayCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCall(LPPParser.ArrayCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPPParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(LPPParser.LiteralContext ctx);
}