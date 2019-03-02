package application;

import config.Global;
import listeners.CListenerEnhanced;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.c.CLexer;
import parser.c.CListener;
import parser.c.CParser;
import parser.c.CBaseListener;
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

    public ParserAdministrator(String inputString) throws Throwable {
        this.inputString = inputString;
        setAll();
    }



    private void setAll() throws Throwable {
        setCharStream();
        setLexerAndParser();
        setParseTree();
    }

    private void setCharStream() {
        charStream = CharStreams.fromString(inputString);
    }

    private void setLexerAndParser() throws Throwable {
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
                throw new Throwable("No such language");
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

    private void setParseTree() throws Throwable {
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
                throw new Throwable();
        }
    }

    public ParseTree getParseTree() {
        return parseTree;
    }
}
