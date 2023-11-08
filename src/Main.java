import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {

    public static void main(String [] args) throws Exception{
        LPPLexer lexer = new LPPLexer(CharStreams.fromFileName("input/06.in"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LPPParser parser = new LPPParser(tokens);
        ParseTree tree = parser.program();
        System.out.println(tree.toStringTree(parser));
        MyVisitor loader = new MyVisitor();
        loader.visit(tree);
    }
}