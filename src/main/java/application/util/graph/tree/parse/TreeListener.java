// Generated from Tree.g4 by ANTLR 4.7.2
package application.util.graph.tree.parse;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TreeParser}.
 */
public interface TreeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TreeParser#tree}.
	 * @param ctx the parse tree
	 */
	void enterTree(TreeParser.TreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreeParser#tree}.
	 * @param ctx the parse tree
	 */
	void exitTree(TreeParser.TreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TreeParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(TreeParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TreeParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(TreeParser.NodeContext ctx);
}