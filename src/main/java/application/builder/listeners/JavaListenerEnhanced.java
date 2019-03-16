package application.builder.listeners;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.java.JavaBaseListener;
import parser.java.JavaParser;

import java.util.Stack;

public class JavaListenerEnhanced extends JavaBaseListener {
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

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        Vertex headVertex = createVertexWithCtx(VertexType.HEAD, ctx);
        cdg.addVertex(headVertex);
        vertexStack.push(headVertex);
    }

    @Override public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.DECLARATION, ctx);
    }

    @Override public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.FUNC_DEF, ctx);
    }

    @Override public void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.DECLARATION, ctx);
    }

    @Override public void enterStatement(JavaParser.StatementContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.EXPR_STAT, ctx);
    }

    @Override public void enterForControl(JavaParser.ForControlContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.ITER_STAT, ctx);
    }

    @Override public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        addVertexWithCtxAndEdgeThenPushToStack(VertexType.HEAD, ctx);
    }

    @Override public void exitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        vertexStack.pop();
    }

    @Override public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        vertexStack.pop();
    }

    @Override public void exitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) {
        vertexStack.pop();
    }

    @Override public void exitStatement(JavaParser.StatementContext ctx) {
        vertexStack.pop();
    }

    @Override public void exitForControl(JavaParser.ForControlContext ctx) {
        vertexStack.pop();
    }

    @Override public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        vertexStack.pop();
    }

}
