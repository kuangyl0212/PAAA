package graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static application.util.graph.Importer.importCDGFrom;
import static org.junit.jupiter.api.Assertions.*;
import static graph.VertexType.*;

public class BaseGraphTest {

    @Test
    void getHeadTest() {
        CDG cdg = importCDGFrom("(HEAD FUNC_DEF" +
                "(BLOCK EXPR_STAT))");
        assertEquals(VertexType.HEAD, cdg.getHead().getType());
    }

    @Test
    void isTerminalFalseTest() {
        CDG cdg = importCDGFrom("(HEAD FUNC_DEF" +
                "(BLOCK EXPR_STAT))");
        Vertex head = cdg.getHead();
        assertFalse(cdg.isTerminal(head));
    }

    @Test
    void isTerminalTrueTest() {
        CDG cdg = importCDGFrom("(HEAD)");
        Vertex head = cdg.getHead();
        assertTrue(cdg.isTerminal(head));
    }

    @Test
    void getChildrenTest() {
        CDG cdg = importCDGFrom("(HEAD FUNC_DEF BLOCK EXPR_STAT)");
        ArrayList<Vertex> children = cdg.getChildrenOf(cdg.getHead());
        assertEquals(3, children.size());
        assertEquals(FUNC_DEF, children.get(0).getType());
        assertEquals(BLOCK, children.get(1).getType());
        assertEquals(EXPR_STAT, children.get(2).getType());
    }
}
