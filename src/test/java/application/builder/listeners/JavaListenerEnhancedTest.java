package application.builder.listeners;

import application.builder.ParserAdministrator;
import application.util.graph.Exporter;
import graph.CDG;
import graph.Vertex;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

public class JavaListenerEnhancedTest {

    private ParserAdministrator administrator;
    private CDG cdg;
    private CDG baseCdg;
    private Vertex baseBlock;
    private CListenerEnhanced listenerEnhanced;
    private ParseTreeWalker  walker;
    @Test
    public void printCDG() {
        String sourceCode =
                "public class CalculatorTest {\n" +
                "    void addTwoNumbers() {\n" +
                "        int a;" +
                        "int b;" +
                        "int c;" +
                "        a = 1;" +
                "    }\n" +
                "\n" +
                "}\n";
        administrator = new ParserAdministrator(sourceCode);
        cdg = new CDG();
        listenerEnhanced = new CListenerEnhanced(cdg);
        walker = new ParseTreeWalker();
        administrator.reset(sourceCode);
        ParseTree parseTree = administrator.getParseTree();
        walker.walk(listenerEnhanced, parseTree);
        System.out.println(Exporter.exportCDGAsDot(cdg));
    }

}
