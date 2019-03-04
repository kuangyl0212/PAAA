package util.graph;

import graph.CDG;
import org.jgrapht.io.*;
import java.io.Writer;
import java.io.StringWriter;
import org.jgrapht.graph.DefaultEdge;
import graph.Vertex;

public class Exporter {

    public static String exportCDGAsDot(CDG graph) {
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

    public static String exportAsDot(SimpleGraph graph) {
        ComponentNameProvider<String> vertexIdProvider = new ComponentNameProvider<String>() {
            public String getName(String vertex) {
                return vertex;
            }
        };
        ComponentNameProvider<String> vertexLabelProvider = new ComponentNameProvider<String>() {
            public String getName(String vertex) {
                return vertex;
            }
        };
        GraphExporter<String, DefaultEdge> exporter =
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
