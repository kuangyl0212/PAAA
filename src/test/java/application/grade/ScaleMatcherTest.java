package application.grade;

import graph.CDG;
import graph.VertexType;
import org.junit.jupiter.api.Test;

import static application.util.graph.Importer.importFrom;
import static application.config.scale.Parameter.*;

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

    @Test
    void sameScaleTest() {
        CDG cdg = importFrom("(HEAD" +
                "(FUNC_DEF" +
                "   (BLOCK" +
                "       (SEL_STAT" +
                "           (SEL_CLAUSE EXPR_STAT)" +
                "           (SEL_STAT" +
                "               (SEL_CLAUSE EXPR_STAT)" +
                "               (SEL_CLAUSE EXPR_STAT))))))");
        double actual = ScaleMatcher.grade(cdg, cdg);
        assertEquals(SCALE_BASE, actual);
    }

    @Test
    void totalDifferentScaleTest() {
        CDG cdgTmp = importFrom("(HEAD" +
                "(FUNC_DEF" +
                "   (BLOCK" +
                "       (SEL_STAT" +
                "           (SEL_CLAUSE EXPR_STAT)" +
                "           (SEL_STAT" +
                "               (SEL_CLAUSE EXPR_STAT)" +
                "               (SEL_CLAUSE EXPR_STAT))))))");
        CDG cdgStu = importFrom("(HEAD)");
        double actual = ScaleMatcher.grade(cdgTmp, cdgStu);
        assertEquals(0, actual);
    }

    @Test
    void oneTypeSameTest(){
        CDG cdgTmp = importFrom("(HEAD" +
                "(FUNC_DEF" +
                "   (BLOCK" +
                "       (SEL_STAT" +
                "           (SEL_CLAUSE EXPR_STAT)" +
                "           (SEL_STAT" +
                "               (SEL_CLAUSE EXPR_STAT)" +
                "               (SEL_CLAUSE EXPR_STAT))))))");
        CDG cdgStu = importFrom("(HEAD FUNC_DEF)");
        double actual = ScaleMatcher.grade(cdgTmp, cdgStu);
        assertEquals( SCALE_BASE / cdgTmp.howManyTypeVertexWithouHead(), actual);
    }

    @Test
    void someTypeSameTest(){
        CDG cdgTmp = importFrom("(HEAD" +
                "(FUNC_DEF" +
                "   (BLOCK" +
                "       (SEL_STAT" +
                "           (SEL_CLAUSE EXPR_STAT)" +
                "           (SEL_STAT" +
                "               (SEL_CLAUSE EXPR_STAT)" +
                "               (SEL_CLAUSE EXPR_STAT))))))");
        CDG cdgStu = importFrom("(HEAD " +
                "(FUNC_DEF" +
                "   (BLOCK EXPR_STAT)))");
        double actual = ScaleMatcher.grade(cdgTmp, cdgStu);
        double expected = (2 + 1.0/ 3) * SCALE_BASE / cdgTmp.howManyTypeVertexWithouHead();
        assertEquals( expected, actual);
    }
}