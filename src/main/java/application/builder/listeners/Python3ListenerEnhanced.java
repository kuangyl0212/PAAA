package application.builder.listeners;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.python.Python3BaseListener;
import parser.python.Python3Parser;

import java.util.Stack;

public class Python3ListenerEnhanced extends Python3BaseListener{
	private Stack<Vertex> vertexStack;
    private CDG cdg;

    private void addVertexWithCtxAndEdgeThenPushToStack(VertexType vertexType, ParserRuleContext ctx) {
        Vertex newVertex = createVertexWithCtx(vertexType, ctx);
        addVertexAndEdge(newVertex);
        pushToStack(newVertex);
    }

    private Vertex createVertexWithCtx(VertexType vertexType, ParserRuleContext ctx) {
        Vertex newVertex = new Vertex(vertexType);
        newVertex.setCtx(ctx);
        return newVertex;
    }

    private void addVertexAndEdge(Vertex newVertex) {
        cdg.addVertex(newVertex);
        cdg.addEdge(vertexStack.peek(), newVertex);
    }

    private void pushToStack(Vertex vertex) {
        vertexStack.push(vertex);
    }

    /* */
	
	@override
	public void enterFile_input(Python3Parser.File_inputContext ctx) {
		Vertex headVertex = createVertexWithCtx(VertexType.HEAD, ctx);
        cdg.addVertex(headVertex);
        vertexStack.push(headVertex);
	}
	
	@Override
    public void exitFile_input(Python3Parser.File_inputContext ctx) {
        vertexStack.pop();
    }
	
	@override
	public void enterExpr_stmt(Python3Parser.Expr_stmtContext ctx) {
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.EXPR_STAT, ctx);
	}
	
	@override
	public void exitExpr_stmt(Python3Parser.Expr_stmtContext ctx) {
		vertexStack.pop();
	}
	
	@Override
	public void enterFuncdef(Python3Parser.FuncdefContext ctx) {
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.FUNC_DEF, ctx);
	}
	
	@Override
	public void exitFuncdef(Python3Parser.FuncdefContext ctx) {
		vertexStack.pop();
	}
	
	
}



