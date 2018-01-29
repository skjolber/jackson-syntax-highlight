package com.github.skjolber.jsh;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.Hightlight;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighterResolver;

/**
 * 
 * Resolver which returns two different {@linkplain SyntaxHighlighter}s
 * based on the context location.
 */

public class MySyntaxHighlighterResolver implements SyntaxHighlighterResolver {

	private SyntaxHighlighter base = new DefaultSyntaxHighlighter();
	
	private SyntaxHighlighter numberField = DefaultSyntaxHighlighter
				.newBuilder()
				.withNumber(Hightlight.BACKGROUND_RED)
				.withString(Hightlight.BACKGROUND_RED)
				.withWhitespace(Hightlight.BACKGROUND_RED)
				.withColon(Hightlight.BACKGROUND_RED)
				.withComma(Hightlight.BACKGROUND_RED)
				.withField(Hightlight.BACKGROUND_RED)
				.withCurlyBrackets(Hightlight.BACKGROUND_RED)
				.build();
	
	@Override
	public SyntaxHighlighter forFieldName(String fieldName, JsonStreamContext context) {
		if(context == null) {
			return base;
		}
		if(Objects.equals(fieldName, "number")) {
			return numberField;
		}

		return base;
	}
	
}
