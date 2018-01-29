package com.github.skjolber.jackson.jsh;

public interface SyntaxHighlighter {

	String forCurlyBrackets();

	String forSquareBrackets();
	
	String forColon();

	String forComma();

	String forWhitespace();
	
	String forFieldName();
	 
	String forNumber();
	
	String forString();
	
	String forBinary();
	
	String forBoolean();
	
	String forNull();
}
