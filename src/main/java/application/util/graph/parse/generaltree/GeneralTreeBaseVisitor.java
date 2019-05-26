// Generated from GeneralTree.g4 by ANTLR 4.7.2
package application.util.graph.parse.generaltree;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link GeneralTreeVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class GeneralTreeBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements GeneralTreeVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitTree(GeneralTreeParser.TreeContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitNode(GeneralTreeParser.NodeContext ctx) { return visitChildren(ctx); }
}