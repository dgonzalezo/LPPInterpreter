import org.antlr.v4.runtime.misc.Pair;

import javax.swing.*;
import java.util.*;

public class MyVisitor extends LPPBaseVisitor<Value> {
    Scanner io = new Scanner(System.in);
    private HashMap<String, Value> globalMem = new HashMap<String, Value>();
    private Stack<HashMap<String, Value>> scopes = new Stack<HashMap<String, Value>>();
    HashMap<String, LPPParser.FuncDeclarationContext> functionTable = new HashMap<String, LPPParser.FuncDeclarationContext>();
    HashMap<String, LPPParser.ProcDeclarationContext> procTable = new HashMap<String, LPPParser.ProcDeclarationContext>();

    @Override
    public Value visitProgram(LPPParser.ProgramContext ctx) {
        scopes.push(globalMem);
        return visitChildren(ctx);
    }

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
        String funcName = ctx.ID().getText().toLowerCase();
        functionTable.put(funcName, ctx);
        return Value.VOID;
    }

    @Override
    public Value visitProcDeclaration(LPPParser.ProcDeclarationContext ctx) {
        String funcName = ctx.ID().getText().toLowerCase();
        procTable.put(funcName, ctx);
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
        } else if (ctx.readStmt() != null) {
            return visitReadStmt(ctx.readStmt());
        } else if (ctx.returnStmt() != null) {
            return visitReturnStmt(ctx.returnStmt());
        }
        return Value.VOID;
    }

    @Override
    public Value visitIfStmt(LPPParser.IfStmtContext ctx) {
        Value booleanString = visitExpr(ctx.expr());
        Boolean booleanValue = booleanString.asBooleano();

        if (booleanValue) {
            visitStmts(ctx.stmts(0));
        } else {
            if (ctx.SINO() != null) {
                visitStmts(ctx.stmts(1));
            }
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
        String variableName = ctx.expr(0).ID().getText().toLowerCase();
        Value value = visitExpr(ctx.expr(1));

        if (scopes.peek().get(variableName) == null) {
            System.out.println(variableName + " ERROR: no ha si declarada!!!!111");
        } else {
            scopes.peek().put(variableName, value);
            //System.out.printf("Se asigno %s a variable %s\n", varTable.get(variableName), variableName);
        }

        return Value.VOID;

    }

    @Override
    public Value visitReadStmt(LPPParser.ReadStmtContext ctx) {

        for (int i = 0; i < ctx.exprList().expr().size(); i++) {
            String id = ctx.exprList().expr(i).getText();
            Value value = new Value(io.nextLine());

            scopes.peek().put(id, value);
        }

        return Value.VOID;
    }

    @Override
    public Value visitCallStmt(LPPParser.CallStmtContext ctx) {
        if (ctx.NUEVA_LINEA() != null) {
            System.out.println("");
        } else {
            visitFunCall(ctx.funCall());
        }
        return Value.VOID;
    }

    @Override
    public Value visitRepeatStmt(LPPParser.RepeatStmtContext ctx) {
        CreateNewScope();
        do {
            visitStmts(ctx.stmts());
        }
        while (!(visitExpr(ctx.expr())).asBooleano());
        AdjustScope();
        return Value.VOID;

    }

    @Override
    public Value visitForAssign(LPPParser.ForAssignContext ctx) {
        String id = ctx.ID().getText().toLowerCase();
        Value value = this.visit(ctx.expr());
        scopes.peek().put(id, value);
        return value;
    }

    public Value visitForStmt(LPPParser.ForStmtContext ctx) {
        CreateNewScope();
        String identifier = (String) ctx.forAssign().ID().getText().toLowerCase();
        Value initial_value = visitExpr(ctx.forAssign().expr());
        Value limit = visitExpr(ctx.expr());

        if (scopes.peek().get(identifier) == null) {
            System.out.println("Error, debe ser declarada");
            return null;
        }
        scopes.peek().put(identifier, initial_value);

        for (int i = initial_value.asEntero(); i <= limit.asEntero(); i++) {
            initial_value = new Value(initial_value.asEntero() + 1);
            visitStmts(ctx.forBlock().stmts());
            scopes.peek().put(identifier, initial_value);
        }
        AdjustScope();
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
                    scopes.peek().put(variable.getText().toLowerCase(), new Value(0));
                } else if (varType.startsWith("real")) {
                    scopes.peek().put(variable.getText().toLowerCase(), new Value(0.0));
                } else if (varType.startsWith("cadena")) {
                    //Quizas toque hacer que la cadena no sea mayor a un tamano...
                    scopes.peek().put(variable.getText().toLowerCase(), new Value(""));
                } else if (varType.startsWith("arreglo")) {
                    scopes.peek().put(variable.getText().toLowerCase(), new Value(new ArrayList<>()));
                } else if (varType.equals("booleano")) {
                    scopes.peek().put(variable.getText().toLowerCase(), new Value(false));
                } else if (varType.equals("caracter")) {
                    scopes.peek().put(variable.getText().toLowerCase(), new Value(""));
                }
                //If its a register then
                else {
                    scopes.peek().put(variable.getText().toLowerCase(), new Value(null));
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
        } else if (ctx.TKN_CHAR() != null) {
            Value value = new Value(ctx.TKN_CHAR().getText().replaceAll("'", ""));
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
        } else if (ctx.TKN_MINUS() != null && ctx.lEx == null) {
            Value value = (visitExpr(ctx.expr(0)));
            if (value.isReal()) {
                return new Value(value.asReal() * -1);
            } else if (value.isEntero()) {
                return new Value(value.asEntero() * -1);
            } else {
                return new Value(value.asEntero() * -1);
            }
        } else if (ctx.literal() != null) {
            return visitLiteral(ctx.literal());
        } else if (ctx.TKN_OPENING_PAR() != null) {
            return visitExpr(ctx.expr(0));
        } else if (ctx.ID() != null) {

            String id = ctx.ID().getText().toLowerCase();
            Value value = scopes.peek().get(id);
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
                case "^":
                    ans = Math.pow(num1.floatValue(), num2.floatValue());
                    break;
            }

            if (value1.isEntero() && value2.isEntero()) {
                ans = ans.intValue();
            }
            ansValue = new Value(ans);
            return ansValue;

        } else if (ctx.lEx != null && (ctx.TKN_MINUS() != null || ctx.TKN_PLUS() != null)) {
            String op = ctx.TKN_PLUS() != null ? ctx.TKN_PLUS().toString() : ctx.TKN_MINUS().toString();
            Value value1 = visitExpr(ctx.lEx);
            Value value2 = visitExpr(ctx.rEx);
            Number num1 = 0,num2 = 0;
            if (!(value1.isCadena() || value2.isCadena())) {
                 num1 = value1.asReal();
                 num2 = value2.asReal();
            }

            Number ans = null;
            Value ansValue;

            switch (op) {
                case "+":
                    if (value1.isCadena() || value2.isCadena()) {
                        ansValue = new Value(value1.asCadena() + value2.asCadena());
                        return ansValue;
                    } else {
                        ans = num1.doubleValue() + num2.doubleValue();
                    }
                    break;
                case "-":
                    ans = num1.doubleValue() - num2.doubleValue();
                    break;
            }

            if (value1.isEntero() && value2.isEntero()) {
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
                    ans = num1 < num2;
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
    public Value visitReturnStmt(LPPParser.ReturnStmtContext ctx) {
        scopes.peek().put("return", visitExpr(ctx.expr()));
        return visitExpr(ctx.expr());
    }

    @Override
    public Value visitFunCall(LPPParser.FunCallContext ctx) {
        if (functionTable.containsKey(ctx.ID().getText().toLowerCase())) {
            CreateNewScope();
            String functionName = ctx.ID().getText().toLowerCase();
            LPPParser.FuncDeclarationContext functionContext = functionTable.get(functionName);
            if (ctx.exprList() != null) {
                List<LPPParser.ExprContext> arguments = ctx.exprList().expr();
                List<LPPParser.ParameterContext> attributes = functionContext.parameters().parameter();
                int counter = 0;
                for (LPPParser.ParameterContext attri : attributes) {
                    String id = attri.ID().getText();
                    Value val = this.visit(arguments.get(counter));
                    scopes.peek().put(id, val);
                    counter++;
                }
            }
            this.visitChildren(functionContext);
            Value returnVal = scopes.peek().get("return");
            AdjustScope();
            return returnVal;
        } else if (procTable.containsKey(ctx.ID().getText().toLowerCase())) {
            CreateNewScope();
            String procName = ctx.ID().getText().toLowerCase();
            LPPParser.ProcDeclarationContext procContext = procTable.get(procName);
            if (ctx.exprList() != null) {
                List<LPPParser.ExprContext> arguments = ctx.exprList().expr();
                List<LPPParser.ParameterContext> attributes = procContext.parameters().parameter();
                int counter = 0;
                if (arguments.size() > 0) {
                    for (LPPParser.ParameterContext attri : attributes) {
                        String id = attri.ID().getText();
                        Value val = this.visit(arguments.get(counter));
                        scopes.peek().put(id, val);
                        counter++;
                    }
                }
            }
            this.visitChildren(procContext);
            AdjustScope();
        }
        return Value.VOID;
    }

    //HELPER METHODs that adjusts scope at the end of a block
    private void CreateNewScope() {
        HashMap<String, Value> newScope = new HashMap<String, Value>();
        newScope.putAll(scopes.peek());
        scopes.push(newScope);
    }


    private void AdjustScope() {
        HashMap<String, Value> currScope = scopes.pop();
        HashMap<String, Value> oldScope = scopes.peek();
        for (Map.Entry<String, Value> entry : oldScope.entrySet()) {
            if (currScope.containsKey(entry.getKey())) {
                Value val = currScope.get(entry.getKey());
                oldScope.put(entry.getKey(), val);
            }
        }
    }


}
