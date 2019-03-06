package application.util.graph;

import application.util.graph.parse.generaltree.GeneralTreeBaseVisitor;
import application.util.graph.parse.generaltree.GeneralTreeParser;

import java.util.Stack;

public class GeneralTreeVisitor extends GeneralTreeBaseVisitor {
    private Stack<String> vertexStack;
    private SimpleGraph graph;

    GeneralTreeVisitor() {
        super();
        vertexStack = new Stack<>();
        graph = new SimpleGraph();
    }

    public SimpleGraph getGraph() {
        return graph;
    }

    @Override
    public Integer visitNode(GeneralTreeParser.NodeContext ctx) {
        String label = ctx.getText();

        graph.addVertex(label);
        graph.addEdge(vertexStack.peek(), label);

        return 0;
    }

    @Override
    public Integer visitTree(GeneralTreeParser.TreeContext ctx) {
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
    private boolean emptyTree(GeneralTreeParser.TreeContext ctx) {
        return ctx.getChildCount() < 3;
    }
    private void visitHeadAndPushStack(GeneralTreeParser.TreeContext ctx) {

        String label = ctx.getChild(1).getText();

            graph.addVertex(label);
            if (!vertexStack.empty()) {
                graph.addEdge(vertexStack.peek(), label);
            }
            vertexStack.push(label);

    }
    private void visitRest(GeneralTreeParser.TreeContext ctx) {
        int nNode = ctx.getChildCount();
        for (int i = 2; i < nNode - 1; i++) {
            visit(ctx.getChild(i));
        }
    }
}
