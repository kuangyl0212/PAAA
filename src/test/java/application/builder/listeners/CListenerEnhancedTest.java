package application.builder.listeners;

import application.builder.ParserAdministrator;
import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import application.util.graph.Exporter;

import static application.util.graph.Importer.importFrom;
import static application.util.graph.Exporter.exportCDGAsDot;

public class CListenerEnhancedTest {
    private ParserAdministrator administrator;
    private CDG cdg;
    private CDG baseCdg;
    private Vertex baseBlock;
    private CListenerEnhanced listenerEnhanced;
    private ParseTreeWalker  walker;

    private String getDotStringFrom(String sourceCode) {
        resetTest();
        administrator.reset(sourceCode);
        ParseTree parseTree = administrator.getParseTree();
        walker.walk(listenerEnhanced, parseTree);
        return getDotStringFrom(cdg);
    }

    private void resetTest() {
        String sourceCode = "";
        administrator = new ParserAdministrator(sourceCode);
        cdg = new CDG();
        listenerEnhanced = new CListenerEnhanced(cdg);
        walker = new ParseTreeWalker();
        buildBaseCDG();
    }

    private String getDotStringFrom(CDG cdg) {
        return Exporter.exportCDGAsDot(cdg);
    }

    private String getDotFrom(String formalString) {
        return exportCDGAsDot(importFrom(formalString));
    };

    /**
     *
     *  void main() {}
     */
    private void buildBaseCDG() {
        baseCdg = new CDG();
        Vertex head = new Vertex(VertexType.HEAD);
        baseCdg.addVertex(head);
        Vertex fun = new Vertex(VertexType.FUNC_DEF);
        baseCdg.addVertex(fun);
        baseCdg.addEdge(head, fun);
        Vertex block = new Vertex(VertexType.BLOCK);
        baseCdg.addVertex(block);
        baseCdg.addEdge(fun, block);
        baseBlock = block;
    }


    @Test
    public void creatHeadTest() {
        String sourceCode = "";
        String actual = getDotStringFrom(sourceCode);

        CDG expectedCDG = new CDG();
        expectedCDG.addVertex(VertexType.HEAD);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void creatFunctionDefinitionTest() {
        String actual = getDotStringFrom("void main() {}");
        String expected = getDotFrom("(HEAD FUNC_DEF)");;

        assertEquals(expected, actual);
    }

    @Test
    public void creatDeclarationTest() {
        String actual = getDotStringFrom("int a;");
        String expected = getDotFrom("(HEAD DECLARATION)");

        assertEquals(expected, actual);
    }

    @Test
    public void creatBlockItemListTest() {
        String actual = getDotStringFrom("void main() {int a;}");
        String expected =
                getDotFrom("(HEAD " +
                "(FUNC_DEF " +
                        "(BLOCK DECLARATION)))");

        assertEquals(expected, actual);
    }

    @Test
    public void creatExpressionStatementTest() {
        String actual = getDotStringFrom("void main() {printf(\"Hello World\");}");

        String expected = getDotFrom("(HEAD" +
                "(FUNC_DEF " +
                    "(BLOCK EXPR_STAT)))");

        assertEquals(expected, actual);
    }

    @Test
    public void createIterationStatementTest() {
        String actual = getDotStringFrom("void main() {for(;;){}}");

        String expected = getDotFrom("(HEAD " +
                "(FUNC_DEF " +
                "(BLOCK ITER_STAT)))");

        assertEquals(expected, actual);
    }

    @Test
    public void creatJumpStatementTest() {
        String actual = getDotStringFrom("void main() {return;}");

        String expected = getDotFrom("(HEAD " +
                "(FUNC_DEF " +
                "(BLOCK JUMP_STAT)))");

        assertEquals(expected, actual);
    }

    @Test
    public void creatSelectionStatementTest() {
        String actual = getDotStringFrom("void main() {if (a > 0) {}}");

        String expected = getDotFrom("(HEAD " +
                "(FUNC_DEF" +
                    "(BLOCK SEL_STAT)))");

        assertEquals(expected, actual);
    }

    @Test
    public void creatIfSelectionClauseTest() {
        String actual = getDotStringFrom("void main() {if (a > 0) {a = 0;}}");

        String expected = getDotFrom("(HEAD" +
                "(FUNC_DEF" +
                "(BLOCK " +
                "   (SEL_STAT" +
                "       (SEL_CLAUSE EXPR_STAT)))))");



        assertEquals(expected, actual);
    }

    @Test
    public void creatStructTest() {
        String sourceCode = "struct tree {int a;};";
        String actual = getDotStringFrom(sourceCode);

        String expected = getDotFrom("(HEAD" +
                "(DECLARATION " +
                "   (STRUCT_SPEC" +
                "       (STRUCT_DEC_LIST STRUCT_DEC))))");

        assertEquals(expected, actual);
    }

    @Test
    public void createSwitchCaseTest() {
        String sourceCode = "void main() {switch (a) {case 1: a=0;}}";
        String actual = getDotStringFrom(sourceCode);

        String expected = getDotFrom("(HEAD" +
                "(FUNC_DEF " +
                "   (BLOCK " +
                "       (SEL_STAT " +
                "           (SEL_CLAUSE EXPR_STAT)))))");

        assertEquals(expected, actual);
    }

    @Test
    public void createIfElseIfElseTest() {
        String sourceCode =
                "void main() {" +
                    "if (a>0) {" +
                        "a = 1;" +
                    "} " +
                    "else if (a<0) {" +
                        "a = -1;" +
                    "} " +
                    "else {" +
                        "a = 0;" +
                    "}" +
                "}";
        String actual = getDotStringFrom(sourceCode);

        CDG expectedCDG = baseCdg;
        Vertex selStat = new Vertex(VertexType.SEL_STAT);
        expectedCDG.addVertex(selStat);
        expectedCDG.addEdge(baseBlock, selStat);
        Vertex ifClause = new Vertex(VertexType.SEL_CLAUSE);
        expectedCDG.addVertex(ifClause);
        expectedCDG.addEdge(selStat, ifClause);
        Vertex exprStat = new Vertex(VertexType.EXPR_STAT);
        expectedCDG.addVertex(exprStat);
        expectedCDG.addEdge(ifClause, exprStat);
        Vertex elseIfSel = new Vertex(VertexType.SEL_STAT);
        expectedCDG.addVertex(elseIfSel);
        expectedCDG.addEdge(selStat, elseIfSel);
        Vertex elseIfClause = new Vertex(VertexType.SEL_CLAUSE);
        expectedCDG.addVertex(elseIfClause);
        expectedCDG.addEdge(elseIfSel, elseIfClause);
        Vertex elseIfExpr = new Vertex(VertexType.EXPR_STAT);
        expectedCDG.addVertex(elseIfExpr);
        expectedCDG.addEdge(elseIfClause, elseIfExpr);
        Vertex elseClause = new Vertex(VertexType.SEL_CLAUSE);
        expectedCDG.addVertex(elseClause);
        expectedCDG.addEdge(elseIfSel, elseClause);
        Vertex elseExpr = new Vertex(VertexType.EXPR_STAT);
        expectedCDG.addVertex(elseExpr);
        expectedCDG.addEdge(elseClause, elseExpr);
        String expected = getDotFrom("(HEAD" +
                "(FUNC_DEF" +
                "   (BLOCK" +
                "       (SEL_STAT" +
                "           (SEL_CLAUSE EXPR_STAT)" +
                "           (SEL_STAT" +
                "               (SEL_CLAUSE EXPR_STAT)" +
                "               (SEL_CLAUSE EXPR_STAT))))))");

        assertEquals(expected, actual);
    }
}