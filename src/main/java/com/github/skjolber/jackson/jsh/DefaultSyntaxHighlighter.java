package com.github.skjolber.jackson.jsh;

import com.fasterxml.jackson.core.JsonStreamContext;

public class DefaultSyntaxHighlighter implements SyntaxHighlighter {

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
		
		public SyntaxHighlighter build() {
			return new DefaultSyntaxHighlighter(fieldValue, binaryValue, booleanValue, nullValue, numberValue, stringValue);
		}
		
	}

	protected String fieldValue;
	protected String binaryValue;
	protected String booleanValue;
	protected String nullValue;
	protected String numberValue;
	protected String stringValue;

	public DefaultSyntaxHighlighter() {
		// String fieldValue, String binaryValue, String booleanValue, String nullValue, String numberValue, String stringValue
		this(null, ANSI_PURPLE, ANSI_GREEN, ANSI_BLACK, ANSI_BLUE, ANSI_RED);
	}
	
	public DefaultSyntaxHighlighter(String fieldValue, String binaryValue, String booleanValue, String nullValue, String numberValue, String stringValue) {
		super();
		this.fieldValue = fieldValue;
		this.binaryValue = binaryValue;
		this.booleanValue = booleanValue;
		this.nullValue = nullValue;
		this.numberValue = numberValue;
		this.stringValue = stringValue;
	}

	public String forField(String name, JsonStreamContext jsonStreamContext) {
		return null;
	}

	public String forNumber(JsonStreamContext jsonStreamContext) {
		return numberValue;
	}

	public String forString(JsonStreamContext jsonStreamContext) {
		return stringValue;
	}

	public String forBinary(JsonStreamContext jsonStreamContext) {
		return binaryValue;
	}

	public String forBoolean(JsonStreamContext jsonStreamContext) {
		return booleanValue;
	}

	public String forNull(JsonStreamContext jsonStreamContext) {
		return nullValue;
	}

}
