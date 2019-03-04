// Generated from Tree.g4 by ANTLR 4.7.2
package util.graph.tree.parse;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TreeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'HEAD'", "'FUNC_DEF'", "'DECLARATION'", "'BLOCK'", 
			"'EXPR_STAT'", "'ITER_STAT'", "'JUMP_STAT'", "'SEL_STAT'", "'SEL_CLAUSE'", 
			"'STRUCT_SPEC'", "'STRUCT_DEC_LIST'", "'STRUCT_DEC'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "WS"
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


	public TreeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tree.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u00a5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\20\6\20\u00a0\n\20\r\20\16\20\u00a1\3"+
		"\20\3\20\2\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21\3\2\3\5\2\13\f\17\17\"\"\2\u00a5\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7%\3\2\2\2\t"+
		"*\3\2\2\2\13\63\3\2\2\2\r?\3\2\2\2\17E\3\2\2\2\21O\3\2\2\2\23Y\3\2\2\2"+
		"\25c\3\2\2\2\27l\3\2\2\2\31w\3\2\2\2\33\u0083\3\2\2\2\35\u0093\3\2\2\2"+
		"\37\u009f\3\2\2\2!\"\7*\2\2\"\4\3\2\2\2#$\7+\2\2$\6\3\2\2\2%&\7J\2\2&"+
		"\'\7G\2\2\'(\7C\2\2()\7F\2\2)\b\3\2\2\2*+\7H\2\2+,\7W\2\2,-\7P\2\2-.\7"+
		"E\2\2./\7a\2\2/\60\7F\2\2\60\61\7G\2\2\61\62\7H\2\2\62\n\3\2\2\2\63\64"+
		"\7F\2\2\64\65\7G\2\2\65\66\7E\2\2\66\67\7N\2\2\678\7C\2\289\7T\2\29:\7"+
		"C\2\2:;\7V\2\2;<\7K\2\2<=\7Q\2\2=>\7P\2\2>\f\3\2\2\2?@\7D\2\2@A\7N\2\2"+
		"AB\7Q\2\2BC\7E\2\2CD\7M\2\2D\16\3\2\2\2EF\7G\2\2FG\7Z\2\2GH\7R\2\2HI\7"+
		"T\2\2IJ\7a\2\2JK\7U\2\2KL\7V\2\2LM\7C\2\2MN\7V\2\2N\20\3\2\2\2OP\7K\2"+
		"\2PQ\7V\2\2QR\7G\2\2RS\7T\2\2ST\7a\2\2TU\7U\2\2UV\7V\2\2VW\7C\2\2WX\7"+
		"V\2\2X\22\3\2\2\2YZ\7L\2\2Z[\7W\2\2[\\\7O\2\2\\]\7R\2\2]^\7a\2\2^_\7U"+
		"\2\2_`\7V\2\2`a\7C\2\2ab\7V\2\2b\24\3\2\2\2cd\7U\2\2de\7G\2\2ef\7N\2\2"+
		"fg\7a\2\2gh\7U\2\2hi\7V\2\2ij\7C\2\2jk\7V\2\2k\26\3\2\2\2lm\7U\2\2mn\7"+
		"G\2\2no\7N\2\2op\7a\2\2pq\7E\2\2qr\7N\2\2rs\7C\2\2st\7W\2\2tu\7U\2\2u"+
		"v\7G\2\2v\30\3\2\2\2wx\7U\2\2xy\7V\2\2yz\7T\2\2z{\7W\2\2{|\7E\2\2|}\7"+
		"V\2\2}~\7a\2\2~\177\7U\2\2\177\u0080\7R\2\2\u0080\u0081\7G\2\2\u0081\u0082"+
		"\7E\2\2\u0082\32\3\2\2\2\u0083\u0084\7U\2\2\u0084\u0085\7V\2\2\u0085\u0086"+
		"\7T\2\2\u0086\u0087\7W\2\2\u0087\u0088\7E\2\2\u0088\u0089\7V\2\2\u0089"+
		"\u008a\7a\2\2\u008a\u008b\7F\2\2\u008b\u008c\7G\2\2\u008c\u008d\7E\2\2"+
		"\u008d\u008e\7a\2\2\u008e\u008f\7N\2\2\u008f\u0090\7K\2\2\u0090\u0091"+
		"\7U\2\2\u0091\u0092\7V\2\2\u0092\34\3\2\2\2\u0093\u0094\7U\2\2\u0094\u0095"+
		"\7V\2\2\u0095\u0096\7T\2\2\u0096\u0097\7W\2\2\u0097\u0098\7E\2\2\u0098"+
		"\u0099\7V\2\2\u0099\u009a\7a\2\2\u009a\u009b\7F\2\2\u009b\u009c\7G\2\2"+
		"\u009c\u009d\7E\2\2\u009d\36\3\2\2\2\u009e\u00a0\t\2\2\2\u009f\u009e\3"+
		"\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a4\b\20\2\2\u00a4 \3\2\2\2\4\2\u00a1\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}