package util.graph;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExporterTest {
    @Test
    public void exportAsDotTest() {
        CDG graph = new CDG();
        graph.addVertex(VertexType.HEAD);
        String expected =
                "strict digraph G {\n" +
                "  HEAD [ label=\"HEAD\" ];\n" +
                "}\n";
        String actual = Exporter.exportAsDot(graph);
        assertEquals(expected, actual);
    }
}
