public class MyVisitor<T> extends LPPBaseVisitor<T>{

    @Override
    public T visitStmt(LPPParser.StmtContext ctx){
        if (ctx.printStmt() != null){
            return visitPrintStmt(ctx.printStmt());
        }
        return null;
    }

    @Override
    public T visitPrintStmt(LPPParser.PrintStmtContext ctx){

        if (ctx.exprList()!= null){
            int times = ctx.exprList().expr().size();
            //System.out.println(times);
            for (int i=0; i <times; i++) {
                Object ans = visitExpr(ctx.exprList().expr(i));
                System.out.println(ans);
            }
       }
        return null;
    }

    @Override
    public T visitExprList(LPPParser.ExprListContext ctx) {
        return null;

    }

    @Override
    public T visitLiteral(LPPParser.LiteralContext ctx){
        if (ctx.TKN_INTEGER()!=null){
            Double value = Double.parseDouble(ctx.TKN_INTEGER().getText());
            return (T) value;
        }
        else if(ctx.TKN_STR()!= null){
            String value = ctx.TKN_STR().getText();
            return (T) value;
        }
        return null;
    }
    @Override
    public T visitExpr(LPPParser.ExprContext ctx) {

        if (ctx.literal() != null) {
            return visitLiteral(ctx.literal());
        }

        else if (ctx.TKN_OPENING_PAR()!= null){
            return visitExpr(ctx.expr(0));
        }
        else if (ctx.ID() != null){
            //Here we will need to check if it's an ID, a function CAll, or
            //And id with some attribute.

            //Just an ID.
            String name = ctx.ID().getText();
            return (T) name;

        }
        else if (ctx.MULOP()!=null){
            String op = ctx.MULOP().getText().toLowerCase();
            Double num1 = (Double) visitExpr(ctx.expr(0));
            Double num2 = (Double) visitExpr(ctx.expr(1));
            Double ans = null;
            switch (op){
                case "*":
                    ans = num1 * num2;
                    break;
                case "/":
                    ans = num1 / num2;
                    break;
                case "mod":
                    ans = num1%num2;
                    break;
                case "div":
                    ans = num1 / num2;
                    break;
            }
            return (T) ans;
        }
        else if (ctx.ADOP()!=null){
            String op = ctx.ADOP().getText();
            Double num1 = (Double) visitExpr(ctx.expr(0));
            Double num2 = (Double) visitExpr(ctx.expr(1));
            Double ans = null;
            switch (op){
                case "+":
                    ans = num1 + num2;
                    break;
                case "-":
                    ans = num1 - num2;
                    break;
            }
            return (T) ans;
        }
        else if(ctx.COMOP()!=null){
            String op = ctx.COMOP().getText();
            Double num1 = (Double) visitExpr(ctx.expr(0));
            Double num2 = (Double) visitExpr(ctx.expr(1));
            switch (op){
                case "=":
                    return (T) (num1 == num2 ? "VERDADERO" : "FALSO");
                case "<":
                    return (T) (num1 < num2 ? "VERDADERO" : "FALSO");
                case ">":
                    return (T) (num1 > num2 ? "VERDADERO" : "FALSO");
                case ">=":
                    return (T) (num1 >= num2 ? "VERDADERO" : "FALSO");
                case "<=":
                    return (T) (num1 <= num2 ? "VERDADERO": "FALSO");
                case "<>":
                    return (T) (num1 != num2 ? "VERDADERO" : "FALSO");
            }
        }
        return null;
    }

}
