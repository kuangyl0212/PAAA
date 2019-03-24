package application.builder.listeners;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

public interface Common {
    CDG getCDG();
    Stack<Vertex> getStack();

    default void addVertexWithCtxAndEdgeThenPushToStack(VertexType vertexType, ParserRuleContext ctx) {
        Vertex newVertex = creatVertexWithCtx(vertexType, ctx);
        addVertexAndEdge(newVertex);
        pushToStack(newVertex);
    }

    default Vertex creatVertexWithCtx(VertexType vertexType, ParserRuleContext ctx) {
        Vertex newVertex = new Vertex(vertexType);
        newVertex.setCtx(ctx);
        return newVertex;
    }

    default void addVertexAndEdge(Vertex newVertex) {
        getCDG().addVertex(newVertex);
        getCDG().addEdge(getStack().peek(), newVertex);
    }

    default void pushToStack(Vertex vertex) {
        getStack().push(vertex);
    }

}
