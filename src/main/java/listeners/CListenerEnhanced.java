package listeners;

import java.util.Stack;
import graph.Vertex;
import graph.CDG;
import graph.VertexType;
import parser.c.CBaseListener;
import parser.c.CParser;

public class CListenerEnhanced extends CBaseListener {
    private Stack<Vertex> vertexStack;
    private CDG cdg;

    public CListenerEnhanced(CDG emptyCDG) {
        super();
        vertexStack = new Stack<>();
        cdg = emptyCDG;
    }

    @Override
    public void enterCompilationUnit(CParser.CompilationUnitContext ctx) {
        Vertex headVertex = new Vertex(VertexType.HEAD);
        cdg.addVertex(headVertex);
        vertexStack.push(headVertex);
    }

    @Override
    public void exitCompilationUnit(CParser.CompilationUnitContext ctx) {
        vertexStack.pop();
    }
}
