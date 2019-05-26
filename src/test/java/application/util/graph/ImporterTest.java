package application.util.graph;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static application.util.graph.Importer.*;
import static application.util.graph.Exporter.*;

class ImporterTest {

    @Test
    void importEmptyTest() {
        CDG actualGraph = importCDGFrom("()");
        CDG expectedGraph = new CDG();

        String expected = exportCDGAsDot(expectedGraph);
        String actual = exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importOneNodeTest() {
        CDG actualGraph = Importer.importCDGFrom("(HEAD)");
        CDG expectedGraph = new CDG();
        expectedGraph.addVertex(new Vertex(VertexType.HEAD));

        String expected = exportCDGAsDot(expectedGraph);
        String actual = exportCDGAsDot(actualGraph);

        assertEquals(expected, actual);
    }

    @Test
    void importOneMoreNodeTest() throws Throwable {
        CDG actualGraph = Importer.importCDGFrom("(HEAD EXPR_STAT)");
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
        CDG actualGraph = Importer.importCDGFrom(formalString);
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

    @Test
    void importGeneralTreeTest() {
        SimpleGraph g = importSimpleGraphFrom("()");
        SimpleGraph expectedG = new SimpleGraph();

        String expected = exportSimpleGraphAsDot(expectedG);
        String actual = exportSimpleGraphAsDot(g);

        assertEquals(expected, actual);

    }

    @Test
    void importOneNodeGeneralTreeTest() {
        SimpleGraph g = importSimpleGraphFrom("(a)");
        SimpleGraph expectedG = new SimpleGraph();
        expectedG.addVertex("a");

        String expected = exportSimpleGraphAsDot(expectedG);
        String actual = exportSimpleGraphAsDot(g);

        assertEquals(expected, actual);

    }

    @Test
    void importMoreNodeGeneralTreeTest() {
        SimpleGraph g = importSimpleGraphFrom("(a (b c))");
        SimpleGraph expectedG = new SimpleGraph();
        expectedG.addVertex("a");
        expectedG.addVertex("b");
        expectedG.addEdge("a" ,"b");
        expectedG.addVertex("c");
        expectedG.addEdge("b" ,"c");

        String expected = exportSimpleGraphAsDot(expectedG);
        String actual = exportSimpleGraphAsDot(g);

        assertEquals(expected, actual);

    }

    @Test
    void importMuchMoreNodeGeneralTreeTest() {
        SimpleGraph g = importSimpleGraphFrom("(a d (b c) e)");
        SimpleGraph expectedG = new SimpleGraph();
        expectedG.addVertex("a");
        expectedG.addVertex("d");
        expectedG.addVertex("b");
        expectedG.addEdge("a" ,"d");
        expectedG.addEdge("a" ,"b");
        expectedG.addVertex("c");
        expectedG.addEdge("b" ,"c");
        expectedG.addVertex("e");
        expectedG.addEdge("a","e");

        String expected = exportSimpleGraphAsDot(expectedG);
        String actual = exportSimpleGraphAsDot(g);

        assertEquals(expected, actual);

    }
}