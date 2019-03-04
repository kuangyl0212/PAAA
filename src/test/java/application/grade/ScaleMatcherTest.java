package application.grade;

import graph.CDG;
import graph.VertexType;
import org.junit.jupiter.api.Test;

import static application.util.graph.Importer.importFrom;

import static org.junit.jupiter.api.Assertions.*;

class ScaleMatcherTest {

    @Test
    void oneVertexTypeCounterTest() {
        CDG cdg = importFrom("(HEAD" +
                "(FUNC_DEF))");
        Scale scale = new Scale(cdg);
        assertEquals(1, scale.get(VertexType.FUNC_DEF));
    }

    @Test
    void moreTypeTest() {
        CDG cdg = importFrom("(HEAD" +
                "(FUNC_DEF" +
                "   (BLOCK" +
                "       (SEL_STAT" +
                "           (SEL_CLAUSE EXPR_STAT)" +
                "           (SEL_STAT" +
                "               (SEL_CLAUSE EXPR_STAT)" +
                "               (SEL_CLAUSE EXPR_STAT))))))");
        Scale scale = new Scale(cdg);
        assertEquals(1, scale.get(VertexType.FUNC_DEF));
        assertEquals(1, scale.get(VertexType.BLOCK));
        assertEquals(2, scale.get(VertexType.SEL_STAT));
        assertEquals(3, scale.get(VertexType.SEL_CLAUSE));
        assertEquals(3, scale.get(VertexType.EXPR_STAT));
    }
}