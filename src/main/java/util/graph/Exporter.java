package util.graph;

import graph.CDG;
import org.jgrapht.io.*;
import java.io.Writer;
import java.io.StringWriter;
import org.jgrapht.graph.DefaultEdge;
import graph.Vertex;

public class Exporter {

    public static String exportAsDot(CDG graph) {
        ComponentNameProvider<Vertex> vertexIdProvider = new ComponentNameProvider<Vertex>() {
            public String getName(Vertex vertex) {
                return vertex.getType().toString();
            }
        };
        ComponentNameProvider<Vertex> vertexLabelProvider = new ComponentNameProvider<Vertex>() {
            public String getName(Vertex vertex) {
                return vertex.getType().toString();
            }
        };
        GraphExporter<Vertex, DefaultEdge> exporter =
                new DOTExporter<>(vertexIdProvider, vertexLabelProvider, null);

        String dotString = "invalid graph";

        Writer writer = new StringWriter();
        try {
            exporter.exportGraph(graph, writer);
            dotString = writer.toString();
        }
        catch (ExportException e) {

        }
        return dotString;
    }
}
