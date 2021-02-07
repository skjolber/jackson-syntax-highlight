package com.github.skjolber.jsh;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.github.skjolber.jackson.jsh.AnsiSyntaxHighlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.JsonStreamContextListener;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

/**
 * 
 * Resolver which returns two different {@linkplain SyntaxHighlighter}s
 * based on the context location.
 */

public class SubtreeJsonStreamContextListener implements JsonStreamContextListener, SyntaxHighlighter {

	private final SyntaxHighlighter base;
	private final SyntaxHighlighter numberField;
	private SyntaxHighlighter delegate;

	/**
	 * Default Listener uses a pretty SyntaxHighlighter
	 */
	public SubtreeJsonStreamContextListener(){
		base = new DefaultSyntaxHighlighter();

		numberField = DefaultSyntaxHighlighter
				.newBuilder()
				.withBackground(AnsiSyntaxHighlight.BACKGROUND_RED)
				.build();

		delegate = base;
	}

	/**
	 * Listener uses a pretty SyntaxHighlighter if [ {@code isPretty = true} ]
	 * otherwise the syntax will be flat
	 * @param isPretty determines if the json should be prettified or not
	 */
	public SubtreeJsonStreamContextListener(boolean isPretty){
		base = new DefaultSyntaxHighlighter(isPretty);

		numberField = DefaultSyntaxHighlighter
				.newBuilder().withPretty(isPretty)
				.withBackground(AnsiSyntaxHighlight.BACKGROUND_RED)
				.build();

		delegate = base;
	}

	public SyntaxHighlighter field(JsonStreamContext context) {
		if(context.pathAsPointer().toString().equals("/object")) {
			return numberField;
		}

		return base;
	}

	@Override
	public void startObject(JsonStreamContext outputContext) {
		this.delegate = field(outputContext);
	}

	@Override
	public void endObject(JsonStreamContext outputContext) {
		// reset
		this.delegate = base;
	}

	@Override
	public void startArray(JsonStreamContext outputContext) {
		this.delegate = field(outputContext);
	}

	@Override
	public void endArray(JsonStreamContext outputContext) {
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
	public String forPretty() {
		return delegate.forPretty();
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
