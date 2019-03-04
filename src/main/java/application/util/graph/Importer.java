package application.util.graph;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import application.util.graph.tree.parse.TreeBaseVisitor;
import application.util.graph.tree.parse.TreeLexer;
import application.util.graph.tree.parse.TreeParser;

import java.util.Stack;


class TreeVisitor extends TreeBaseVisitor<Integer> {
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

public class Importer {
    public static CDG importFrom(String formalString) {
        CharStream charStream = CharStreams.fromString(formalString);
        TreeLexer lexer = new TreeLexer(charStream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        TreeParser parser = new TreeParser(tokenStream);
        ParseTree parseTree = parser.tree();
        TreeVisitor visitor = new TreeVisitor();
        visitor.visit(parseTree);
        return visitor.getGraph();
    }
}
