package application.builder;

import application.builder.listeners.Python3ListenerEnhanced;
import application.config.Global;
import application.builder.listeners.CListenerEnhanced;
import graph.CDG;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import parser.c.CLexer;
import parser.c.CParser;
import parser.java.JavaLexer;
import parser.java.JavaParser;
import parser.python.Python3Lexer;
import parser.python.Python3Parser;

public class ParserAdministrator {
    private String inputString;
    private CharStream charStream;
    private CommonTokenStream tokenStream;
    private ParseTree parseTree;
    private Lexer lexer;
    private Parser parser;

    public ParserAdministrator(String inputString) {
        this.inputString = inputString;
        setAll();
    }

    public void reset(String inputString) {
        this.inputString = inputString;
        setAll();
    }



    private void setAll()  {
        setCharStream();
        setLexerAndParser();
        setParseTree();
    }

    private void setCharStream() {
        charStream = CharStreams.fromString(inputString);
    }

    private void setLexerAndParser() {
        switch (Global.LAN) {
            case C:
                setLexer(new CLexer(charStream));
                setTokenStream(new CommonTokenStream(lexer));
                setParser(new CParser(tokenStream));
                break;
            case JAVA:
                setLexer(new JavaLexer(charStream));
                setTokenStream(new CommonTokenStream(lexer));
                setParser(new JavaParser(tokenStream));
                break;
            case PYTHON:
                setLexer(new Python3Lexer(charStream));
                setTokenStream(new CommonTokenStream(lexer));
                setParser(new Python3Parser(tokenStream));
                break;
            default:
//                throw new Throwable("No such language");
        }
    }

    private void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    private void setTokenStream(CommonTokenStream tokenStream) {
        this.tokenStream = tokenStream;
    }

    private void setParser(Parser parser) {
        this.parser = parser;
    }

    private void setParseTree() {
        switch (Global.LAN) {
            case C:
                parseTree = ((CParser)parser).compilationUnit();
                break;
            case JAVA:
                parseTree = ((JavaParser)parser).compilationUnit();
                break;
            case PYTHON:
                parseTree = ((Python3Parser)parser).file_input();
                break;
            default:
//                throw new Throwable();
        }
    }

    public ParseTree getParseTree() {
        return parseTree;
    }

    public ParseTreeListener getListener(CDG emptyCDG) {
        switch (Global.LAN) {
            case C:
                return new CListenerEnhanced(emptyCDG);
            case JAVA:
            case PYTHON:
                return new Python3ListenerEnhanced(emptyCDG);
        }
        // TODO throw some expectation here
        return null;
    }
}
