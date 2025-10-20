package org.entur.jackson.tools.jsh;

import java.math.BigDecimal;
import java.math.BigInteger;

import tools.jackson.core.TokenStreamContext;

/**
 * 
 * Resolver which returns two different {@linkplain SyntaxHighlighter}s
 * based on the context location.
 */

public class SubtreeTokenStreamContextListener implements TokenStreamContextListener, SyntaxHighlighter {

	private SyntaxHighlighter base = new DefaultSyntaxHighlighter();
	
	private SyntaxHighlighter numberField = DefaultSyntaxHighlighter
				.newBuilder()
				.withBackground(AnsiSyntaxHighlight.BACKGROUND_RED)
				.build();
	
	private SyntaxHighlighter delegate = base;
	
	public SyntaxHighlighter field(TokenStreamContext context) {
		if(context.pathAsPointer().toString().equals("/object")) {
			return numberField;
		}

		return base;
	}

	@Override
	public void startObject(TokenStreamContext outputContext) {
		this.delegate = field(outputContext);
	}

	@Override
	public void endObject(TokenStreamContext outputContext) {
		// reset
		this.delegate = base;
	}

	@Override
	public void startArray(TokenStreamContext outputContext) {
		this.delegate = field(outputContext);
	}

	@Override
	public void endArray(TokenStreamContext outputContext) {
		// reset
		this.delegate = base;
	}
	
	@Override
	public String forCurlyBrackets() {
		return delegate.forCurlyBrackets();
	}

	@Override
	public String forSquareBrackets() {
		return delegate.forSquareBrackets();
	}

	@Override
	public String forColon() {
		return delegate.forColon();
	}

	@Override
	public String forComma() {
		return delegate.forComma();
	}

	@Override
	public String forWhitespace() {
		return delegate.forWhitespace();
	}

	@Override
	public String forFieldName(String value) {
		return delegate.forFieldName(value);
	}

	@Override
	public String forNumber(int value) {
		return delegate.forNumber(value);
	}

	@Override
	public String forString(String value) {
		return delegate.forString(value);
	}

	@Override
	public String forBinary() {
		return delegate.forBinary();
	}

	@Override
	public String forBoolean(boolean value) {
		return delegate.forBoolean(value);
	}

	@Override
	public String forNull() {
		return delegate.forNull();
	}

	@Override
	public String forNumber(double value) {
		return delegate.forNumber(value);
	}

	@Override
	public String forNumber(long value) {
		return delegate.forNumber(value);
	}

	@Override
	public String forNumber(BigInteger v) {
		return delegate.forNumber(v);
	}

	@Override
	public String forNumber(BigDecimal v) {
		return delegate.forNumber(v);
	}

	@Override
	public String forNumber(String encodedValue) {
		return delegate.forNumber(encodedValue);
	}

}
