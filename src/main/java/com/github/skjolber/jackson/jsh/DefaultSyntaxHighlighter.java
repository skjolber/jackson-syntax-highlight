package com.github.skjolber.jackson.jsh;

import com.fasterxml.jackson.core.JsonStreamContext;

public class DefaultSyntaxHighlighter implements SyntaxHighlighter{

	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		protected String fieldValue;
		protected String binaryValue;
		protected String booleanValue;
		protected String nullValue;
		protected String numberValue;
		protected String stringValue;
		protected String curlyBrackets;
		protected String squareBrackets;
		protected String colon;
		protected String whitespace;
		protected String comma;
		
		public Builder withComma(String value) {
			this.comma = value;
			return this;
		}
		
		public Builder withCurlyBrackets(String value) {
			this.curlyBrackets = value;
			return this;
		}

		public Builder withSquareBrackets(String value) {
			this.squareBrackets = value;
			return this;
		}

		public Builder withColon(String value) {
			this.colon = value;
			return this;
		}

		public Builder withWhitespace(String whitespace) {
			this.whitespace = whitespace;
			return this;
		}

		public Builder withField(String fieldValue) {
			this.fieldValue = fieldValue;
			return this;
		}
		public Builder withBinary(String binaryValue) {
			this.binaryValue = binaryValue;
			return this;
		}
		public Builder withBoolean(String booleanValue) {
			this.booleanValue = booleanValue;
			return this;
		}
		public Builder withNull(String nullValue) {
			this.nullValue = nullValue;
			return this;
		}
		public Builder withNumber(String numberValue) {
			this.numberValue = numberValue;
			return this;
		}
		public Builder withString(String stringValue) {
			this.stringValue = stringValue;
			return this;
		}
		
		public DefaultSyntaxHighlighter build() {
			return new DefaultSyntaxHighlighter(fieldValue, binaryValue, booleanValue, nullValue, numberValue, stringValue, curlyBrackets, squareBrackets, colon, whitespace, comma);
		}
		
	}

	protected String fieldName;
	protected String binaryValue;
	protected String booleanValue;
	protected String nullValue;
	protected String numberValue;
	protected String stringValue;
	protected String curlyBrackets;
	protected String squareBrackets;
	protected String colon;
	protected String whitespace;
	protected String comma;

	public DefaultSyntaxHighlighter() {
		// String fieldValue, String binaryValue, String booleanValue, String nullValue, String numberValue, String stringValue
		this(null, ANSI_PURPLE, ANSI_GREEN, ANSI_BLACK, ANSI_BLUE, ANSI_RED, null, null, null, null, null);
	}
	
	public DefaultSyntaxHighlighter(String fieldValue, String binaryValue, String booleanValue, String nullValue, String numberValue, String stringValue, String curlyBrackets, String squareBrackets, String colon, String whitespace, String comma) {
		super();
		this.fieldName = fieldValue;
		this.binaryValue = binaryValue;
		this.booleanValue = booleanValue;
		this.nullValue = nullValue;
		this.numberValue = numberValue;
		this.stringValue = stringValue;
		this.curlyBrackets = curlyBrackets;
		this.squareBrackets = squareBrackets;
		this.colon = colon;
		this.whitespace = whitespace;
		this.comma = comma;
	}

	public String forFieldName() {
		return fieldName;
	}

	public String forNumber() {
		return numberValue;
	}

	public String forString() {
		return stringValue;
	}

	public String forBinary() {
		return binaryValue;
	}

	public String forBoolean() {
		return booleanValue;
	}

	public String forNull() {
		return nullValue;
	}

	@Override
	public String forCurlyBrackets() {
		return curlyBrackets;
	}

	@Override
	public String forSquareBrackets() {
		return squareBrackets;
	}

	@Override
	public String forColon() {
		return colon;
	}

	@Override
	public String forWhitespace() {
		return whitespace;
	}

	@Override
	public String forComma() {
		return comma;
	}

}
