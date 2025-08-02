// Generated from c:/workspace/gerador/src/main/java/br/com/gerador/grammar/ProjetoDSL.g4 by ANTLR 4.13.1

package br.com.gerador.grammar;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ProjetoDSLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, BOOLEAN=29, ID=30, INT=31, STRING=32, 
		WS=33, COMMENT=34;
	public static final int
		RULE_file = 0, RULE_packageDecl = 1, RULE_packageName = 2, RULE_entityDecl = 3, 
		RULE_crudConfig = 4, RULE_crudOptions = 5, RULE_fieldDecl = 6, RULE_type = 7, 
		RULE_option = 8, RULE_validation = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "packageDecl", "packageName", "entityDecl", "crudConfig", "crudOptions", 
			"fieldDecl", "type", "option", "validation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'package'", "'{'", "'}'", "'.'", "'entity'", "'crud'", "'generateRepository'", 
			"':'", "'generateService'", "'generateController'", "'generateDto'", 
			"'dddLayers'", "'string'", "'uuid'", "'integer'", "'long'", "'double'", 
			"'boolean'", "'date'", "'datetime'", "'decimal'", "'?'", "'min('", "','", 
			"')'", "'max('", "'notNull'", "'notBlank'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "BOOLEAN", "ID", "INT", "STRING", "WS", 
			"COMMENT"
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
	public String getGrammarFileName() { return "ProjetoDSL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ProjetoDSLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileContext extends ParserRuleContext {
		public List<PackageDeclContext> packageDecl() {
			return getRuleContexts(PackageDeclContext.class);
		}
		public PackageDeclContext packageDecl(int i) {
			return getRuleContext(PackageDeclContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				packageDecl();
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
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

	@SuppressWarnings("CheckReturnValue")
	public static class PackageDeclContext extends ParserRuleContext {
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public List<EntityDeclContext> entityDecl() {
			return getRuleContexts(EntityDeclContext.class);
		}
		public EntityDeclContext entityDecl(int i) {
			return getRuleContext(EntityDeclContext.class,i);
		}
		public List<CrudConfigContext> crudConfig() {
			return getRuleContexts(CrudConfigContext.class);
		}
		public CrudConfigContext crudConfig(int i) {
			return getRuleContext(CrudConfigContext.class,i);
		}
		public PackageDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDecl; }
	}

	public final PackageDeclContext packageDecl() throws RecognitionException {
		PackageDeclContext _localctx = new PackageDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_packageDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(T__0);
			setState(26);
			packageName();
			setState(27);
			match(T__1);
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(30);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
					{
					setState(28);
					entityDecl();
					}
					break;
				case T__5:
					{
					setState(29);
					crudConfig();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__4 || _la==T__5 );
			setState(34);
			match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PackageNameContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ProjetoDSLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ProjetoDSLParser.ID, i);
		}
		public PackageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageName; }
	}

	public final PackageNameContext packageName() throws RecognitionException {
		PackageNameContext _localctx = new PackageNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_packageName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(ID);
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(37);
				match(T__3);
				setState(38);
				match(ID);
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class EntityDeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ProjetoDSLParser.ID, 0); }
		public List<FieldDeclContext> fieldDecl() {
			return getRuleContexts(FieldDeclContext.class);
		}
		public FieldDeclContext fieldDecl(int i) {
			return getRuleContext(FieldDeclContext.class,i);
		}
		public EntityDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityDecl; }
	}

	public final EntityDeclContext entityDecl() throws RecognitionException {
		EntityDeclContext _localctx = new EntityDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_entityDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(T__4);
			setState(45);
			match(ID);
			setState(46);
			match(T__1);
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47);
				fieldDecl();
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(52);
			match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CrudConfigContext extends ParserRuleContext {
		public List<CrudOptionsContext> crudOptions() {
			return getRuleContexts(CrudOptionsContext.class);
		}
		public CrudOptionsContext crudOptions(int i) {
			return getRuleContext(CrudOptionsContext.class,i);
		}
		public CrudConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_crudConfig; }
	}

	public final CrudConfigContext crudConfig() throws RecognitionException {
		CrudConfigContext _localctx = new CrudConfigContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_crudConfig);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__5);
			setState(55);
			match(T__1);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7808L) != 0)) {
				{
				{
				setState(56);
				crudOptions();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CrudOptionsContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(ProjetoDSLParser.BOOLEAN, 0); }
		public CrudOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_crudOptions; }
	}

	public final CrudOptionsContext crudOptions() throws RecognitionException {
		CrudOptionsContext _localctx = new CrudOptionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_crudOptions);
		try {
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				match(T__6);
				setState(65);
				match(T__7);
				setState(66);
				match(BOOLEAN);
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				match(T__8);
				setState(68);
				match(T__7);
				setState(69);
				match(BOOLEAN);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				match(T__9);
				setState(71);
				match(T__7);
				setState(72);
				match(BOOLEAN);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				match(T__10);
				setState(74);
				match(T__7);
				setState(75);
				match(BOOLEAN);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(76);
				match(T__11);
				setState(77);
				match(T__7);
				setState(78);
				match(BOOLEAN);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FieldDeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ProjetoDSLParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public ValidationContext validation() {
			return getRuleContext(ValidationContext.class,0);
		}
		public FieldDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDecl; }
	}

	public final FieldDeclContext fieldDecl() throws RecognitionException {
		FieldDeclContext _localctx = new FieldDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fieldDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(ID);
			setState(82);
			match(T__7);
			setState(83);
			type();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(84);
				option();
				}
			}

			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 478150656L) != 0)) {
				{
				setState(87);
				validation();
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4186112L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class OptionContext extends ParserRuleContext {
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__21);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ValidationContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ProjetoDSLParser.INT, 0); }
		public TerminalNode STRING() { return getToken(ProjetoDSLParser.STRING, 0); }
		public ValidationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validation; }
	}

	public final ValidationContext validation() throws RecognitionException {
		ValidationContext _localctx = new ValidationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_validation);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__22:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				match(T__22);
				setState(95);
				match(INT);
				setState(96);
				match(T__23);
				setState(97);
				match(STRING);
				setState(98);
				match(T__24);
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(T__25);
				setState(100);
				match(INT);
				setState(101);
				match(T__24);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				match(T__26);
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 4);
				{
				setState(103);
				match(T__27);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\u0004\u0001\"k\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0001\u0000\u0004\u0000\u0016\b\u0000\u000b"+
		"\u0000\f\u0000\u0017\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0004\u0001\u001f\b\u0001\u000b\u0001\f\u0001 \u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002(\b\u0002\n\u0002"+
		"\f\u0002+\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004"+
		"\u00031\b\u0003\u000b\u0003\f\u00032\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004:\b\u0004\n\u0004\f\u0004=\t\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"P\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"V\b\u0006\u0001\u0006\u0003\u0006Y\b\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\ti\b\t\u0001\t\u0000\u0000\n\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0000\u0001\u0001\u0000\r\u0015o\u0000"+
		"\u0015\u0001\u0000\u0000\u0000\u0002\u0019\u0001\u0000\u0000\u0000\u0004"+
		"$\u0001\u0000\u0000\u0000\u0006,\u0001\u0000\u0000\u0000\b6\u0001\u0000"+
		"\u0000\u0000\nO\u0001\u0000\u0000\u0000\fQ\u0001\u0000\u0000\u0000\u000e"+
		"Z\u0001\u0000\u0000\u0000\u0010\\\u0001\u0000\u0000\u0000\u0012h\u0001"+
		"\u0000\u0000\u0000\u0014\u0016\u0003\u0002\u0001\u0000\u0015\u0014\u0001"+
		"\u0000\u0000\u0000\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0015\u0001"+
		"\u0000\u0000\u0000\u0017\u0018\u0001\u0000\u0000\u0000\u0018\u0001\u0001"+
		"\u0000\u0000\u0000\u0019\u001a\u0005\u0001\u0000\u0000\u001a\u001b\u0003"+
		"\u0004\u0002\u0000\u001b\u001e\u0005\u0002\u0000\u0000\u001c\u001f\u0003"+
		"\u0006\u0003\u0000\u001d\u001f\u0003\b\u0004\u0000\u001e\u001c\u0001\u0000"+
		"\u0000\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f \u0001\u0000\u0000"+
		"\u0000 \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!\"\u0001"+
		"\u0000\u0000\u0000\"#\u0005\u0003\u0000\u0000#\u0003\u0001\u0000\u0000"+
		"\u0000$)\u0005\u001e\u0000\u0000%&\u0005\u0004\u0000\u0000&(\u0005\u001e"+
		"\u0000\u0000\'%\u0001\u0000\u0000\u0000(+\u0001\u0000\u0000\u0000)\'\u0001"+
		"\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*\u0005\u0001\u0000\u0000"+
		"\u0000+)\u0001\u0000\u0000\u0000,-\u0005\u0005\u0000\u0000-.\u0005\u001e"+
		"\u0000\u0000.0\u0005\u0002\u0000\u0000/1\u0003\f\u0006\u00000/\u0001\u0000"+
		"\u0000\u000012\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u000023\u0001"+
		"\u0000\u0000\u000034\u0001\u0000\u0000\u000045\u0005\u0003\u0000\u0000"+
		"5\u0007\u0001\u0000\u0000\u000067\u0005\u0006\u0000\u00007;\u0005\u0002"+
		"\u0000\u00008:\u0003\n\u0005\u000098\u0001\u0000\u0000\u0000:=\u0001\u0000"+
		"\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<>\u0001"+
		"\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>?\u0005\u0003\u0000\u0000"+
		"?\t\u0001\u0000\u0000\u0000@A\u0005\u0007\u0000\u0000AB\u0005\b\u0000"+
		"\u0000BP\u0005\u001d\u0000\u0000CD\u0005\t\u0000\u0000DE\u0005\b\u0000"+
		"\u0000EP\u0005\u001d\u0000\u0000FG\u0005\n\u0000\u0000GH\u0005\b\u0000"+
		"\u0000HP\u0005\u001d\u0000\u0000IJ\u0005\u000b\u0000\u0000JK\u0005\b\u0000"+
		"\u0000KP\u0005\u001d\u0000\u0000LM\u0005\f\u0000\u0000MN\u0005\b\u0000"+
		"\u0000NP\u0005\u001d\u0000\u0000O@\u0001\u0000\u0000\u0000OC\u0001\u0000"+
		"\u0000\u0000OF\u0001\u0000\u0000\u0000OI\u0001\u0000\u0000\u0000OL\u0001"+
		"\u0000\u0000\u0000P\u000b\u0001\u0000\u0000\u0000QR\u0005\u001e\u0000"+
		"\u0000RS\u0005\b\u0000\u0000SU\u0003\u000e\u0007\u0000TV\u0003\u0010\b"+
		"\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VX\u0001\u0000"+
		"\u0000\u0000WY\u0003\u0012\t\u0000XW\u0001\u0000\u0000\u0000XY\u0001\u0000"+
		"\u0000\u0000Y\r\u0001\u0000\u0000\u0000Z[\u0007\u0000\u0000\u0000[\u000f"+
		"\u0001\u0000\u0000\u0000\\]\u0005\u0016\u0000\u0000]\u0011\u0001\u0000"+
		"\u0000\u0000^_\u0005\u0017\u0000\u0000_`\u0005\u001f\u0000\u0000`a\u0005"+
		"\u0018\u0000\u0000ab\u0005 \u0000\u0000bi\u0005\u0019\u0000\u0000cd\u0005"+
		"\u001a\u0000\u0000de\u0005\u001f\u0000\u0000ei\u0005\u0019\u0000\u0000"+
		"fi\u0005\u001b\u0000\u0000gi\u0005\u001c\u0000\u0000h^\u0001\u0000\u0000"+
		"\u0000hc\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000hg\u0001\u0000"+
		"\u0000\u0000i\u0013\u0001\u0000\u0000\u0000\n\u0017\u001e )2;OUXh";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}