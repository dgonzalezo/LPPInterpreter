import java.sql.Array;
import java.util.HashMap;
import java.util.ArrayList;

public class MyVisitor<T> extends LPPBaseVisitor<T>{


    // We probably should store the type too, but idk right now how to do it.

    HashMap<String, Object> varTable = new HashMap<>();


    @Override
    public T visitStmt(LPPParser.StmtContext ctx){
        if (ctx.printStmt() != null){
            return visitPrintStmt(ctx.printStmt());
        }

        else if (ctx.assignStmt() != null){
            return visitAssignStmt(ctx.assignStmt());

        }
        return null;
    }


    @Override
    public T visitVarDeclarations(LPPParser.VarDeclarationsContext ctx){

        for (int i=0; i<ctx.varDeclaration().size(); i++) {
            String varType = ctx.varDeclaration(i).varType().getText().toLowerCase();
            ArrayList<String> idList = (ArrayList<String>) visitIdList(ctx.varDeclaration(i).idList());

            // Ooriginalmente pensaba hacer un switch, pero es un lio el tema de cadenas y arreglos
            for (String variable : idList) {
                if (varType.equals("entero")) {
                    varTable.put(variable, 0);
                } else if (varType.startsWith("real")){
                    varTable.put(variable, 0.0);
                } else if (varType.startsWith("cadena")) {
                    //Quizas toque hacer que la cadena no sea mayor a un tamano...
                    varTable.put(variable, "");
                } else {
                    varTable.put(variable, new Object());
                }
            }

        }
        return null;
    }

    @Override
    public T visitIdList(LPPParser.IdListContext ctx){

        ArrayList<String> idList = new ArrayList<String>();
        if (ctx.ID()!=null){
            for (int i=0; i<ctx.ID().size();i++){
                idList.add(ctx.ID(i).getText());
            }
        }
        return (T) idList;
    }

    @Override
    public T visitAssignStmt(LPPParser.AssignStmtContext ctx){
        String variableName = ctx.expr(0).ID().getText();
        Object value = visitExpr(ctx.expr(1));

        if (varTable.get(variableName) == null){
            System.out.println(variableName+" ERROR: no ha si declarada!!!!111");
        } else {
            varTable.put(variableName, value);
            //System.out.printf("Se asigno %s a variable %s\n", varTable.get(variableName), variableName);
        }

        return null;

    }

    //A Temporal visitPrintStmt to print exprs, and declared variables.
    @Override
    public T visitPrintStmt(LPPParser.PrintStmtContext ctx){

        if (ctx.exprList()!= null){
            int times = ctx.exprList().expr().size();
            for (int i=0; i <times; i++) {
                Object ans = visitExpr(ctx.exprList().expr(i));
                System.out.print(ans);
            }
       }

        return null;
    }



    @Override
    public T visitLiteral(LPPParser.LiteralContext ctx){
        if (ctx.TKN_INTEGER()!=null){
            Integer value = Integer.parseInt(ctx.TKN_INTEGER().getText());
            return (T) value;
        } else if(ctx.TKN_STR()!= null){
            String value = ctx.TKN_STR().getText().replaceAll("\"", "");
            return (T) value;
        } else if(ctx.TKN_REAL()!= null){
            Double value = Double.parseDouble(ctx.TKN_REAL().getText());
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

            //La mas deificil, aqui toca quizas ahcer la logica sobre como lalmar las funciones, los arreglos y las ids no mas.
            //Por ahora solo llamo el valor de una ID

            String id = ctx.ID().getText();
            Object value = varTable.get(id);
            return (T) value;

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
            Double num1 = Double.parseDouble(visitExpr(ctx.expr(0)).toString());
            Double num2 = Double.parseDouble(visitExpr(ctx.expr(1)).toString());
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
