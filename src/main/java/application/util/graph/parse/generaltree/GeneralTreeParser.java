// Generated from GeneralTree.g4 by ANTLR 4.7.2
package application.util.graph.parse.generaltree;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GeneralTreeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, NODE=3, WS=4;
	public static final int
		RULE_tree = 0, RULE_node = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"tree", "node"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "NODE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GeneralTree.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GeneralTreeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TreeContext extends ParserRuleContext {
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public List<TreeContext> tree() {
			return getRuleContexts(TreeContext.class);
		}
		public TreeContext tree(int i) {
			return getRuleContext(TreeContext.class,i);
		}
		public TreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneralTreeListener ) ((GeneralTreeListener)listener).enterTree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneralTreeListener ) ((GeneralTreeListener)listener).exitTree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneralTreeVisitor ) return ((GeneralTreeVisitor<? extends T>)visitor).visitTree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TreeContext tree() throws RecognitionException {
		TreeContext _localctx = new TreeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tree);
		int _la;
		try {
			setState(17);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(4);
				match(T__0);
				setState(5);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(6);
				match(T__0);
				setState(7);
				node();
				setState(12);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0 || _la==NODE) {
					{
					setState(10);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case NODE:
						{
						setState(8);
						node();
						}
						break;
					case T__0:
						{
						setState(9);
						tree();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(14);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(15);
				match(T__1);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeContext extends ParserRuleContext {
		public TerminalNode NODE() { return getToken(GeneralTreeParser.NODE, 0); }
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneralTreeListener ) ((GeneralTreeListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneralTreeListener ) ((GeneralTreeListener)listener).exitNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneralTreeVisitor ) return ((GeneralTreeVisitor<? extends T>)visitor).visitNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_node);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			match(NODE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\6\30\4\2\t\2\4\3"+
		"\t\3\3\2\3\2\3\2\3\2\3\2\3\2\7\2\r\n\2\f\2\16\2\20\13\2\3\2\3\2\5\2\24"+
		"\n\2\3\3\3\3\3\3\2\2\4\2\4\2\2\2\30\2\23\3\2\2\2\4\25\3\2\2\2\6\7\7\3"+
		"\2\2\7\24\7\4\2\2\b\t\7\3\2\2\t\16\5\4\3\2\n\r\5\4\3\2\13\r\5\2\2\2\f"+
		"\n\3\2\2\2\f\13\3\2\2\2\r\20\3\2\2\2\16\f\3\2\2\2\16\17\3\2\2\2\17\21"+
		"\3\2\2\2\20\16\3\2\2\2\21\22\7\4\2\2\22\24\3\2\2\2\23\6\3\2\2\2\23\b\3"+
		"\2\2\2\24\3\3\2\2\2\25\26\7\5\2\2\26\5\3\2\2\2\5\f\16\23";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}