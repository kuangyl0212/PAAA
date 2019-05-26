package application.builder.listeners;

import application.builder.ParserAdministrator;
import application.config.DefaultConf;
import application.config.LANG;
import graph.CDG;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static application.util.graph.Exporter.exportCDGAsLispStyle;
import static application.util.file.Misc.readStringFrom;

class PythonListenerEnhancedTest {
    private ParserAdministrator administrator;
    private PythonListenerEnhanced listener;
    private ParseTreeWalker walker;
    private CDG cdg;

    PythonListenerEnhancedTest() {
        DefaultConf.setLANG(LANG.PYTHON);
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

    @Test
    void forStmtTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "for.py");
        String expected =
                "(HEAD " +
                    "(ITER_STAT " +
                        "(BLOCK EXPR_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void nestedLoopTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "nested_loop.py");
        String expected =
                "(HEAD " +
                    "(ITER_STAT " +
                        "(BLOCK " +
                            "(ITER_STAT (BLOCK EXPR_STAT)))))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void whileStmtTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "while.py");
        String expected =
                "(HEAD " +
                    "(ITER_STAT " +
                    "(BLOCK EXPR_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void breakTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "break.py");
        String expected =
                "(HEAD " +
                    "(ITER_STAT " +
                    "(BLOCK JUMP_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void continueTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "continue.py");
        String expected =
                "(HEAD " +
                    "(ITER_STAT " +
                    "(BLOCK JUMP_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void funDefTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "fun_def.py");
        String expected =
                "(HEAD " +
                    "(FUNC_DEF " +
                        "(BLOCK EXPR_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void returnTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "return.py");
        String expected =
                "(HEAD " +
                    "(FUNC_DEF " +
                        "(BLOCK JUMP_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void classDefTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "class_def.py");
        String expected =
                "(HEAD " +
                    "(CLASS_DEF " +
                        "(BLOCK EXPR_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void classDefFullTest() throws FileNotFoundException {
        String sourceCode = readStringFrom(sourceCodeRoot + "class_def_full.py");
        String expected =
                "(HEAD " +
                    "(CLASS_DEF " +
                        "(BLOCK " +
                            "EXPR_STAT " +
                            "(FUNC_DEF " +
                                "(BLOCK JUMP_STAT)))))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void realWorldPyTest1() throws FileNotFoundException {
        String sourceCode = readStringFrom("src/test/resources/py-source-a/006py_source_file1.py");
        String expected =
                "(HEAD " +
                    "(ITER_STAT " +
                        "(BLOCK " +
                            "(ITER_STAT " +
                                "(BLOCK EXPR_STAT)) " +
                            "(ITER_STAT " +
                                "(BLOCK EXPR_STAT)) " +
                            "EXPR_STAT)) " +
                    "(ITER_STAT " +
                        "(BLOCK " +
                            "(ITER_STAT " +
                                "(BLOCK EXPR_STAT)) " +
                            "(ITER_STAT " +
                                "(BLOCK EXPR_STAT)) " +
                        "EXPR_STAT)))";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }

    @Test
    void realWorldPyTest2() throws FileNotFoundException {
        String sourceCode = readStringFrom("src/test/resources/py-source-a/006py_source_file2.py");
        String expected =
                "(HEAD " +
                    "(FUNC_DEF " +
                        "(BLOCK " +
                            "(ITER_STAT " +
                                "(BLOCK " +
                                    "(ITER_STAT " +
                                        "(BLOCK " +
                                            "EXPR_STAT " +
                                            "EXPR_STAT " +
                                            "(SEL_STAT " +
                                                "(SEL_CLAUSE " +
                                                    "EXPR_STAT)))))))) " +
                    "EXPR_STAT)";
        assertEquals(expected, getLispStyleFrom(sourceCode));
    }
}