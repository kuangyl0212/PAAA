package application;

import graph.CDG;
import org.jgrapht.Graph;

public class CDGBuilder extends GraphBuilder {
    @Override
    public Graph build() {
        return new CDG();
    }
}
