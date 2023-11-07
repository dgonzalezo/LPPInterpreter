// Generated from /Users/eliangonzalez/Documents/Semestres/2023-02/Lenguajes/LPPInterpreter/grammar/LPP.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LPPParser}.
 */
public interface LPPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LPPParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LPPParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LPPParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#registerDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterRegisterDeclarations(LPPParser.RegisterDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#registerDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitRegisterDeclarations(LPPParser.RegisterDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#registerDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRegisterDeclaration(LPPParser.RegisterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#registerDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRegisterDeclaration(LPPParser.RegisterDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#funProcDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterFunProcDeclarations(LPPParser.FunProcDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#funProcDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitFunProcDeclarations(LPPParser.FunProcDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#funcDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclaration(LPPParser.FuncDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#funcDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclaration(LPPParser.FuncDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#procDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcDeclaration(LPPParser.ProcDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#procDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcDeclaration(LPPParser.ProcDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(LPPParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(LPPParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(LPPParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(LPPParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#varDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclarations(LPPParser.VarDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#varDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclarations(LPPParser.VarDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(LPPParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(LPPParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(LPPParser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(LPPParser.IdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(LPPParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(LPPParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVarType(LPPParser.VarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVarType(LPPParser.VarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#integerList}.
	 * @param ctx the parse tree
	 */
	void enterIntegerList(LPPParser.IntegerListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#integerList}.
	 * @param ctx the parse tree
	 */
	void exitIntegerList(LPPParser.IntegerListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#funcStmts}.
	 * @param ctx the parse tree
	 */
	void enterFuncStmts(LPPParser.FuncStmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#funcStmts}.
	 * @param ctx the parse tree
	 */
	void exitFuncStmts(LPPParser.FuncStmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#principalBlock}.
	 * @param ctx the parse tree
	 */
	void enterPrincipalBlock(LPPParser.PrincipalBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#principalBlock}.
	 * @param ctx the parse tree
	 */
	void exitPrincipalBlock(LPPParser.PrincipalBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#stmts}.
	 * @param ctx the parse tree
	 */
	void enterStmts(LPPParser.StmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#stmts}.
	 * @param ctx the parse tree
	 */
	void exitStmts(LPPParser.StmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(LPPParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(LPPParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(LPPParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(LPPParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#readStmt}.
	 * @param ctx the parse tree
	 */
	void enterReadStmt(LPPParser.ReadStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#readStmt}.
	 * @param ctx the parse tree
	 */
	void exitReadStmt(LPPParser.ReadStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(LPPParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(LPPParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#callStmt}.
	 * @param ctx the parse tree
	 */
	void enterCallStmt(LPPParser.CallStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#callStmt}.
	 * @param ctx the parse tree
	 */
	void exitCallStmt(LPPParser.CallStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(LPPParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(LPPParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#ifNot}.
	 * @param ctx the parse tree
	 */
	void enterIfNot(LPPParser.IfNotContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#ifNot}.
	 * @param ctx the parse tree
	 */
	void exitIfNot(LPPParser.IfNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void enterCaseStmt(LPPParser.CaseStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void exitCaseStmt(LPPParser.CaseStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#caseElement}.
	 * @param ctx the parse tree
	 */
	void enterCaseElement(LPPParser.CaseElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#caseElement}.
	 * @param ctx the parse tree
	 */
	void exitCaseElement(LPPParser.CaseElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#listaExprOpcion}.
	 * @param ctx the parse tree
	 */
	void enterListaExprOpcion(LPPParser.ListaExprOpcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#listaExprOpcion}.
	 * @param ctx the parse tree
	 */
	void exitListaExprOpcion(LPPParser.ListaExprOpcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#exprOpcion}.
	 * @param ctx the parse tree
	 */
	void enterExprOpcion(LPPParser.ExprOpcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#exprOpcion}.
	 * @param ctx the parse tree
	 */
	void exitExprOpcion(LPPParser.ExprOpcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#rangeExpr}.
	 * @param ctx the parse tree
	 */
	void enterRangeExpr(LPPParser.RangeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#rangeExpr}.
	 * @param ctx the parse tree
	 */
	void exitRangeExpr(LPPParser.RangeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#ifNotCase}.
	 * @param ctx the parse tree
	 */
	void enterIfNotCase(LPPParser.IfNotCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#ifNotCase}.
	 * @param ctx the parse tree
	 */
	void exitIfNotCase(LPPParser.IfNotCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(LPPParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(LPPParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(LPPParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(LPPParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#repeatStmt}.
	 * @param ctx the parse tree
	 */
	void enterRepeatStmt(LPPParser.RepeatStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#repeatStmt}.
	 * @param ctx the parse tree
	 */
	void exitRepeatStmt(LPPParser.RepeatStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(LPPParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(LPPParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(LPPParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(LPPParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#funCall}.
	 * @param ctx the parse tree
	 */
	void enterFunCall(LPPParser.FunCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#funCall}.
	 * @param ctx the parse tree
	 */
	void exitFunCall(LPPParser.FunCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPParser#arrayCall}.
	 * @param ctx the parse tree
	 */
	void enterArrayCall(LPPParser.ArrayCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#arrayCall}.
	 * @param ctx the parse tree
	 */
	void exitArrayCall(LPPParser.ArrayCallContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link LPPParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(LPPParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(LPPParser.LiteralContext ctx);
}