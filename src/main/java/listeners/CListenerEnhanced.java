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

    private void addVertexWithCtxAndEdgeThenPushToStack(VertexType vertexType, ParserRuleContext ctx) {
        Vertex newVertex = creatVertexWithCtx(vertexType, ctx);
        addVertexAndEdge(newVertex);
        pushToStack(newVertex);
    }

    private Vertex creatVertexWithCtx(VertexType vertexType, ParserRuleContext ctx) {
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
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.FUNC_DEF, ctx);
    }

    @Override
    public void exitFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        vertexStack.pop();
    }

    @Override
    public void enterDeclaration(CParser.DeclarationContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.DECLARATION, ctx);
    }
    @Override
    public void exitDeclaration(CParser.DeclarationContext ctx) {
        vertexStack.pop();
    }

    @Override
    public void enterBlockItemList(CParser.BlockItemListContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.BLOCK, ctx);
    }

    @Override
    public void exitBlockItemList(CParser.BlockItemListContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterExpressionStatement(CParser.ExpressionStatementContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.EXPR_STAT, ctx);
    }
    @Override public void exitExpressionStatement(CParser.ExpressionStatementContext ctx){
        vertexStack.pop();
    }

    @Override public void enterIterationStatement(CParser.IterationStatementContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.ITER_STAT, ctx);
    }

    @Override public void exitIterationStatement(CParser.IterationStatementContext ctx) {
        vertexStack.pop();
    }

    @Override public void enterJumpStatement(CParser.JumpStatementContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.JUMP_STAT, ctx);
    }

    @Override public void exitJumpStatement(CParser.JumpStatementContext ctx) {
        vertexStack.pop();
    }


    @Override public void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.SEL_STAT, ctx);
    }
    @Override public void exitSelectionStatement(CParser.SelectionStatementContext ctx) {
        vertexStack.pop();
    }

    @Override
    public void enterStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.STRUCT_SPEC, ctx);
    }
    @Override
    public void exitStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {
        vertexStack.pop();
    }

    @Override
    public void enterStructDeclarationList(CParser.StructDeclarationListContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.STRUCT_DEC_LIST, ctx);
    }

    @Override
    public void exitStructDeclarationList(CParser.StructDeclarationListContext ctx) {
        vertexStack.pop();
    }

    @Override
    public void enterStructDeclaration(CParser.StructDeclarationContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.STRUCT_DEC, ctx);
    }

    @Override
    public void exitStructDeclaration(CParser.StructDeclarationContext ctx) {
        vertexStack.pop();
    }
}
