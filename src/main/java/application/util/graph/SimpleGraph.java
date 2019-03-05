package application.util.graph;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class SimpleGraph extends DefaultDirectedGraph<String, DefaultEdge> {
    public SimpleGraph() {
        super(DefaultEdge.class);
    }
}
