package graph;

import org.jgrapht.graph.DefaultEdge;

import java.io.Serializable;

public class Edge extends DefaultEdge implements Serializable {
    private VertexType type;

    public void setType(VertexType type) {
        this.type = type;
    }
    public VertexType getType() {
        return type;
    }
}
