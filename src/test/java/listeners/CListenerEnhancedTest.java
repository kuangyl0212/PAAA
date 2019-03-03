package listeners;

import application.ParserAdministrator;
import graph.CDG;
import graph.Vertex;
import graph.VertexType;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import util.graph.Exporter;

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
        String sourceCode = "void main() {}";
        String actual = getDotStringFrom(sourceCode);

        CDG expectedCDG = new CDG();
        Vertex head = new Vertex(VertexType.HEAD);
        Vertex fun = new Vertex(VertexType.FUNC_DEF);
        expectedCDG.addVertex(head);
        expectedCDG.addVertex(fun);
        expectedCDG.addEdge(head, fun);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void creatDeclarationTest() {
        String actual = getDotStringFrom("int a;");

        CDG expectedCDG = new CDG();
        Vertex head = new Vertex(VertexType.HEAD);
        Vertex declaration = new Vertex(VertexType.DECLARATION);
        expectedCDG.addVertex(head);
        expectedCDG.addVertex(declaration);
        expectedCDG.addEdge(head, declaration);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void creatBlockItemListTest() {
        String actual = getDotStringFrom("void main() {int a;}");

        CDG expectedCDG = baseCdg;
        Vertex declaration = new Vertex(VertexType.DECLARATION);
        expectedCDG.addVertex(declaration);
        expectedCDG.addEdge(baseBlock, declaration);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void creatExpressionStatementTest() {
        String actual = getDotStringFrom("void main() {printf(\"Hello World\");}");

        CDG expectedCDG = baseCdg;
        Vertex exprStat = new Vertex(VertexType.EXPR_STAT);
        expectedCDG.addVertex(exprStat);
        expectedCDG.addEdge(baseBlock, exprStat);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void createIterationStatementTest() {
        String actual = getDotStringFrom("void main() {for(;;){}}");

        CDG expectedCDG = baseCdg;
        Vertex itrStat = new Vertex(VertexType.ITER_STAT);
        expectedCDG.addVertex(itrStat);
        expectedCDG.addEdge(baseBlock, itrStat);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void creatJumpStatementTest() {
        String actual = getDotStringFrom("void main() {return;}");

        CDG expectedCDG = baseCdg;
        Vertex jumpStat = new Vertex(VertexType.JUMP_STAT);
        expectedCDG.addVertex(jumpStat);
        expectedCDG.addEdge(baseBlock, jumpStat);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void creatSelectionStatementTest() {
        String actual = getDotStringFrom("void main() {if (a > 0) {}}");

        CDG expectedCDG = baseCdg;
        Vertex selStat = new Vertex(VertexType.SEL_STAT);
        expectedCDG.addVertex(selStat);
        expectedCDG.addEdge(baseBlock, selStat);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void creatIfSelectionClauseTest() {
        String actual = getDotStringFrom("void main() {if (a > 0) {a = 0;}}");

        CDG expectedCDG = baseCdg;
        Vertex selStat = new Vertex(VertexType.SEL_STAT);
        expectedCDG.addVertex(selStat);
        expectedCDG.addEdge(baseBlock, selStat);
        Vertex selClause = new Vertex(VertexType.SEL_CLAUSE);
        expectedCDG.addVertex(selClause);
        expectedCDG.addEdge(selStat, selClause);
        Vertex exprStat = new Vertex(VertexType.EXPR_STAT);
        expectedCDG.addVertex(exprStat);
        expectedCDG.addEdge(selClause, exprStat);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void creatStructTest() {
        String sourceCode = "struct tree {int a;};";
        String actual = getDotStringFrom(sourceCode);

        CDG expectedCDG = new CDG();
        Vertex head = new Vertex(VertexType.HEAD);
        Vertex declaration = new Vertex(VertexType.DECLARATION);
        expectedCDG.addVertex(head);
        expectedCDG.addVertex(declaration);
        expectedCDG.addEdge(head, declaration);
        Vertex struct = new Vertex(VertexType.STRUCT_SPEC);
        expectedCDG.addVertex(struct);
        expectedCDG.addEdge(declaration, struct);
        Vertex structDecList = new Vertex(VertexType.STRUCT_DEC_LIST);
        expectedCDG.addVertex(structDecList);
        expectedCDG.addEdge(struct, structDecList);
        Vertex structDec = new Vertex(VertexType.STRUCT_DEC);
        expectedCDG.addVertex(structDec);
        expectedCDG.addEdge(structDecList, structDec);
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);
    }

    @Test
    public void createSwitchCaseTest() {
        String sourceCode = "void main() {switch (a) {case 1: a=0;}}";
        String actual = getDotStringFrom(sourceCode);

        CDG expectedCDG = baseCdg;
        Vertex selStat = new Vertex(VertexType.SEL_STAT);
        expectedCDG.addVertex(selStat);
        expectedCDG.addEdge(baseBlock, selStat);
        Vertex selClause = new Vertex(VertexType.SEL_CLAUSE);
        expectedCDG.addVertex(selClause);
        expectedCDG.addEdge(selStat, selClause);
        Vertex exprStat = new Vertex(VertexType.EXPR_STAT);
        expectedCDG.addVertex(exprStat);
        expectedCDG.addEdge(selClause, exprStat);
        String expected = getDotStringFrom(expectedCDG);

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
        String expected = getDotStringFrom(expectedCDG);

        assertEquals(expected, actual);

    }
}