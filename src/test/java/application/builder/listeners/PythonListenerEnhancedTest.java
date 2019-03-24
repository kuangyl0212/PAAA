package application.builder.listeners;

import application.builder.ParserAdministrator;
import application.config.Global;
import application.config.LANG;
import graph.CDG;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static application.util.graph.Exporter.exportCDGAsLispStyle;

class PythonListenerEnhancedTest {
    private ParserAdministrator administrator;
    private PythonListenerEnhanced listener;
    private ParseTreeWalker walker;
    private CDG cdg;

    PythonListenerEnhancedTest() {
        Global.setLAN(LANG.PYTHON);
    }

    private void initTest(String sourceCode) {
        administrator = new ParserAdministrator(sourceCode);
        cdg = new CDG();
        listener = new PythonListenerEnhanced(cdg);
        walker = new ParseTreeWalker();
    }

    private String getLispStyleFrom(String sourceCode) {
        initTest(sourceCode);
        ParseTree parseTree = administrator.getParseTree();
        walker.walk(listener, parseTree);
        return getLispStyleFrom(cdg);
    }

    private String getLispStyleFrom(CDG cdg) {
        return exportCDGAsLispStyle(cdg);
    }

    private String readStringFrom(String filePath) throws FileNotFoundException {
        return new Scanner(new File(filePath)).useDelimiter("\\Z").next();
    }

    private static String sourceCodeRoot = "src/test/resources/pythonCode/";

    @Test
    void createHead() {
        String lispStyle = getLispStyleFrom("");
        assertEquals("(HEAD)", lispStyle);
    }

    @Test
    void helloWorldTest() {
        String lispStyle = getLispStyleFrom("print('Hello, world!')\n");
        assertEquals("(HEAD EXPR_STAT)", lispStyle);
    }

    @Test
    void exprStmtTest() {
        String lispStyle = getLispStyleFrom("a = 1\n");
        assertEquals("(HEAD EXPR_STAT)", lispStyle);
    }

    @Test
    void ifStmtTest() {
        String lispStyle = getLispStyleFrom("if a > 0:\ta = 1\n");
        String expected =
                "(HEAD " +
                    "(SEL_STAT " +
                        "(SEL_CLAUSE EXPR_STAT)))";
        assertEquals(expected, lispStyle);
    }

    @Test
    void ifStmtMoreTest() {
        String lispStyle = getLispStyleFrom("if a > 0:\n\ta = 1\n\tb=1\n");
        String expected =
                "(HEAD " +
                    "(SEL_STAT " +
                        "(SEL_CLAUSE EXPR_STAT EXPR_STAT)))";
        assertEquals(expected, lispStyle);
    }

    @Test
    void readFromFileTest() throws FileNotFoundException {
        String sourceCode = readStringFrom("src/test/resources/pythonCode/if_stmt.py");
        String expected =
                "(HEAD " +
                    "(SEL_STAT " +
                        "(SEL_CLAUSE EXPR_STAT EXPR_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void ifElseTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "if_else.py");
        String expected =
                "(HEAD " +
                    "(SEL_STAT " +
                        "(SEL_CLAUSE EXPR_STAT) " +
                        "(SEL_CLAUSE EXPR_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void ifElseifElseTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "if_elif_else.py");
        String expected =
                "(HEAD " +
                    "(SEL_STAT " +
                    "(SEL_CLAUSE EXPR_STAT) " +
                    "(SEL_CLAUSE EXPR_STAT) " +
                    "(SEL_CLAUSE EXPR_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void nestedIfElseTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "nested_if_else.py");
        String expected =
                "(HEAD " +
                    "(SEL_STAT " +
                        "(SEL_CLAUSE " +
                            "(SEL_STAT " +
                                "(SEL_CLAUSE EXPR_STAT) " +
                                "(SEL_CLAUSE EXPR_STAT))) " +
                        "(SEL_CLAUSE EXPR_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }
}