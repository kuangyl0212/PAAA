package graph;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.Set;

public class CDG extends BaseGraph {
   public CDG() {

   }

    public boolean addVertex(VertexType type) {
       Vertex vertex = new Vertex(type);
        return super.addVertex(vertex);
    }
    public int howManyTypeVertexWithoutHead() {
       HashSet<VertexType> types = new HashSet<>();
        for (Vertex v: vertexSet())
            if (v.getType() != VertexType.HEAD)
                types.add(v.getType());
       return types.size();
    }
}
