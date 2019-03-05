package application.util.graph;

import graph.CDG;
import graph.Edge;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.*;
import java.io.Writer;
import java.io.StringWriter;

import graph.Vertex;

public class Exporter {

    private  static ComponentNameProvider<Vertex> CDGVertexIdProvider =
            new ComponentNameProvider<Vertex>() {
                public String getName(Vertex vertex) {
                    return vertex.getType().toString();
                }
            };
    private static ComponentNameProvider<Vertex> CDGVertexLabelProvider  =
            new ComponentNameProvider<Vertex>() {
                public String getName(Vertex vertex) {
                    return vertex.getType().toString();
                }
            };

    private  static ComponentNameProvider<String> simpleGraphVertexIdProvider =
            new ComponentNameProvider<String>() {
                public String getName(String vertex) {
                    return vertex;
                }
            };
    private static ComponentNameProvider<String> simpleGraphVertexLabelProvider  =
            new ComponentNameProvider<String>() {
                public String getName(String vertex) {
                    return vertex;
                }
            };

    private static GraphExporter<Vertex, Edge> CDGExporter =
            new DOTExporter<>(CDGVertexIdProvider, CDGVertexLabelProvider, null);


    private static GraphExporter<String , DefaultEdge> simpleGraphExporter =
            new DOTExporter<>(simpleGraphVertexIdProvider, simpleGraphVertexLabelProvider, null);


    public static String exportCDGAsDot(CDG graph) {
        String dotString = "invalid graph";

        Writer writer = new StringWriter();
        try {
            CDGExporter.exportGraph(graph, writer);
            dotString = writer.toString();
        }
        catch (ExportException e) {

        }
        return dotString;
    }

    public static String exportSimpleGraphAsDot(SimpleGraph graph) {

        String dotString = "invalid graph";

        Writer writer = new StringWriter();
        try {
            simpleGraphExporter.exportGraph(graph, writer);
            dotString = writer.toString();
        }
        catch (ExportException e) {

        }
        return dotString;
    }

    public static String exportCDGAsLispStyle(CDG cdg) {
        if (isEmptyCDG(cdg))
            return "()";
        else
            return exportAsLispStyleFromHead(cdg);

    }

    private static boolean isEmptyCDG(CDG cdg) {
        return cdg.vertexSet().size() == 0;
    }

    private static String exportAsLispStyleFromHead(CDG cdg) {
        Vertex head = cdg.getHead();
        return exportFrom(head, cdg);
    }

    private static String exportFrom(Vertex vertex, CDG cdg) {
        StringBuilder lispStyle = new StringBuilder();
        if (cdg.isTerminal(vertex)){
            if (cdg.isHead(vertex)) {
                lispStyle = new StringBuilder("(" + vertex.getType().toString() + ")");
            }
            else lispStyle.append(" ").append(vertex.getType().toString());
        }
        else {
            lispStyle.append("(").append(vertex.getType().toString());
            if (!cdg.isHead(vertex))
                lispStyle.insert(0, " ");
            for (Vertex child: cdg.getChildrenOf(vertex)
                 ) {
                lispStyle.append(exportFrom(child, cdg));
            }
            lispStyle.append(")");
        }
        return lispStyle.toString();
    }
}
