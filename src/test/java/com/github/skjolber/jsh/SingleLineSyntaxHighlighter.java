package com.github.skjolber.jsh;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.github.skjolber.jackson.jsh.AnsiSyntaxHightlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class SingleLineSyntaxHighlighter implements SyntaxHighlighter {

	private SyntaxHighlighter base = new DefaultSyntaxHighlighter();
	
	private SyntaxHighlighter redBackground = DefaultSyntaxHighlighter
				.newBuilder()
				.withBackground(AnsiSyntaxHightlight.BACKGROUND_RED)
				.build();
	
	private SyntaxHighlighter delegate = base;
	
	public SyntaxHighlighter field(JsonStreamContext context) {
		if(context.inRoot()) {
			return base;
		}

		return base;
	}

	@Override
	public String forCurlyBrackets() {
		return getSyntaxHighlighter().forCurlyBrackets();
	}

	@Override
	public String forSquareBrackets() {
		return getSyntaxHighlighter().forSquareBrackets();
	}

	@Override
	public String forColon() {
		return getSyntaxHighlighter().forColon();
	}

	@Override
	public String forComma() {
		return getSyntaxHighlighter().forComma();
	}

	@Override
	public String forWhitespace() {
		return getSyntaxHighlighter().forWhitespace();
	}

	@Override
	public String forFieldName(String value) {
		if("booleanValue".equals(value)) {
			this.delegate = redBackground;
		} else {
			this.delegate = base;
		}
		return getSyntaxHighlighter().forFieldName(value);
	}

	@Override
	public String forNumber(int value) {
		return getSyntaxHighlighter().forNumber(value);
	}

	@Override
	public String forString(String value) {
		return getSyntaxHighlighter().forString(value);
	}

	@Override
	public String forBinary() {
		return getSyntaxHighlighter().forBinary();
	}

	@Override
	public String forBoolean(boolean value) {
		return getSyntaxHighlighter().forBoolean(value);
	}

	@Override
	public String forNull() {
		return getSyntaxHighlighter().forNull();
	}

	@Override
	public String forNumber(double value) {
		return getSyntaxHighlighter().forNumber(value);
	}

	@Override
	public String forNumber(long value) {
		return getSyntaxHighlighter().forNumber(value);
	}

	@Override
	public String forNumber(BigInteger v) {
		return getSyntaxHighlighter().forNumber(v);
	}

	@Override
	public String forNumber(BigDecimal v) {
		return getSyntaxHighlighter().forNumber(v);
	}

	@Override
	public String forNumber(String encodedValue) {
		return getSyntaxHighlighter().forNumber(encodedValue);
	}

	private SyntaxHighlighter getSyntaxHighlighter() {
		return delegate;
	}	
}
