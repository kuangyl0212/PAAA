package application.builder;

import application.config.DefaultConf;
import application.config.LANG;
import graph.Vertex;
import graph.VertexType;
import org.junit.jupiter.api.Test;
import graph.CDG;
import static application.util.graph.Exporter.exportCDGAsDot;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CDGBuilderTest {

    private CDGBuilder builder;
    private CDG sampleCDG;

    CDGBuilderTest() {
        DefaultConf.setLANG(LANG.C);
        builder = new CDGBuilder();
        sampleCDG = buildSampleCDG();
    }

    CDG buildSampleCDG() {
        CDG expectedCDG = new CDG();
        Vertex head = new Vertex(VertexType.HEAD);
        expectedCDG.addVertex(head);
        Vertex fun = new Vertex(VertexType.FUNC_DEF);
        expectedCDG.addVertex(fun);
        expectedCDG.addEdge(head, fun);
        return expectedCDG;
    }

    @Test
    void buildFromEmptySourceCodeTest() {
        String sourceCode = "";
        CDG actualCDG = builder.buildFrom(sourceCode);
        CDG expectedCDG = new CDG();
        expectedCDG.addVertex(VertexType.HEAD);

        String actual = exportCDGAsDot(actualCDG);
        String expect = exportCDGAsDot(expectedCDG);

        assertEquals(expect, actual);
    }

    @Test
    void buildFromSourceCodeTest() {
        CDG expectedCDG = sampleCDG;

        String sourceCode = "void main() {}";
        CDG actualCdg = builder.buildFrom(sourceCode);

        String expected = exportCDGAsDot(expectedCDG);
        String actual = exportCDGAsDot(actualCdg);

        assertEquals(expected, actual);
    }

    @Test
    void buildFromFileTest() {

        File file = new File("src/test/resources/c/testBuildCDGFromFile.c");

        try {
            CDG actualCDG = builder.buildFrom(file);
            CDG expectedCDG = sampleCDG;

            String actual = exportCDGAsDot(actualCDG);
            String expected = exportCDGAsDot(expectedCDG);

            assertEquals(expected, actual);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}