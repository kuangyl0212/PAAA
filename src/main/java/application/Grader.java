package application;

import graph.CDG;
import listeners.CListenerEnhanced;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import util.graph.Exporter;

public class Grader {
    public static void main(String[] args) {
        String inputString = "void main() {}";
        ParserAdministrator administrator;
        CDG cdg = new CDG();
        CListenerEnhanced listenerEnhanced = new CListenerEnhanced(cdg);
        try {
            administrator = new ParserAdministrator(inputString);
            ParseTree parseTree = administrator.getParseTree();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listenerEnhanced, parseTree);
            String dotString = Exporter.exportAsDot(cdg);
            System.out.println(dotString);
        }
        catch (Throwable e) {

        }

    }
}
