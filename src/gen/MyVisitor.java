import org.antlr.v4.runtime.misc.Pair;

import java.sql.Array;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.Scanner;
public class MyVisitor<T> extends LPPBaseVisitor<T>{

    // Helper functions

    boolean getBooleanValue(String booleanString){
        return booleanString.equalsIgnoreCase("verdadero");
    }

    String getBooleanString(boolean booleanString){
        if (booleanString){
            return "VERDADERO";
        } else {
            return "FALSO";
        }
    }

    // We probably should store the type too, but idk right now how to do it.
    HashMap<String, Object> varTable = new HashMap<>();

    HashMap<String, ArrayList<Pair<String, String>>> registerTable = new HashMap<>();

    // Register related visitors.

    /*
    @Override
    public T visitRegisterDeclarations(LPPParser.RegisterDeclarationsContext ctx){
        if (ctx.registerDeclaration() !=null){
            return visitRegisterDeclaration(ctx.registerDeclaration(0));
        } else {
            return null;
        }

    }

    @Override
    public T visitRegisterDeclaration(LPPParser.RegisterDeclarationContext ctx){

        String registerName = ctx.ID().getText();
        ArrayList<Pair<String, String>> attributes = new ArrayList<>();

        for (int i = 0; i<ctx.varDeclarations().children.size(); i++){

            String varType = ctx.varDeclarations().varDeclaration(i).varType().getText();
            ArrayList<String> variables = (ArrayList<String>) visitIdList(ctx.varDeclarations().varDeclaration(i).idList());
            for (String variable : variables){
                attributes.add(new Pair<>(varType, variable));
            }
        }

        registerTable.put(registerName, attributes);
        System.out.println(registerTable.get(registerName).get(0));

        return null;
    }

    */
    // Stmt related visitors

    @Override
    public T visitStmt(LPPParser.StmtContext ctx){
        if (ctx.printStmt() != null){
            return visitPrintStmt(ctx.printStmt());
        }

        else if (ctx.assignStmt() != null){
            return visitAssignStmt(ctx.assignStmt());

        }
        else if (ctx.ifStmt() != null){
            return visitIfStmt(ctx.ifStmt());
        }
        else if (ctx.repeatStmt() != null){
            return visitRepeatStmt(ctx.repeatStmt());
        }
        else if (ctx.forStmt()!= null){
            return visitForStmt(ctx.forStmt());
        }
        else if (ctx.whileStmt()!= null){
            return visitWhileStmt(ctx.whileStmt());
        }
        return null;
    }

    @Override
    public T visitIfStmt(LPPParser.IfStmtContext ctx){
        String booleanString = (String) visitExpr(ctx.expr());
        Boolean booleanValue = false;
        if (booleanString.equalsIgnoreCase("verdadero")) {
            booleanValue = true;
        }

        if (booleanValue){
            visitStmts(ctx.stmts());
        }

        return null;
    }
    @Override
    public T visitWhileStmt(LPPParser.WhileStmtContext ctx){


        while (((String) visitExpr(ctx.expr())).equalsIgnoreCase("verdadero")){
            visitStmts(ctx.stmts());
        }
        return null;

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

    @Override
    public T visitReadStmt(LPPParser.ReadStmtContext ctx){

        // The expression list must be a list of variables
        // The read Stmt assign a value to some variable, array, etc.
        // So we probably just need to read the value, and visit the assign with some contedxt idk


        // For that we get the list of variables to read first

        String exprList = ctx.exprList().expr(0).getText();
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();

        scanner.close();

        return null;
    }

    @Override

    public T visitRepeatStmt(LPPParser.RepeatStmtContext ctx){


        while (((String) visitExpr(ctx.expr())).equalsIgnoreCase("falso")){
            visitStmts(ctx.stmts());
        }

        return null;

    }

    public T visitForStmt(LPPParser.ForStmtContext ctx){
        String identifier = (String) ctx.ID().getText();
        Integer initial_value = (Integer) visitExpr(ctx.expr(0));
        Integer limit = (Integer) visitExpr(ctx.expr(1));

        if (varTable.get(identifier)==null){
            System.out.println("Error, debe ser declarada");
            return null;
        }
        varTable.put(identifier, initial_value);

        for (int i = initial_value; i<limit; i++){
            initial_value++;
            visitStmts(ctx.stmts());
            varTable.put(identifier, initial_value);
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
                }
                //If its a register then
                else {
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
        } else if(ctx.VERDADERO()!= null){
            //El interprete de LPP imprime verdadero, por lo que devuelvo el string por ahora
            return (T) "verdadero";
        } else if(ctx.FALSO() != null){
            return (T) "falso";
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

        else if(ctx.BOLOP()!=null){

            String op = (String) ctx.BOLOP().getText();
            String value1 = (String) visitExpr(ctx.expr(0));
            String value2 = (String) visitExpr(ctx.expr(1));
            boolean result;

            boolean bValue1 = getBooleanValue(value1);
            boolean bValue2 = getBooleanValue(value2);

            if (op.equalsIgnoreCase("o")){
                result = (bValue1 || bValue2);
            } else {
                result = (bValue1 && bValue2);
            }

            return (T) getBooleanString(result);
        }
        return null;
    }

}
