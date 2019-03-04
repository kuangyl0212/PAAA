package application.grade;

import application.builder.CDGBuilder;
import graph.CDG;
import graph.Vertex;
import org.junit.jupiter.api.Test;

import static util.graph.Exporter.exportCDGAsDot;

import static org.junit.jupiter.api.Assertions.*;

class ScaleMatcherTest {

    @Test
    void VertexTypeCounterTest() {
        CDGBuilder cdgBuilder = new CDGBuilder();
        CDG cdg = cdgBuilder.buildFrom("void main() {}");
        Scale scale = new Scale(cdg);
        assertEquals(1, scale.functionDefinition);
    }

}