package com.github.skjolber.jackson.jsh;

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
		protected String curlyBrackets;
		protected String squareBrackets;
		protected String colon;
		protected String whitespace;
		protected String comma;
		
		// common for all colors, if no background is set
		protected String backgroundColorValue;
		
		private String background(String color) {
			if(backgroundColorValue == null) {
				return color;
			}
			if(color == null) {
				return backgroundColorValue;
			}
			
			for(int i = 0; i < color.length(); i++) {
				if(Hightlight.isBackground(color.substring(i, i+1))) {
					return color;
				}
			}
			return color + backgroundColorValue;
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
			this.fieldValue = toString(value);
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
			this.backgroundColorValue = toString(values);
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
					background(fieldValue), 
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
		this(null, Hightlight.MAGENTA, Hightlight.GREEN, Hightlight.BLACK, Hightlight.BLUE, Hightlight.RED, null, null, null, null, null);
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
			return Hightlight.SANE;
		}
		if(value.startsWith(Hightlight.SANE)) {
			return value;
		}
		return Hightlight.SANE + value;
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
