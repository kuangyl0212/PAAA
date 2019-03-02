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

    private void resetTest() {
        String sourceCode = "";
        administrator = new ParserAdministrator(sourceCode);
        cdg = new CDG();
        listenerEnhanced = new CListenerEnhanced(cdg);
        walker = new ParseTreeWalker();
        buildBaseCDG();
    }

    private String getDotStringFrom(String sourceCode) {
        resetTest();
        administrator.reset(sourceCode);
        ParseTree parseTree = administrator.getParseTree();
        walker.walk(listenerEnhanced, parseTree);
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

        String expected = Exporter.exportCDGAsDot(expectedCDG);
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

        String expected = Exporter.exportCDGAsDot(expectedCDG);
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

        String expected = Exporter.exportCDGAsDot(expectedCDG);
        assertEquals(expected, actual);
    }

    @Test
    public void creatBlockItemListTest() {
        String actual = getDotStringFrom("void main() {int a;}");
        CDG expectedCDG = baseCdg;
        Vertex declaration = new Vertex(VertexType.DECLARATION);
        expectedCDG.addVertex(declaration);
        expectedCDG.addEdge(baseBlock, declaration);

        String expected = Exporter.exportCDGAsDot(expectedCDG);
        assertEquals(expected, actual);
    }

    @Test
    public void creatExpressionStatementTest() {
        String actual = getDotStringFrom("void main() {printf(\"Hello World\");}");
        CDG expectedCDG = baseCdg;
        Vertex exprStat = new Vertex(VertexType.EXPR_STAT);
        expectedCDG.addVertex(exprStat);
        expectedCDG.addEdge(baseBlock, exprStat);

        String expected = Exporter.exportCDGAsDot(expectedCDG);
        assertEquals(expected, actual);
    }

    @Test
    public void createIterationStatementTest() {
        String actual = getDotStringFrom("void main() {for(;;){}}");
        CDG expectedCDG = baseCdg;
        Vertex itrStat = new Vertex(VertexType.ITER_STAT);
        expectedCDG.addVertex(itrStat);
        expectedCDG.addEdge(baseBlock, itrStat);

        String expected = Exporter.exportCDGAsDot(expectedCDG);
        assertEquals(expected, actual);
    }

    @Test
    public void creatJumpStatementTest() {
        String actual = getDotStringFrom("void main() {return;}");
        CDG expectedCDG = baseCdg;
        Vertex jumpStat = new Vertex(VertexType.JUMP_STAT);
        expectedCDG.addVertex(jumpStat);
        expectedCDG.addEdge(baseBlock, jumpStat);

        String expected = Exporter.exportCDGAsDot(expectedCDG);
        assertEquals(expected, actual);
    }

    @Test
    public void creatSelectionStatementTest() {
        String actual = getDotStringFrom("void main() {if (a > 0) {}}");
        CDG expectedCDG = baseCdg;
        Vertex selStat = new Vertex(VertexType.SEL_STAT);
        expectedCDG.addVertex(selStat);
        expectedCDG.addEdge(baseBlock, selStat);

        String expected = Exporter.exportCDGAsDot(expectedCDG);
        assertEquals(expected, actual);
    }

    @Test
    public void creatSelectionBodyTest() {
        String actual = getDotStringFrom("void main() {if (a > 0) {a = 0;}}");
        CDG expectedCDG = baseCdg;
        Vertex selStat = new Vertex(VertexType.SEL_STAT);
        expectedCDG.addVertex(selStat);
        expectedCDG.addEdge(baseBlock, selStat);
        Vertex selBody = new Vertex(VertexType.BLOCK);
        expectedCDG.addVertex(selBody);
        expectedCDG.addEdge(selStat, selBody);
        Vertex exprStat = new Vertex(VertexType.EXPR_STAT);
        expectedCDG.addVertex(exprStat);
        expectedCDG.addEdge(selBody, exprStat);

        String expected = Exporter.exportCDGAsDot(expectedCDG);
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

        String expected = Exporter.exportCDGAsDot(expectedCDG);
        assertEquals(expected, actual);
    }
}
