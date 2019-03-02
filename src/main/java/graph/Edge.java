package graph;

import java.io.Serializable;

public class Edge implements Serializable {
    private VertexType type;

    public void setType(VertexType type) {
        this.type = type;
    }
    public VertexType getType() {
        return type;
    }
}
