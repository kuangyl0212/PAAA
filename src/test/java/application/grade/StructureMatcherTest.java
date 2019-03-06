/*
 * THESE TESTS ARE NOT EASY TO UNDERSTAND
 * @WARNING !!!PLEASE REFER TO THE PAPER IF YOU WANT CHANGE SOMETHING
 */

package application.grade;

import graph.CDG;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static application.util.graph.Importer.*;
import static application.config.structure.Parameter.*;

class StructureMatcherTest {

    @Test
    void matchOnlyHeadCDGTest() {
        CDG cdg = importCDGFrom("(HEAD)");
        StructureMatcher matcher = new StructureMatcher(cdg, cdg);
        assertEquals(1, matcher.howManyVertexesMatched());
        assertEquals(0, matcher.grade());
    }

    @Test
    void matchSmallCDGTest() {
        CDG cdg = importCDGFrom("(HEAD FUNC_DEF)");
        StructureMatcher matcher = new StructureMatcher(cdg, cdg);
        assertEquals(2, matcher.howManyVertexesMatched());
        assertEquals(STRUCTURE_BASE, matcher.grade());
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
        assertEquals(STRUCTURE_BASE, matcher.grade());
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
        assertEquals(0, matcher.grade());
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
        System.out.println(matcher.howManyMatchedOfEachType());
        String expected = String.format("%.2f", STRUCTURE_BASE * 4 / 9);
        String actual = String.format("%2.2f", matcher.grade());
        assertEquals(expected, actual);
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
        VertexTypeCount matchedTypeCount = matcher.howManyMatchedOfEachType();
        System.out.println(matchedTypeCount);
        String expectedCount = "iterations: \t0\n" +
                "selections: \t2\n" +
                "functionDef: \t1\n" +
                "other: \t\t\t2\n";
        assertEquals(expectedCount, matchedTypeCount.toString());
        String expected = String.format("%.2f", STRUCTURE_BASE * 8 / 9);
        String actual = String.format("%2.2f", matcher.grade());
        assertEquals(expected, actual);
    }
}