package application.builder;

import org.jgrapht.graph.DefaultDirectedGraph;

import java.io.File;
import java.io.FileNotFoundException;

public interface GraphBuilder {
    DefaultDirectedGraph buildFrom(String sourceCode);
    DefaultDirectedGraph buildFrom(File file) throws FileNotFoundException;
}
