package graph;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class CDG extends DefaultDirectedGraph<Vertex, DefaultEdge> {
   public CDG() {
       super(DefaultEdge.class);
   }

    public boolean addVertex(VertexType type) {
       Vertex vertex = new Vertex(type);
        return super.addVertex(vertex);
    }
}
