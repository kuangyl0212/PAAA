package application.builder.listeners;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.python.Python3BaseListener;
import parser.python.Python3Parser;

import java.util.Stack;

import static graph.VertexType.*;

public class PythonListenerEnhanced extends Python3BaseListener implements Common {
    private Stack<Vertex> vertexStack;
    private CDG cdg;

    public PythonListenerEnhanced(CDG emptyCDG) {
        super();
        vertexStack = new Stack<>();
        cdg = emptyCDG;
    }

    private boolean isInSelection(ParserRuleContext ctx) {
        return vertexStack.peek().getType() == SEL_STAT;
    }

    /* there is no element in the stack when head node is created */
    @Override
    public void enterFile_input(Python3Parser.File_inputContext ctx) {
        Vertex headVertex = creatVertexWithCtx(HEAD, ctx);
        cdg.addVertex(headVertex);
        vertexStack.push(headVertex);
    }
    @Override
    public void exitFile_input(Python3Parser.File_inputContext ctx) {
        vertexStack.pop();
    }

    @Override
    public void enterExpr_stmt(Python3Parser.Expr_stmtContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(EXPR_STAT, ctx);
    }
    @Override
    public void exitExpr_stmt(Python3Parser.Expr_stmtContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterIf_stmt(Python3Parser.If_stmtContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(SEL_STAT, ctx);
    }
    @Override public void exitIf_stmt(Python3Parser.If_stmtContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterSuite(Python3Parser.SuiteContext ctx) {
        VertexType type = BLOCK;
        if (isInSelection(ctx)) type = SEL_CLAUSE;
        addVertexWithCtxAndEdgeThenPushToStack(type, ctx);
    }
    @Override public void exitSuite(Python3Parser.SuiteContext ctx) {
        vertexStack.pop();
    }

    @Override
    public CDG getCDG() {
        return cdg;
    }

    @Override
    public Stack<Vertex> getStack() {
        return vertexStack;
    }
}
