package application.builder.listeners;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import parser.python.Python3BaseListener;
import parser.python.Python3Parser;

import java.util.Stack;

public class Python3ListenerEnhanced extends Python3BaseListener{
	private Stack<Vertex> vertexStack;
	private CDG cdg;

	public Python3ListenerEnhanced(CDG emptyCDG) {
		super();
		vertexStack = new Stack<>();
		cdg = emptyCDG;
	}

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
	
	@Override
	public void enterSingle_input(Python3Parser.Single_inputContext ctx) {
		Vertex headVertex = createVertexWithCtx(VertexType.HEAD, ctx);
		cdg.addVertex(headVertex);
		vertexStack.push(headVertex);
	}
	@Override
	public void exitSingle_input(Python3Parser.Single_inputContext ctx) {
		vertexStack.pop();
	}
/*
	@Override
	public void enterExpr_stmt(Python3Parser.Expr_stmtContext ctx) {
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.EXPR_STAT, ctx);
	}
	@Override
	public void exitExpr_stmt(Python3Parser.Expr_stmtContext ctx) {
		vertexStack.pop();
	}
*/
	@Override
	public void enterFuncdef(Python3Parser.FuncdefContext ctx) {
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.FUNC_DEF, ctx);
	}
	@Override
	public void exitFuncdef(Python3Parser.FuncdefContext ctx) {
		vertexStack.pop();
	}

	@Override public void enterReturn_stmt(Python3Parser.Return_stmtContext ctx) {
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.RET_STAT, ctx);
	}
	@Override public void exitReturn_stmt(Python3Parser.Return_stmtContext ctx) {
		vertexStack.pop();
	}

	@Override public void enterIf_stmt(Python3Parser.If_stmtContext ctx) {
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.SEL_STAT, ctx);
	}
	@Override public void exitIf_stmt(Python3Parser.If_stmtContext ctx) {
		vertexStack.pop();
	}

	@Override public void enterWhile_stmt(Python3Parser.While_stmtContext ctx) {
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.ITER_STAT, ctx);
	}
	@Override public void exitWhile_stmt(Python3Parser.While_stmtContext ctx) {
		vertexStack.pop();
	}

	@Override public void enterFor_stmt(Python3Parser.For_stmtContext ctx) {
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.ITER_STAT, ctx);
	}
	@Override public void exitFor_stmt(Python3Parser.For_stmtContext ctx) {
		vertexStack.pop();
	}

	@Override public void enterSimple_stmt(Python3Parser.Simple_stmtContext ctx){
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.DECLARATION, ctx);
	}
	@Override
	public void exitSimple_stmt(Python3Parser.Simple_stmtContext ctx) {
		vertexStack.pop();
	}
/*
	@Override public void enterInteger(Python3Parser.IntegerContext ctx) {
		addVertexWithCtxAndEdgeThenPushToStack(VertexType.DECLARATION,ctx);
	}
	@Override public void exitInteger(Python3Parser.IntegerContext ctx) {
		vertexStack.pop();
	}
*/
	/*@Override public void visitErrorNode(ErrorNode node) {
		//For error detecting
	}*/


}



