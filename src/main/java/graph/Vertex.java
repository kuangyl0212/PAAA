package graph;

import java.io.Serializable;

public class Vertex implements Serializable {
    private VertexType type;

    public Vertex(VertexType type) {
        this.type = type;
    }
    public VertexType getType() {
        return type;
    }

}
