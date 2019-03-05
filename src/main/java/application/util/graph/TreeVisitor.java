package application.util.graph;

import application.util.graph.parse.tree.TreeBaseVisitor;
import application.util.graph.parse.tree.TreeParser;
import graph.CDG;
import graph.Vertex;
import graph.VertexType;

import java.util.Stack;

public class TreeVisitor extends TreeBaseVisitor<Integer> {
    private Stack<Vertex> vertexStack;
    private CDG graph;

    TreeVisitor() {
        super();
        vertexStack = new Stack<>();
        graph = new CDG();
    }

    public CDG getGraph() {
        return graph;
    }

    @Override public Integer visitNode(TreeParser.NodeContext ctx) {
        String label = ctx.getText();

        Vertex nexVertex = null;
        try {
            nexVertex = new Vertex(VertexType.getTypeFrom(label));
            graph.addVertex(nexVertex);
            graph.addEdge(vertexStack.peek(), nexVertex);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return 0;
    }

    @Override
    public Integer  visitTree(TreeParser.TreeContext ctx) {
        if (!emptyTree(ctx)) {
            visitHeadAndPushStack(ctx);
            visitRest(ctx);
            vertexStack.pop();
        }
        return 0;
    }

    /**
     *
     * example "()"
     */
    private boolean emptyTree(TreeParser.TreeContext ctx) {
        return ctx.getChildCount() < 3;
    }
    private void visitHeadAndPushStack(TreeParser.TreeContext ctx) {

        String label = ctx.getChild(1).getText();
        Vertex newVertex = null;
        try {
            newVertex = new Vertex(VertexType.getTypeFrom(label));
            graph.addVertex(newVertex);
            if (!vertexStack.empty()) {
                graph.addEdge(vertexStack.peek(), newVertex);
            }
            vertexStack.push(newVertex);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    private void visitRest(TreeParser.TreeContext ctx) {
        int nNode = ctx.getChildCount();
        for (int i = 2; i < nNode - 1; i++) {
            visit(ctx.getChild(i));
        }
    }
}
