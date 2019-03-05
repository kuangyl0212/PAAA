package application.grade;

import application.util.graph.Exporter;
import graph.CDG;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static application.util.graph.Importer.importFrom;
import static application.util.graph.Exporter.exportCDGAsLispStyle;

class StructureExtractorTest {

    @Test
    void extractHeadTest() {
        CDG inputCDG = importFrom("(HEAD)");
        StructureExtractor extractor = new StructureExtractor(inputCDG);
        CDG outputCDG = extractor.get();
        assertEquals("(HEAD)", exportCDGAsLispStyle(outputCDG));
    }

    @Test
    void extractTest() {
        CDG inputCDG = importFrom("(HEAD " +
                "DECLARATION" +
                "FUNC_DEF)");
        String expected = "(HEAD NODE FUNC_DEF)";
        StructureExtractor extractor = new StructureExtractor(inputCDG);
        CDG outputCDG = extractor.get();
        assertEquals(expected, exportCDGAsLispStyle(outputCDG));
    }

    @Test
    void extractMoreTest() {
        CDG inputCDG = importFrom("(HEAD" +
                " DECLARATION" +
                " FUNC_DEF" +
                " DECLARATION)");
        String expected = "(HEAD NODE FUNC_DEF NODE)";
        StructureExtractor extractor = new StructureExtractor(inputCDG);
        CDG outputCDG = extractor.get();
        assertEquals(expected, exportCDGAsLispStyle(outputCDG));
    }

    @Test
    void extractMuchMoreTest() {
        CDG inputCDG = importFrom("(HEAD" +
                " DECLARATION" +
                " EXPR_STAT" +
                " (FUNC_DEF " +
                    "(BLOCK" +
                        " EXPR_STAT" +
                        " (SEL_STAT" +
                            " (SEL_CLAUSE EXPR_STAT))" +
                        " EXPR_STAT" +
                        " EXPR_STAT))" +
                " DECLARATION" +
                " EXPR_STAT)");
        String expected = "(HEAD" +
                " NODE" +
                " (FUNC_DEF" +
                    " (BLOCK" +
                        " NODE" +
                        " (SEL_STAT" +
                            " (SEL_CLAUSE NODE))" +
                        " NODE))" +
                " NODE)";
        StructureExtractor extractor = new StructureExtractor(inputCDG);
        CDG outputCDG = extractor.get();
        assertEquals(expected, exportCDGAsLispStyle(outputCDG));
    }
}