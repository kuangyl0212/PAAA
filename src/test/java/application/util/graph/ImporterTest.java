package application.util.graph;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImporterTest {

    @Test
    void importEmptyTest() {
        CDG actualGraph = Importer.importFrom("()");
        CDG expectedGraph = new CDG();

        String expected = Exporter.exportCDGAsDot(expectedGraph);
        String actual = Exporter.exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importOneNodeTest() {
        CDG actualGraph = Importer.importFrom("(HEAD)");
        CDG expectedGraph = new CDG();
        expectedGraph.addVertex(new Vertex(VertexType.HEAD));

        String expected = Exporter.exportCDGAsDot(expectedGraph);
        String actual = Exporter.exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importOneMoreNodeTest() throws Throwable {
        CDG actualGraph = Importer.importFrom("(HEAD EXPR_STAT)");
        CDG expectedGraph = new CDG();
        Vertex head = new Vertex("HEAD");
        expectedGraph.addVertex(head);
        Vertex exprStat = new Vertex("EXPR_STAT");
        expectedGraph.addVertex(exprStat);
        expectedGraph.addEdge(head, exprStat);

        String expected = Exporter.exportCDGAsDot(expectedGraph);
        String actual = Exporter.exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importFromTest() throws Throwable {
        String formalString = "(HEAD (FUNC_DEF BLOCK))";
        CDG actualGraph = Importer.importFrom(formalString);
        CDG expectedGraph = new CDG();

        Vertex head = new Vertex("HEAD");
        expectedGraph.addVertex(head);
        Vertex funcDef = new Vertex("FUNC_DEF");
        expectedGraph.addVertex(funcDef);
        expectedGraph.addEdge(head, funcDef);
        Vertex block = new Vertex("BLOCK");
        expectedGraph.addVertex(block);
        expectedGraph.addEdge(funcDef, block);

        String expected = Exporter.exportCDGAsDot(expectedGraph);
        String actual = Exporter.exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }
}