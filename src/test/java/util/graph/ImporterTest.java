package util.graph;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.junit.jupiter.api.Test;

import static util.graph.Exporter.exportCDGAsDot;
import static org.junit.jupiter.api.Assertions.*;
import static util.graph.Importer.importFrom;

class ImporterTest {

    @Test
    void importEmptyTest() {
        CDG actualGraph = importFrom("()");
        CDG expectedGraph = new CDG();

        String expected = exportCDGAsDot(expectedGraph);
        String actual = exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importOneNodeTest() {
        CDG actualGraph = importFrom("(HEAD)");
        CDG expectedGraph = new CDG();
        expectedGraph.addVertex(new Vertex(VertexType.HEAD));

        String expected = exportCDGAsDot(expectedGraph);
        String actual = exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importOneMoreNodeTest() throws Throwable {
        CDG actualGraph = importFrom("(HEAD EXPR_STAT)");
        CDG expectedGraph = new CDG();
        Vertex head = new Vertex("HEAD");
        expectedGraph.addVertex(head);
        Vertex exprStat = new Vertex("EXPR_STAT");
        expectedGraph.addVertex(exprStat);
        expectedGraph.addEdge(head, exprStat);

        String expected = exportCDGAsDot(expectedGraph);
        String actual = exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importFromTest() throws Throwable {
        String formalString = "(HEAD (FUNC_DEF BLOCK))";
        CDG actualGraph = importFrom(formalString);
        CDG expectedGraph = new CDG();

        Vertex head = new Vertex("HEAD");
        expectedGraph.addVertex(head);
        Vertex funcDef = new Vertex("FUNC_DEF");
        expectedGraph.addVertex(funcDef);
        expectedGraph.addEdge(head, funcDef);
        Vertex block = new Vertex("BLOCK");
        expectedGraph.addVertex(block);
        expectedGraph.addEdge(funcDef, block);

        String expected = exportCDGAsDot(expectedGraph);
        String actual = exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }
}