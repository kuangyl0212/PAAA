package util.graph;

import graph.CDG;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import util.graph.tree.parse.TreeBaseVisitor;
import util.graph.tree.parse.TreeLexer;
import util.graph.tree.parse.TreeParser;

import java.util.Stack;

class SimpleGraph extends DefaultDirectedGraph<String, DefaultEdge> {
    SimpleGraph() {
        super(DefaultEdge.class);
    }
}


class TreeVisitor extends TreeBaseVisitor<Integer> {
    private Stack<String> vertexStack;
    private SimpleGraph graph;

    TreeVisitor() {
        super();
        vertexStack = new Stack<>();
        graph = new SimpleGraph();
    }

    public SimpleGraph getGraph() {
        return graph;
    }

    @Override public Integer visitNode(TreeParser.NodeContext ctx) {
        String node = ctx.getText();
        graph.addVertex(node);
        graph.addEdge(vertexStack.peek(), node);
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

        String head = ctx.getChild(1).getText();
        graph.addVertex(head);
        if (!vertexStack.empty()) {
            graph.addEdge(vertexStack.peek(), head);
        }
        vertexStack.push(head);
    }
    private void visitRest(TreeParser.TreeContext ctx) {
        int nNode = ctx.getChildCount();
        for (int i = 2; i < nNode - 1; i++) {
            visit(ctx.getChild(i));
        }
    }
}

public class Importer {
    public static SimpleGraph importFrom(String formalString) {
        CharStream charStream = CharStreams.fromString(formalString);
        TreeLexer lexer = new TreeLexer(charStream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        TreeParser parser = new TreeParser(tokenStream);
        ParseTree parseTree = parser.tree();
        TreeVisitor visitor = new TreeVisitor();
        visitor.visit(parseTree);
        SimpleGraph graph = visitor.getGraph();
        return graph;
    }
}
