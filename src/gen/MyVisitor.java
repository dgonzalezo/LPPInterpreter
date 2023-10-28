public class MyVisitor<T> extends LPPBaseVisitor<T>{

    @Override
    public T visitStmt(LPPParser.StmtContext ctx){
        if (ctx.printStmt() != null){
            Object ans = visitExpr(ctx.printStmt().exprList().expr(0));
            System.out.println(ans);
        }
        return null;
    }

    public T visitExpr(LPPParser.ExprContext ctx) {
        if (ctx.literal() != null) {
            Object literal = ctx.literal().getText();
            return (T) literal;
        }
        return null;
    }
    public T visitLiteral(LPPParser.LiteralContext ctx) {
        if (ctx.TKN_INTEGER() != null){
            return (T) ctx.getText();
        }
        else {
            return null;
        }
    }
}
