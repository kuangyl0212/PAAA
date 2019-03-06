package application.builder;

import graph.CDG;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.File;

public class CDGBuilder implements GraphBuilder {

    @Override
    public CDG buildFrom(String sourceCode) {
        CDG cdg = new CDG();
        ParserAdministrator administrator = new ParserAdministrator(sourceCode);
        ParseTree parseTree = administrator.getParseTree();
        ParseTreeWalker walker = new ParseTreeWalker();
        ParseTreeListener listener = administrator.getListener(cdg);
        walker.walk(listener, parseTree);
        return cdg;

    }

    @Override
    public CDG buildFrom(File file) throws FileNotFoundException {
        String sourceCode = readStringFrom(file);
        return buildFrom(sourceCode);
    }

    private String readStringFrom(File file) throws FileNotFoundException {
        /* some magic refer to
          https://stackoverflow.com/questions/3402735/what-is-simplest-way-to-read-a-file-into-string */

        return new Scanner(file).useDelimiter("\\Z").next();
    }
}
