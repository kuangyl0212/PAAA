package listeners;

import java.util.Stack;
import graph.Vertex;
import graph.CDG;
import graph.VertexType;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.c.CBaseListener;
import parser.c.CParser;

public class CListenerEnhanced extends CBaseListener {
    private Stack<Vertex> vertexStack;
    private CDG cdg;

    public CListenerEnhanced(CDG emptyCDG) {
        super();
        vertexStack = new Stack<>();
        cdg = emptyCDG;
    }

    private Vertex creatVertexWithCtx(VertexType type, ParserRuleContext ctx) {
        Vertex vertex = new Vertex(type);
        vertex.setCtx(ctx);
        return vertex;
    }

    @Override
    public void enterCompilationUnit(CParser.CompilationUnitContext ctx) {
        Vertex headVertex = creatVertexWithCtx(VertexType.HEAD, ctx);
        cdg.addVertex(headVertex);
        vertexStack.push(headVertex);
    }

    @Override
    public void exitCompilationUnit(CParser.CompilationUnitContext ctx) {
        vertexStack.pop();
    }

    @Override
    public void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        Vertex vertex = creatVertexWithCtx(VertexType.FUNC_DEF, ctx);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
        vertexStack.push(vertex);
    }

    @Override
    public void exitFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        vertexStack.pop();
    }

    @Override
    public void enterDeclaration(CParser.DeclarationContext ctx) {
        Vertex vertex = creatVertexWithCtx(VertexType.DECLARATION, ctx);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
    }

    @Override
    public void enterBlockItemList(CParser.BlockItemListContext ctx) {
        Vertex vertex = creatVertexWithCtx(VertexType.BLOCK, ctx);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
        vertexStack.push(vertex);
    }

    @Override
    public void exitBlockItemList(CParser.BlockItemListContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterExpressionStatement(CParser.ExpressionStatementContext ctx) {
        Vertex vertex = creatVertexWithCtx(VertexType.EXPR_STAT, ctx);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
    }

    @Override public void enterIterationStatement(CParser.IterationStatementContext ctx) {
        Vertex vertex = creatVertexWithCtx(VertexType.ITER_STAT, ctx);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
        vertexStack.push(vertex);
    }

    @Override public void exitIterationStatement(CParser.IterationStatementContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterJumpStatement(CParser.JumpStatementContext ctx) {
        Vertex vertex = creatVertexWithCtx(VertexType.JUMP_STAT, ctx);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
    }

    @Override public void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
        Vertex vertex = creatVertexWithCtx(VertexType.SEL_STAT, ctx);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
        vertexStack.push(vertex);
    }
    @Override public void exitSelectionStatement(CParser.SelectionStatementContext ctx) {
        vertexStack.pop();
    }
}
