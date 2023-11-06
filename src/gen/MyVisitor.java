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

    // We encapsulate the dictionary NameOfVariable: (Type, value)
    HashMap<String, Object> varTable = new HashMap<>();
    HashMap<String, ArrayList<Pair<String, String>>> registerTable = new HashMap<>();


    class FunctionInfo {
        String returnType;
        ArrayList<Pair<String, String>> parameters;
        LPPParser.FuncStmtsContext stmts;

    }

    HashMap<String, FunctionInfo> functionTable = new HashMap<>();



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

    // Functions related visitors

    @Override
    public T visitFuncDeclaration(LPPParser.FuncDeclarationContext ctx) {

        return null;
    }

    public T visitParameters(LPPParser.ParametersContext ctx){
        ArrayList<Pair<String, String>> parameterList = new ArrayList<>();
        if (ctx.parameter()!=null){
            for(int i =0; i<ctx.parameter().size(); i++){
                String paramType = ctx.parameter(i).varType().getText();
                String paramName = ctx.parameter(i).ID().getText();

                Pair<String, String> parameter = new Pair<>(paramType, paramName);
                parameterList.add(parameter);
            }
        }
        return (T) parameterList;
    }

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
        else if (ctx.callStmt() != null){
            return visitCallStmt(ctx.callStmt());
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
    public T visitCallStmt(LPPParser.CallStmtContext ctx){
        if (ctx.NUEVA_LINEA()!=null){
            System.out.println("");
        }
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

        if (ctx.funCall()!=null){
            return visitFunCall(ctx.funCall());
        }
        else if (ctx.literal() != null) {
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
        else if (ctx.MULOP() != null || ctx.ADOP() != null) {
            String op = (ctx.MULOP() != null) ? ctx.MULOP().getText().toLowerCase() : ctx.ADOP().getText();
            Number num1 = (Number) visitExpr(ctx.expr(0));
            Number num2 = (Number) visitExpr(ctx.expr(1));
            Number ans = null;

            switch (op) {
                case "*":
                    ans = num1.doubleValue() * num2.doubleValue();
                    break;
                case "/":
                    ans = num1.doubleValue() / num2.doubleValue();
                    break;
                case "mod":
                    ans = num1.intValue() % num2.intValue();
                    break;
                case "div":
                    ans = num1.intValue() / num2.intValue();
                    break;
                case "+":
                    if (num1 instanceof Double || num2 instanceof Double) {
                        ans = num1.doubleValue() + num2.doubleValue();
                    } else {
                        ans = num1.intValue() + num2.intValue();
                    }
                    break;
                case "-":
                    if (num1 instanceof Double && num2 instanceof Double) {
                        ans = num1.doubleValue() - num2.doubleValue();
                    } else {
                        ans = num1.intValue() - num2.intValue();
                    }
                    break;
            }

            if (num1 instanceof Integer || num2 instanceof Integer) {
                ans = ans.intValue();
            }

            return (T) ans;
        }
        else if (ctx.COMOP() != null) {
            String op = ctx.COMOP().getText();
            Number num1 = (Number) visitExpr(ctx.expr(0));
            Number num2 = (Number) visitExpr(ctx.expr(1));
            String ans = null;

            switch (op) {
                case "=":
                    ans = (num1.doubleValue() == num2.doubleValue() ? "VERDADERO" : "FALSO");
                    break;
                case "<":
                    ans = (num1.doubleValue() < num2.doubleValue() ? "VERDADERO" : "FALSO");
                    break;
                case ">":
                    ans = (num1.doubleValue() > num2.doubleValue() ? "VERDADERO" : "FALSO");
                    break;
                case ">=":
                    ans = (num1.doubleValue() >= num2.doubleValue() ? "VERDADERO" : "FALSO");
                    break;
                case "<=":
                    ans = (num1.doubleValue() <= num2.doubleValue() ? "VERDADERO" : "FALSO");
                    break;
                case "<>":
                    ans = (num1.doubleValue() != num2.doubleValue() ? "VERDADERO" : "FALSO");
                    break;
            }

            return (T) ans;
        }

        else if (ctx.BOLOP() != null) {
            String op = ctx.BOLOP().getText();
            String value1 = (String) visitExpr(ctx.expr(0));
            String value2 = (String) visitExpr(ctx.expr(1));
            boolean result;

            boolean bValue1 = getBooleanValue(value1);
            boolean bValue2 = getBooleanValue(value2);

            if (op.equalsIgnoreCase("o")) {
                result = (bValue1 || bValue2);
            } else {
                result = (bValue1 && bValue2);
            }

            return (T) getBooleanString(result);
        }

        return null;
    }
    @Override
    public T visitFunCall(LPPParser.FunCallContext ctx) {
        String functionName = ctx.ID().getText();

        if (functionTable.containsKey(functionName)) {
            FunctionInfo functionInfo = functionTable.get(functionName);

            // Create a new local scope for function parameters
            HashMap<String, Object> localScope = new HashMap<>();
            ArrayList<Pair<String, String>> parameters = functionInfo.parameters;

            if (ctx.exprList() != null) {
                for (int i = 0; i < parameters.size() && i < ctx.exprList().expr().size(); i++) {
                    String paramName = parameters.get(i).b;
                    Object argValue = visitExpr(ctx.exprList().expr(i));
                    localScope.put(paramName, argValue);
                }
            }

            // Execute the function's statement block in the new scope
            MyVisitor<T> functionVisitor = new MyVisitor<>();
            functionVisitor.varTable = localScope; // Set local scope
            functionVisitor.visitFuncStmts(functionInfo.stmts);
        } else {
            System.out.println("Error: Function " + functionName + " not defined.");
        }

        return null;
    }



}
