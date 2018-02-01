package com.github.skjolber.jackson.jsh;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DefaultSyntaxHighlighter implements SyntaxHighlighter {

	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		
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
		
		// common for all colors, if no background is set
		protected String background;
		
		private String background(String color) {
			if(background == null) {
				return color;
			}
			if(color == null) {
				return background;
			}
			
			for(int i = 0; i < color.length(); i++) {
				if(AnsiSyntaxHightlight.isBackground(color.substring(i, i+1))) {
					return color;
				}
			}
			return color + background;
		}
		
		public Builder withComma(String ... value) {
			this.comma = toString(value);
			return this;
		}
		
		public Builder withCurlyBrackets(String ... value) {
			this.curlyBrackets = toString(value);
			return this;
		}

		public Builder withSquareBrackets(String ... value) {
			this.squareBrackets = toString(value);
			return this;
		}

		public Builder withColon(String ... value) {
			this.colon = toString(value);
			return this;
		}

		public Builder withWhitespace(String ... value) {
			this.whitespace = toString(value);
			return this;
		}

		public Builder withField(String ... value) {
			this.fieldName = toString(value);
			return this;
		}
		public Builder withBinary(String ... values) {
			this.binaryValue = toString(values);
			return this;
		}
		
		public Builder withBoolean(String ... values) {
			this.booleanValue = toString(values);
			return this;
		}
		public Builder withNull(String ... values) {
			this.nullValue = toString(values);
			return this;
		}
		public Builder withNumber(String ... values) {
			this.numberValue = toString(values);
			return this;
		}
		public Builder withString(String ... values) {
			this.stringValue = toString(values);
			return this;
		}
		
		/**
		 * Se the default background. This will be added to all 
		 * colors which do not themself define a background color.
		 * 
		 * @param values background colors
		 * @return builder
		 */

		public Builder withBackground(String ... values) {
			this.background = toString(values);
			return this;
		}
		
		private String toString(String ... strings) {
			if(strings == null) {
				return null;
			}
			if(strings.length == 1) {
				return strings[0];
			}
			StringBuffer b = new StringBuffer();
			for(String string : strings) {
				b.append(string);
			}
			return b.toString();
		}

		public DefaultSyntaxHighlighter build() {
			return new DefaultSyntaxHighlighter(
					background(fieldName), 
					background(binaryValue),
					background(booleanValue), 
					background(nullValue), 
					background(numberValue),
					background(stringValue), 
					background(curlyBrackets), 
					background(squareBrackets),
					background(colon), 
					background(whitespace), 
					background(comma)
					
					);
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
		this(null, AnsiSyntaxHightlight.MAGENTA, AnsiSyntaxHightlight.GREEN, AnsiSyntaxHightlight.BLACK, AnsiSyntaxHightlight.BLUE, AnsiSyntaxHightlight.RED, null, null, null, null, null);
	}
	
	public DefaultSyntaxHighlighter(String fieldValue, String binaryValue, String booleanValue, String nullValue, String numberValue, String stringValue, String curlyBrackets, String squareBrackets, String colon, String whitespace, String comma) {
		super();
		this.fieldName = reset(fieldValue);
		this.binaryValue = reset(binaryValue);
		this.booleanValue = reset(booleanValue);
		this.nullValue = reset(nullValue);
		this.numberValue = reset(numberValue);
		this.stringValue = reset(stringValue);
		this.curlyBrackets = reset(curlyBrackets);
		this.squareBrackets = reset(squareBrackets);
		this.colon = reset(colon);
		this.whitespace = reset(whitespace);
		this.comma = reset(comma);
	}

	protected String reset(String value) {
		if(value == null) {
			return AnsiSyntaxHightlight.SANE;
		}
		if(value.startsWith(AnsiSyntaxHightlight.SANE)) {
			return value;
		}
		return AnsiSyntaxHightlight.SANE + value;
	}

	public String forFieldName(String value) {
		return fieldName;
	}

	public String forNumber(int value) {
		return numberValue;
	}

	public String forNumber(long value) {
		return numberValue;
	}

	public String forNumber(double value) {
		return numberValue;
	}

	public String forString(String string) {
		return stringValue;
	}

	public String forBinary() {
		return binaryValue;
	}

	public String forBoolean(boolean value) {
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

	@Override
	public String forNumber(BigInteger v) {
		return numberValue;
	}

	@Override
	public String forNumber(BigDecimal v) {
		return numberValue;
	}

	@Override
	public String forNumber(String encodedValue) {
		return numberValue;
	}

}
