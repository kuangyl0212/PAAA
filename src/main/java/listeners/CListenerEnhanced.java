package listeners;

import java.util.Stack;
import graph.Vertex;
import graph.CDG;
import graph.VertexType;
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

    @Override
    public void enterCompilationUnit(CParser.CompilationUnitContext ctx) {
        Vertex headVertex = new Vertex(VertexType.HEAD);
        cdg.addVertex(headVertex);
        vertexStack.push(headVertex);
    }

    @Override
    public void exitCompilationUnit(CParser.CompilationUnitContext ctx) {
        vertexStack.pop();
    }

    @Override
    public void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        Vertex vertex = new Vertex(VertexType.FUNC_DEF);
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
        Vertex vertex = new Vertex(VertexType.DECLARATION);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
    }

    @Override
    public void enterBlockItemList(CParser.BlockItemListContext ctx) {
        Vertex vertex = new Vertex(VertexType.BLOCK);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
        vertexStack.push(vertex);
    }

    @Override
    public void exitBlockItemList(CParser.BlockItemListContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterExpressionStatement(CParser.ExpressionStatementContext ctx) {
        Vertex vertex = new Vertex(VertexType.EXPR_STAT);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
    }

    @Override public void enterIterationStatement(CParser.IterationStatementContext ctx) {
        Vertex vertex = new Vertex(VertexType.ITER_STAT);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
        vertexStack.push(vertex);
    }

    @Override public void exitIterationStatement(CParser.IterationStatementContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterJumpStatement(CParser.JumpStatementContext ctx) {
        Vertex vertex = new Vertex(VertexType.JUMP_STAT);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
    }

    @Override public void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
        Vertex vertex = new Vertex(VertexType.SEL_STAT);
        cdg.addVertex(vertex);
        cdg.addEdge(vertexStack.peek(), vertex);
        vertexStack.push(vertex);
    }
    @Override public void exitSelectionStatement(CParser.SelectionStatementContext ctx) {
        vertexStack.pop();
    }
}
