package com.github.skjolber.jackson.jsh;

import com.fasterxml.jackson.core.JsonStreamContext;

public interface SyntaxHighlighterResolver {

	/**
	 * Resolve a syntax highlighter. Notice that the context is at a position
	 * BEFORE the new field name.
	 * 
	 * @param fieldName
	 * @param context
	 * @return
	 */
	
	SyntaxHighlighter forFieldName(String fieldName, JsonStreamContext context);
	
}
