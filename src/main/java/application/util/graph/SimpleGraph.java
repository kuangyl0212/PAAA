package application.util.graph;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

class SimpleGraph extends DefaultDirectedGraph<String, DefaultEdge> {
    SimpleGraph() {
        super(DefaultEdge.class);
    }
}
