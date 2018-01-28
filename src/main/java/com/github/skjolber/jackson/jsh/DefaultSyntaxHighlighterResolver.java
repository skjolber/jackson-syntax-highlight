package com.github.skjolber.jackson.jsh;

import com.fasterxml.jackson.core.JsonStreamContext;

public class DefaultSyntaxHighlighterResolver implements SyntaxHighlighterResolver {

	private SyntaxHighlighter syntaxHighlighter;
	
	public DefaultSyntaxHighlighterResolver(SyntaxHighlighter syntaxHighlighter) {
		super();
		this.syntaxHighlighter = syntaxHighlighter;
	}

	@Override
	public SyntaxHighlighter forFieldName(String fieldName, JsonStreamContext context) {
		return syntaxHighlighter;
	}

}
