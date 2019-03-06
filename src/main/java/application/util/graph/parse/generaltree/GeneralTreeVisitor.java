// Generated from GeneralTree.g4 by ANTLR 4.7.2
package application.util.graph.parse.generaltree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GeneralTreeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GeneralTreeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GeneralTreeParser#tree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTree(GeneralTreeParser.TreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GeneralTreeParser#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode(GeneralTreeParser.NodeContext ctx);
}