import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;
import java.util.ArrayList;

import java.util.Objects;
import java.util.Scanner;

public class MyVisitor extends LPPBaseVisitor<Value> {

    // We encapsulate the dictionary NameOfVariable: (Type, value)
    HashMap<String, Value> varTable = new HashMap<>();
    HashMap<String, ArrayList<Pair<String, String>>> registerTable = new HashMap<>();


    class FunctionInfo {
        String returnType;
        ArrayList<Pair<String, String>> parameters;
        LPPParser.FuncStmtsContext stmts;

    }

    HashMap<String, LPPParser.FuncDeclarationContext> functionTable = new HashMap<String, LPPParser.FuncDeclarationContext>();


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
    public Value visitFuncDeclaration(LPPParser.FuncDeclarationContext ctx) {
        String funcName = ctx.ID().getText();
        functionTable.put(funcName, ctx);
        return Value.VOID;
    }

//    public Value visitParameters(LPPParser.ParametersContext ctx){
//        ArrayList<Pair<String, String>> parameterList = new ArrayList<>();
//        if (ctx.parameter()!=null){
//            for(int i =0; i<ctx.parameter().size(); i++){
//                String paramType = ctx.parameter(i).varType().getText();
//                String paramName = ctx.parameter(i).ID().getText();
//
//                Pair<String, String> parameter = new Pair<>(paramType, paramName);
//                parameterList.add(parameter);
//            }
//        }
//        return (Value) parameterList;
//    }

    // Stmt related visitors

    @Override
    public Value visitStmt(LPPParser.StmtContext ctx) {
        if (ctx.printStmt() != null) {
            return visitPrintStmt(ctx.printStmt());
        } else if (ctx.assignStmt() != null) {
            return visitAssignStmt(ctx.assignStmt());
        } else if (ctx.ifStmt() != null) {
            return visitIfStmt(ctx.ifStmt());
        } else if (ctx.repeatStmt() != null) {
            return visitRepeatStmt(ctx.repeatStmt());
        } else if (ctx.forStmt() != null) {
            return visitForStmt(ctx.forStmt());
        } else if (ctx.whileStmt() != null) {
            return visitWhileStmt(ctx.whileStmt());
        } else if (ctx.callStmt() != null) {
            return visitCallStmt(ctx.callStmt());
        }
        return Value.VOID;
    }

    @Override
    public Value visitIfStmt(LPPParser.IfStmtContext ctx) {
        Value booleanString = visitExpr(ctx.expr());
        Boolean booleanValue = booleanString.asBooleano();

        if (booleanValue) {
            visitStmts(ctx.stmts());
        }

        return Value.VOID;
    }

    @Override
    public Value visitWhileStmt(LPPParser.WhileStmtContext ctx) {

        while (visitExpr(ctx.expr()).asBooleano()) {
            visitStmts(ctx.stmts());
        }
        return Value.VOID;

    }


    @Override
    public Value visitAssignStmt(LPPParser.AssignStmtContext ctx) {
        String variableName = ctx.expr(0).ID().getText();
        Value value = visitExpr(ctx.expr(1));

        if (varTable.get(variableName) == null) {
            System.out.println(variableName + " ERROR: no ha si declarada!!!!111");
        } else {
            varTable.put(variableName, value);
            //System.out.printf("Se asigno %s a variable %s\n", varTable.get(variableName), variableName);
        }

        return Value.VOID;

    }

    @Override
    public Value visitReadStmt(LPPParser.ReadStmtContext ctx) {

        // The expression list must be a list of variables
        // The read Stmt assign a value to some variable, array, etc.
        // So we probably just need to read the value, and visit the assign with some contedxt idk


        // For that we get the list of variables to read first

        String exprList = ctx.exprList().expr(0).getText();
        Scanner scanner = new Scanner(System.in);
        Value value = new Value(scanner.nextLine());

        scanner.close();

        return null;
    }

    @Override
    public Value visitCallStmt(LPPParser.CallStmtContext ctx) {
        if (ctx.NUEVA_LINEA() != null) {
            System.out.println("");
        }
        return Value.VOID;
    }

    @Override
    public Value visitRepeatStmt(LPPParser.RepeatStmtContext ctx) {
        do {
            visitStmts(ctx.stmts());
        }
        while (!(visitExpr(ctx.expr())).asBooleano());

        return Value.VOID;

    }

    public Value visitForStmt(LPPParser.ForStmtContext ctx) {
        String identifier = (String) ctx.ID().getText();
        Value initial_value = visitExpr(ctx.expr(0));
        Value limit = visitExpr(ctx.expr(1));

        if (varTable.get(identifier) == null) {
            System.out.println("Error, debe ser declarada");
            return null;
        }
        varTable.put(identifier, initial_value);

        for (int i = initial_value.asEntero(); i < limit.asEntero(); i++) {
            initial_value = new Value(initial_value.asEntero() + 1);
            visitStmts(ctx.stmts());
            varTable.put(identifier, initial_value);
        }
        return Value.VOID;
    }

    //A Temporal visitPrintStmt to print exprs, and declared variables.
    @Override
    public Value visitPrintStmt(LPPParser.PrintStmtContext ctx) {

        if (ctx.exprList() != null) {
            int times = ctx.exprList().expr().size();
            for (int i = 0; i < times; i++) {
                Object ans = visitExpr(ctx.exprList().expr(i));
                System.out.print(ans);
            }
        }

        return Value.VOID;
    }


    @Override
    public Value visitVarDeclarations(LPPParser.VarDeclarationsContext ctx) {

        for (int i = 0; i < ctx.varDeclaration().size(); i++) {
            String varType = ctx.varDeclaration(i).varType().getText().toLowerCase();
            ArrayList<LPPParser.IdentifierContext> ids = (ArrayList<LPPParser.IdentifierContext>) ctx.varDeclaration(i).idList().identifier();
            // Ooriginalmente pensaba hacer un switch, pero es un lio el tema de cadenas y arreglos
            for (LPPParser.IdentifierContext variable : ids) {
                if (varType.equals("entero")) {
                    varTable.put(variable.getText(), new Value(0));
                } else if (varType.startsWith("real")) {
                    varTable.put(variable.getText(), new Value(0.0));
                } else if (varType.startsWith("cadena")) {
                    //Quizas toque hacer que la cadena no sea mayor a un tamano...
                    varTable.put(variable.getText(), new Value(""));
                }
                //If its a register then
                else {
                    varTable.put(variable.getText(), new Value(null));
                }
            }

        }
        return Value.VOID;
    }

    @Override
    public Value visitLiteral(LPPParser.LiteralContext ctx) {
        if (ctx.TKN_INTEGER() != null) {
            Value value = new Value(Integer.parseInt(ctx.TKN_INTEGER().getText()));
            return value;
        } else if (ctx.TKN_STR() != null) {
            Value value = new Value(ctx.TKN_STR().getText().replaceAll("\"", ""));
            return value;
        } else if (ctx.TKN_REAL() != null) {
            Value value = new Value(Double.parseDouble(ctx.TKN_REAL().getText()));
            return value;
        } else if (ctx.VERDADERO() != null) {
            //El interprete de LPP imprime verdadero, por lo que devuelvo el string por ahora
            return new Value(true);
        } else if (ctx.FALSO() != null) {
            return new Value(false);
        }
        return Value.VOID;
    }

    @Override
    public Value visitExpr(LPPParser.ExprContext ctx) {

        if (ctx.funCall() != null) {
            return visitFunCall(ctx.funCall());
        } else if (ctx.TKN_MINUS() != null) {
            Value value = new Value(visitExpr(ctx.expr(0)).value);
            if (value.isReal()) {
                return new Value(value.asReal() * -1);
            } else if (value.isEntero()) {
                return new Value(value.asEntero() * -1);
            } else {
                return value;
            }
        } else if (ctx.literal() != null) {
            return visitLiteral(ctx.literal());
        } else if (ctx.TKN_OPENING_PAR() != null) {
            return visitExpr(ctx.expr(0));
        } else if (ctx.ID() != null) {

            String id = ctx.ID().getText();
            Value value = varTable.get(id);
            return value;

        } else if (ctx.MULOP() != null) {
            String op = ctx.MULOP().getText().toLowerCase();

            Value value1 = visitExpr(ctx.expr(0));
            Value value2 = visitExpr(ctx.expr(1));

            Number num1 = value1.asReal();
            Number num2 = value2.asReal();

            Number ans = null;
            Value ansValue;

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
            }

            if (value1.isEntero() &&  value2.isEntero()) {
                ans = ans.intValue();
            }
            ansValue = new Value(ans);
            return ansValue;

        } else if (ctx.lEx != null && (ctx.TKN_MINUS() != null || ctx.TKN_PLUS() != null)) {
            String op = ctx.TKN_PLUS() != null ? ctx.TKN_PLUS().toString() : ctx.TKN_MINUS().toString();
            Value value1 = visitExpr(ctx.lEx);
            Value value2 = visitExpr(ctx.rEx);

            Number num1 = value1.asReal();
            Number num2 = value2.asReal();

            Number ans = null;
            Value ansValue;

            switch (op) {
                case "+":
                    ans = num1.doubleValue() + num2.doubleValue();
                    break;
                case "-":
                    ans = num1.doubleValue() - num2.doubleValue();
                    break;
            }

            if (value1.isEntero() &&  value2.isEntero()) {
                ans = ans.intValue();
            }
            ansValue = new Value(ans);
            return ansValue;

        } else if (ctx.COMOP() != null) {
            String op = ctx.COMOP().getText();
            Value value1 = visitExpr(ctx.expr(0));
            Value value2 = visitExpr(ctx.expr(1));

            Float num1 = value1.asReal();
            Float num2 = value2.asReal();

            Boolean ans = null;
            Value ansValue;

            switch (op) {
                case "=":
                    ans = Objects.equals(num1, num2);
                    break;
                case "<":
                    ans = num1 < num2 ;
                    break;
                case ">":
                    ans = num1 > num2;
                    break;
                case ">=":
                    ans = num1 >= num2;
                    break;
                case "<=":
                    ans = num1 <= num2;
                    break;
                case "<>":
                    ans = !Objects.equals(num1, num2);
                    break;
            }
            ansValue = new Value(ans);
            return ansValue;
        } else if (ctx.BOLOP() != null) {
            String op = ctx.BOLOP().getText();
            Value value1 = visitExpr(ctx.expr(0));
            Value value2 = visitExpr(ctx.expr(1));
            Value result;

            boolean bValue1 = (value1.asBooleano());
            boolean bValue2 = (value2.asBooleano());

            if (op.equalsIgnoreCase("o")) {
                result = new Value(bValue1 || bValue2);
            } else {
                result = new Value(bValue1 && bValue2);
            }

            return (result);
        }

        return Value.VOID;
    }

    @Override
    public Value visitFunCall(LPPParser.FunCallContext ctx) {
        String functionName = ctx.ID().getText();
        LPPParser.FuncDeclarationContext functionContext = functionTable.get(functionName);
        System.out.println(functionContext.getText());
        if (functionTable.containsKey(functionName)) {
            System.out.println(ctx);
        }
//        if (functionTable.containsKey(functionName)) {
//            FunctionInfo functionInfo = functionTable.get(functionName);
//
//            // Create a new local scope for function parameters
//            HashMap<String, Object> localScope = new HashMap<>();
//            ArrayList<Pair<String, String>> parameters = functionInfo.parameters;
//
//            if (ctx.exprList() != null) {
//                for (int i = 0; i < parameters.size() && i < ctx.exprList().expr().size(); i++) {
//                    String paramName = parameters.get(i).b;
//                    Object argValue = visitExpr(ctx.exprList().expr(i));
//                    localScope.put(paramName, argValue);
//                }
//            }
//
//            // Execute the function's statement block in the new scope
//            MyVisitor<T> functionVisitor = new MyVisitor<>();
//            functionVisitor.varTable = localScope; // Set local scope
//            functionVisitor.visitFuncStmts(functionInfo.stmts);
//        } else {
//            System.out.println("Error: Function " + functionName + " not defined.");
//        }

        return Value.VOID;
    }


}
