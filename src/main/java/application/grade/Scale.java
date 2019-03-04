package application.grade;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;

import java.util.HashMap;

public class Scale {

    private HashMap<VertexType, Integer> vertexTypeCount;

    public Scale(CDG cdg) {
        vertexTypeCount = new HashMap<>();
        for (Vertex vertex : cdg.vertexSet()) {
            if (typeExist(vertex))
                updateTypeCount(vertex);
            else
                putType(vertex);
        }
    }

    private boolean typeExist(Vertex vertex) {
        return vertexTypeCount.containsKey(vertex.getType());
    }

    private void updateTypeCount(Vertex vertex) {
        VertexType key = vertex.getType();
        int count = vertexTypeCount.get(key);
        vertexTypeCount.replace(key, count + 1);
    }

    private void putType(Vertex vertex) {
        vertexTypeCount.put(vertex.getType(), 1);
    }

    public int get(VertexType type) {
        if (vertexTypeCount.containsKey(type))
            return vertexTypeCount.get(type);
        return 0;
    }
    public int get(String type) throws Throwable {
        return vertexTypeCount.get(VertexType.getTypeFrom(type));
    }

}
