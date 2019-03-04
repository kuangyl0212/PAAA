package application.util.graph;

import graph.CDG;
import graph.VertexType;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExporterTest {
    @Test
    public void exportCDGAsDotTest() {
        CDG graph = new CDG();
        graph.addVertex(VertexType.HEAD);
        String expected =
                "strict digraph G {\n" +
                "  HEAD [ label=\"HEAD\" ];\n" +
                "}\n";
        String actual = Exporter.exportCDGAsDot(graph);
        assertEquals(expected, actual);
    }
}
