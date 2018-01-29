package com.github.skjolber.jackson.jsh;

import com.fasterxml.jackson.core.JsonStreamContext;

public interface SyntaxHighlighterResolver {

	/**
	 * Resolver for {@linkplain SyntaxHighlighter}, to be used for the supplied 
	 * field name and its subtree.<br><br>
	 *  
	 * Notice that the {@linkplain JsonStreamContext} is at a 
	 * position BEFORE the new field name.
	 * 
	 * @param fieldName name of pending field name
	 * @param context position within the tree 
	 * @return {@linkplain SyntaxHighlighter} for the field name (and its subtree)
	 */
	
	SyntaxHighlighter forFieldName(String fieldName, JsonStreamContext context);
	
}
