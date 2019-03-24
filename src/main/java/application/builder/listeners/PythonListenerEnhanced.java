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

    @Override
    public CDG getCDG() {
        return cdg;
    }

    @Override
    public Stack<Vertex> getStack() {
        return vertexStack;
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
    public void enterFor_stmt(Python3Parser.For_stmtContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(ITER_STAT, ctx);
    }
    @Override
    public void exitFor_stmt(Python3Parser.For_stmtContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterFuncdef(Python3Parser.FuncdefContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(FUNC_DEF, ctx);
    }
    @Override public void exitFuncdef(Python3Parser.FuncdefContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterReturn_stmt(Python3Parser.Return_stmtContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(JUMP_STAT, ctx);
    }
    @Override public void exitReturn_stmt(Python3Parser.Return_stmtContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterWhile_stmt(Python3Parser.While_stmtContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(ITER_STAT, ctx);
    }
    @Override public void exitWhile_stmt(Python3Parser.While_stmtContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterBreak_stmt(Python3Parser.Break_stmtContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(JUMP_STAT, ctx);
    }
    @Override public void exitBreak_stmt(Python3Parser.Break_stmtContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterContinue_stmt(Python3Parser.Continue_stmtContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(JUMP_STAT, ctx);
    }
    @Override public void exitContinue_stmt(Python3Parser.Continue_stmtContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterClassdef(Python3Parser.ClassdefContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(CLASS_DEF, ctx);
    }
    @Override public void exitClassdef(Python3Parser.ClassdefContext ctx) {
        vertexStack.pop();
    }
}
