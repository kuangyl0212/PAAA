package application.util.graph;

import application.util.graph.parse.generaltree.GeneralTreeLexer;
import application.util.graph.parse.generaltree.GeneralTreeParser;
import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import application.util.graph.parse.tree.TreeBaseVisitor;
import application.util.graph.parse.tree.TreeLexer;
import application.util.graph.parse.tree.TreeParser;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Stack;


public class Importer {

    public static CDG importCDGFrom(String lispStyle) {
        TreeVisitor visitor = new TreeVisitor();
        visitor.visit(fromStringToParseTree(lispStyle));
        return visitor.getGraph();
    }
    private static ParseTree fromStringToParseTree(String string) {
        CharStream charStream = CharStreams.fromString(string);
        TreeLexer lexer = new TreeLexer(charStream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        TreeParser parser = new TreeParser(tokenStream);
        return parser.tree();
    }

    public static SimpleGraph importSimpleGraphFrom(String formalString) {
        GeneralTreeVisitor visitor = new GeneralTreeVisitor();
        visitor.visit(getGeneralTreeParserFrom(formalString));
        return visitor.getGraph();
    }

    private static ParseTree getGeneralTreeParserFrom(String formalString) {
        CharStream charStream = CharStreams.fromString(formalString);
        GeneralTreeLexer lexer = new GeneralTreeLexer(charStream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        GeneralTreeParser parser = new GeneralTreeParser(tokenStream);
        return parser.tree();
    }
}
