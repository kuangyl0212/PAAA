package util.graph;

import org.junit.jupiter.api.Test;

import static util.graph.Exporter.exportAsDot;
import static util.graph.Exporter.exportCDGAsDot;
import static org.junit.jupiter.api.Assertions.*;
import static util.graph.Importer.importFrom;

class ImporterTest {

    @Test
    void importEmptyTest() {
        SimpleGraph actualGraph = importFrom("()");
        SimpleGraph expectedGraph = new SimpleGraph();

        String expected = exportAsDot(expectedGraph);
        String actual = exportAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importOneNodeTest() {
        SimpleGraph actualGraph = importFrom("(HEAD)");
        SimpleGraph expectedGraph = new SimpleGraph();
        expectedGraph.addVertex("HEAD");

        String expected = exportAsDot(expectedGraph);
        String actual = exportAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importOneMoreNodeTest() {
        SimpleGraph actualGraph = importFrom("(HEAD EXPR_STAT)");
        SimpleGraph expectedGraph = new SimpleGraph();
        expectedGraph.addVertex("HEAD");
        expectedGraph.addVertex("EXPR_STAT");
        expectedGraph.addEdge("HEAD", "EXPR_STAT");

        String expected = exportAsDot(expectedGraph);
        String actual = exportAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importFromTest() {
        String formalString = "(HEAD (FUNC_DEF BLOCK))";
        SimpleGraph actualGraph = importFrom(formalString);
        SimpleGraph expectedGraph = new SimpleGraph();
        expectedGraph.addVertex("HEAD");
        expectedGraph.addVertex("FUNC_DEF");
        expectedGraph.addEdge("HEAD", "FUNC_DEF");
        expectedGraph.addVertex("BLOCK");
        expectedGraph.addEdge("FUNC_DEF", "BLOCK");

        String expected = exportAsDot(expectedGraph);
        String actual = exportAsDot(actualGraph);

        assertEquals(expected, actual);
    }
}