// Generated from GeneralTree.g4 by ANTLR 4.7.2
package application.util.graph.parse.generaltree;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GeneralTreeParser}.
 */
public interface GeneralTreeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GeneralTreeParser#tree}.
	 * @param ctx the parse tree
	 */
	void enterTree(GeneralTreeParser.TreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneralTreeParser#tree}.
	 * @param ctx the parse tree
	 */
	void exitTree(GeneralTreeParser.TreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneralTreeParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(GeneralTreeParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneralTreeParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(GeneralTreeParser.NodeContext ctx);
}