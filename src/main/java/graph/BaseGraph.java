package graph;

import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.*;

public abstract class BaseGraph extends DefaultDirectedGraph<Vertex, Edge> {
    public BaseGraph() {
        super(Edge.class);
    }

    public Vertex getHead() {
        return vertexSet()
                .stream()
                .filter(this::isHead)
                .findAny()
                .get();
    }

    public boolean isHead(Vertex vertex) {
        return vertex.getType().equals(VertexType.HEAD);
    }

    public boolean isTerminal(Vertex vertex) {

        return outDegreeOf(vertex) <= 0;
    }

    public ArrayList<Vertex> getChildrenOf(Vertex vertex) {
        Set<Edge> outgoingEdgesOf = outgoingEdgesOf(vertex);
        ArrayList<Vertex> children = new ArrayList<>();
        for (Edge edge: outgoingEdgesOf)
            if (!children.contains(vertex))
            children.add(getEdgeTarget(edge));
        return children;
    }
}
