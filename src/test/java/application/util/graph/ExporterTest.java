package application.util.graph;

import graph.CDG;
import graph.VertexType;
import org.junit.jupiter.api.Test;

import static application.util.graph.Exporter.exportCDGAsLispStyle;
import static org.junit.jupiter.api.Assertions.*;
import static application.util.graph.Importer.*;

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

    @Test
    void exportEmptyCDGAsLispStyleTest() {
        CDG emptyCDG = new CDG();
        assertEquals("()", exportCDGAsLispStyle(emptyCDG));
    }

    @Test
    void exportNonEmptyCDGAsLispStyleTest() {
        String formalString = "(HEAD)";
        CDG cdg = importCDGFrom(formalString);
        assertEquals(formalString, exportCDGAsLispStyle(cdg));
    }

    @Test
    void exportCDGAsLispStyleTest() {
        String formalString = "(HEAD FUNC_DEF)";
        CDG cdg = importCDGFrom(formalString);
        assertEquals(formalString, exportCDGAsLispStyle(cdg));
    }

    @Test
    void exportCDGAsLispStyleMoreTest() {
        String formalString = "(HEAD (FUNC_DEF (BLOCK EXPR_STAT)))";
        CDG cdg = importCDGFrom(formalString);
        assertEquals(formalString, exportCDGAsLispStyle(cdg));
    }

    @Test
    void exportCDGAsLispStyleMuchMoreTest() {
        String formalString = "(HEAD" +
                " (FUNC_DEF (BLOCK EXPR_STAT))" +
                " EXPR_STAT" +
                " DECLARATION" +
                " (SEL_STAT (SEL_CLAUSE EXPR_STAT)))";
        CDG cdg = importCDGFrom(formalString);
        assertEquals(formalString, exportCDGAsLispStyle(cdg));
    }

}
