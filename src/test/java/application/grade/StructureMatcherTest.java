/**
 * THESE TEST IS NOT EASY TO UNDERSTAND
 * @WARNNING !!!PLEASE REFER TO THE PAPER IF YOU WANT CHANGE SOMETHING
 */

package application.grade;

import graph.CDG;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static application.util.graph.Importer.*;

class StructureMatcherTest {

    @Test
    void matchOnlyHeadCDGTest() {
        CDG cdg = importCDGFrom("(HEAD)");
        StructureMatcher matcher = new StructureMatcher(cdg, cdg);
        assertEquals(1, matcher.howManyVertexesMatched());
    }

    @Test
    void matchSmallCDGTest() {
        CDG cdg = importCDGFrom("(HEAD FUNC_DEF)");
        StructureMatcher matcher = new StructureMatcher(cdg, cdg);
        assertEquals(2, matcher.howManyVertexesMatched());
    }

    @Test
    void matchNestedCDGTest() {
        CDG cdg = importCDGFrom("(HEAD " +
                "DECLARATION " +
                "EXPR_STAT" +
                "(FUNC_DEF " +
                "   (BLOCK " +
                "       (SEL_STAT " +
                "           (SEL_CLAUSE EXPR_STAT))" +
                "       EXPR_STAT" +
                "       DECLARATION" +
                "       EXPR_STAT)))");
        StructureMatcher matcher = new StructureMatcher(cdg, cdg);
        CDG paired = matcher.whichVertexesMatched();
        assertEquals(8, matcher.howManyVertexesMatched());
        assertEquals(8, paired.vertexSet().size());

    }

    @Test
    void matchDifferentCDGTest() {
        CDG CDG_A = importCDGFrom("(HEAD " +
                "DECLARATION " +
                "(FUNC_DEF " +
                "   (BLOCK " +
                "       (SEL_STAT " +
                "           (SEL_CLAUSE EXPR_STAT))" +
                "       EXPR_STAT)))");
        CDG CDG_B = importCDGFrom("(HEAD)");
        StructureMatcher matcher = new StructureMatcher(CDG_A, CDG_B);
        assertEquals(1, matcher.howManyVertexesMatched());
    }

    @Test
    void matchDifferentCDGOfMoreVertexTest() {
        CDG CDG_A = importCDGFrom("(HEAD " +
                "DECLARATION " +
                "EXPR_STAT" +
                "(FUNC_DEF " +
                "   (BLOCK " +
                "       (SEL_STAT " +
                "           (SEL_CLAUSE EXPR_STAT))" +
                "       EXPR_STAT" +
                "       DECLARATION" +
                "       EXPR_STAT)))");
        CDG CDG_B = importCDGFrom("(HEAD" +
                "DECLARATION " +
                "FUNC_DEF " +
                "EXPR_STAT)");
        StructureMatcher matcher = new StructureMatcher(CDG_A, CDG_B);
        CDG paired = matcher.whichVertexesMatched();
        assertEquals(3, matcher.howManyVertexesMatched());
        assertEquals(3, paired.vertexSet().size());

    }

    @Test
    void matchDifferentCDGOfMuchMoreVertexTest() {
        CDG CDG_A = importCDGFrom("(HEAD " +
                "DECLARATION " +
                "EXPR_STAT" +
                "(FUNC_DEF " +
                "   (BLOCK " +
                "       (SEL_STAT " +
                "           (SEL_CLAUSE EXPR_STAT))" +
                "       EXPR_STAT" +
                "       DECLARATION" +
                "       EXPR_STAT)))");
        CDG CDG_B = importCDGFrom("(HEAD" +
                "DECLARATION " +
                "(FUNC_DEF BLOCK)" +
                "EXPR_STAT" +
                "(FUNC_DEF " +
                "   (BLOCK " +
                "       (SEL_STAT " +
                "           (SEL_CLAUSE EXPR_STAT))))" +
                ")");
        StructureMatcher matcher = new StructureMatcher(CDG_A, CDG_B);
        CDG paired = matcher.whichVertexesMatched();
        assertEquals(7, matcher.howManyVertexesMatched());
        assertEquals(7, paired.vertexSet().size());

    }
}