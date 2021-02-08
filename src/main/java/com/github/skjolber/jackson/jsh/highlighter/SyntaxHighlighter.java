package com.github.skjolber.jackson.jsh;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * All methods must return a value. Return {@linkplain AnsiSyntaxHighlight#RESET} for default coloring.
 * 
 */

public interface SyntaxHighlighter {

	String forCurlyBrackets();

	String forSquareBrackets();
	
	String forColon();

	String forComma();

	String forPretty();

	String forWhitespace();
	
	String forFieldName(String value);
	 
	String forNumber(int value);
	String forNumber(double value);
	String forNumber(long value);
	String forNumber(BigInteger v);
	String forNumber(BigDecimal v);
	String forNumber(String encodedValue);
	
	String forString(String string);
	
	String forBinary();
	
	String forBoolean(boolean value);
	
	String forNull();
}
