package application.util.graph;

import graph.CDG;
import graph.Edge;
import org.jgrapht.io.*;
import java.io.Writer;
import java.io.StringWriter;

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
        GraphExporter<Vertex, Edge> exporter =
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
//        lispStyle += head.getType().toString();
        return exportFrom(head, cdg);
    }

    private static String exportFrom(Vertex vertex, CDG cdg) {
        String lispStyle = "";
        if (cdg.isTerminal(vertex)){
            if (cdg.isHead(vertex)) {
                lispStyle = "(" + vertex.getType().toString() + ")";
            }
            else lispStyle += " " + vertex.getType().toString();
        }
        else {
            lispStyle += "(" + vertex.getType().toString();
            if (!cdg.isHead(vertex))
                lispStyle = " " + lispStyle;
            for (Vertex child: cdg.getChildrenOf(vertex)
                 ) {
                lispStyle += exportFrom(child, cdg);
            }
            lispStyle += ")";
        }
        return lispStyle;
    }
}
