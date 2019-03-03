package application.builder;

import org.jgrapht.Graph;
import graph.CDG;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.io.File;

public class SDGBuilder implements GraphBuilder {

    @Override
    public DefaultDirectedGraph buildFrom(String sourceCode) {
        return null;
    }

    @Override
    public DefaultDirectedGraph buildFrom(File file) {
        return null;
    }

    public Graph buildFrom(CDG cdg) {
        return null;
    }
}
