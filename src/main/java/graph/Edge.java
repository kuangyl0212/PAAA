package graph;

import org.jgrapht.graph.DefaultEdge;

import java.io.Serializable;

public class Edge extends DefaultEdge {
    private VertexType type;

    public void setType(VertexType type) {
        this.type = type;
    }
    public VertexType getType() {
        return type;
    }

    @Override
    protected Object getSource() {
        return super.getSource();
    }

    @Override
    protected Object getTarget() {
        return super.getTarget();
    }
}
