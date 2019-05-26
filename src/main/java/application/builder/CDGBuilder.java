package application.builder;

import application.config.LANG;
import graph.CDG;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.File;

public class CDGBuilder {

    /**
     * 从源程序字符串建立程序依赖图
     * @param sourceCode 源程序字符串
     * @return sourceCoded对应的程序依赖图
     */
    @Deprecated
    public static CDG buildFrom(String sourceCode) {
        CDG cdg = new CDG();
        ParserAdministrator administrator = new ParserAdministrator(sourceCode);
        ParseTree parseTree = administrator.getParseTree();
        ParseTreeWalker walker = new ParseTreeWalker();
        ParseTreeListener listener = administrator.getListener(cdg);
        walker.walk(listener, parseTree);
        return cdg;

    }

    /**
     * Build CDG from a source code (of a specific language)
     * @param currentLanguage Current language of source code
     * @param sourceCode Source code
     * @return Built CDG
     */
    public static CDG buildFrom(LANG currentLanguage, String sourceCode) {
        CDG cdg = new CDG();
        ParserAdministrator administrator = new ParserAdministrator(currentLanguage,sourceCode);
        ParseTree parseTree = administrator.getParseTree();
        ParseTreeWalker walker = new ParseTreeWalker();
        ParseTreeListener listener = administrator.getListener(cdg);
        walker.walk(listener, parseTree);
        return cdg;
    }

    /**
     * 从源程序文件构建程序依赖图
     * @param file 源程序文件
     * @return file对应的程序依赖图
     * @throws FileNotFoundException 文件不存在时抛出异常
     */
    //@Override
    public static CDG buildFrom(File file) throws FileNotFoundException {
        String sourceCode = readStringFrom(file);
        return buildFrom(sourceCode);
    }

    private static String readStringFrom(File file) throws FileNotFoundException {
        /* some magic refer to
          https://stackoverflow.com/questions/3402735/what-is-simplest-way-to-read-a-file-into-string */

        return new Scanner(file).useDelimiter("\\Z").next();
    }
}
