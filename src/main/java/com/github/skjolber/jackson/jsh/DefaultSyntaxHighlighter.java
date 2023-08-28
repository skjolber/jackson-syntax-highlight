package com.github.skjolber.jackson.jsh;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DefaultSyntaxHighlighter implements SyntaxHighlighter {

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {

		protected String[] fieldName;
		protected String[] binaryValue;
		protected String[] booleanValue;
		protected String[] nullValue;
		protected String[] numberValue;
		protected String[] stringValue;
		protected String[] curlyBrackets;
		protected String[] squareBrackets;
		protected String[] colon;
		protected String[] whitespace;
		protected String[] comma;

		// common for all colors, if no background is set
		protected String background;

		protected String build(String[] colors) {
			if (background == null) {
				return AnsiSyntaxHighlight.build(colors);
			}
			if (colors == null || colors.length == 0) {
				return AnsiSyntaxHighlight.build(background);
			}

			// check if already background is specified
			for (String c : colors) {
				if (AnsiSyntaxHighlight.isBackground(c)) {
					return AnsiSyntaxHighlight.build(colors);
				}
			}
			String[] colorsWithBackground = new String[colors.length + 1];
			System.arraycopy(colors, 0, colorsWithBackground, 0, colors.length);
			colorsWithBackground[colors.length] = background;
			return AnsiSyntaxHighlight.build(colorsWithBackground);
		}

		public Builder withComma(String... value) {
			this.comma = value;
			return this;
		}

		public Builder withCurlyBrackets(String... value) {
			this.curlyBrackets = value;
			return this;
		}

		public Builder withSquareBrackets(String... value) {
			this.squareBrackets = value;
			return this;
		}

		public Builder withColon(String... value) {
			this.colon = value;
			return this;
		}

		public Builder withWhitespace(String... value) {
			this.whitespace = value;
			return this;
		}

		public Builder withField(String... value) {
			this.fieldName = value;
			return this;
		}

		public Builder withBinary(String... values) {
			this.binaryValue = values;
			return this;
		}

		public Builder withBoolean(String... values) {
			this.booleanValue = values;
			return this;
		}

		public Builder withNull(String... values) {
			this.nullValue = values;
			return this;
		}

		public Builder withNumber(String... values) {
			this.numberValue = values;
			return this;
		}

		public Builder withString(String... values) {
			this.stringValue = values;
			return this;
		}

		/**
		 * Se the default background. This will be added to all colors which do not themself define a background color.
		 * 
		 * @param value background color
		 * @return builder
		 */

		public Builder withBackground(String value) {
			this.background = value;
			return this;
		}

		public DefaultSyntaxHighlighter build() {
			return new DefaultSyntaxHighlighter(this);
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
		this(newBuilder());
	}

	public DefaultSyntaxHighlighter(String fieldValue, String binaryValue, String booleanValue, String nullValue, String numberValue, String stringValue,
			String curlyBrackets, String squareBrackets, String colon, String whitespace, String comma) {
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

	public DefaultSyntaxHighlighter(Builder builder) {
		this(
			builder.build(builder.fieldName), 
			builder.build(builder.binaryValue),
			builder.build(builder.booleanValue), 
			builder.build(builder.nullValue), 
			builder.build(builder.numberValue),
			builder.build(builder.stringValue), 
			builder.build(builder.curlyBrackets), 
			builder.build(builder.squareBrackets),
			builder.build(builder.colon), 
			builder.build(builder.whitespace), 
			builder.build(builder.comma)	
		);
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
