package com.github.skjolber.jsh;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighterResolver;

public class MySyntaxHighlighterResolver implements SyntaxHighlighterResolver {

	private SyntaxHighlighter base = new DefaultSyntaxHighlighter();
	private SyntaxHighlighter object = DefaultSyntaxHighlighter
				.newBuilder()
				.withNumber(SyntaxHighlighter.ANSI_RED_BACKGROUND)
				.withString(SyntaxHighlighter.ANSI_RED_BACKGROUND)
				.withWhitespace(SyntaxHighlighter.ANSI_RED_BACKGROUND)
				.withColon(SyntaxHighlighter.ANSI_RED_BACKGROUND)
				.withField(SyntaxHighlighter.ANSI_RED_BACKGROUND)
				.withCurlyBrackets(SyntaxHighlighter.ANSI_RED_BACKGROUND)
				.build();
	
	@Override
	public SyntaxHighlighter forFieldName(String fieldName, JsonStreamContext context) {
		if(context == null) {
			return base;
		}
		if(fieldName.equals("number")) {
			return object;
		}

		return base;
	}
	
}
