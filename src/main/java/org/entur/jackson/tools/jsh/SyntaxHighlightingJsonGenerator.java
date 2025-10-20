package org.entur.jackson.tools.jsh;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import tools.jackson.core.Base64Variant;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.PrettyPrinter;
import tools.jackson.core.SerializableString;
import tools.jackson.core.util.JsonGeneratorDelegate;

/**
 * Syntax highlighting {@linkplain JsonGenerator} wrapper.
 * 
 * Always uses the same highlighting colors.
 * 
 */

public class SyntaxHighlightingJsonGenerator extends JsonGeneratorDelegate {

	public static SyntaxHighlightingJsonGenerator create(JsonGenerator jsonGenerator) {
		return create(jsonGenerator, null);
	}

	public static SyntaxHighlightingJsonGenerator create(JsonGenerator jsonGenerator, TokenStreamContextListener listener) {
		PrettyPrinter candidate = jsonGenerator.getPrettyPrinter();
		if(!(candidate instanceof SyntaxHighlightingPrettyPrinter)) {
			throw new IllegalStateException("Expected pretty-printer of type " + SyntaxHighlightingPrettyPrinter.class.getName());
		}
		SyntaxHighlightingPrettyPrinter prettyPrinter = (SyntaxHighlightingPrettyPrinter)candidate;

		prettyPrinter.setTokenStreamContextListener(listener);

		return new SyntaxHighlightingJsonGenerator(jsonGenerator, prettyPrinter, prettyPrinter.getObjectIndenter(), prettyPrinter.getArrayIndenter(), prettyPrinter.getSyntaxHighlighter());
	}

	/*
	 *
	 * Implementation note: The indent and value is triggered in the same method call, so the indenter must be rigged so that they write the correct color after
	 * the whitespace.
	 * 
	 */

	protected SyntaxHighlightingPrettyPrinter prettyPrinter;
	protected SyntaxHighlighterIndenter objectIndenter;
	protected SyntaxHighlighterIndenter arrayIndenter;

	protected SyntaxHighlighter syntaxHighlighter;

	public SyntaxHighlightingJsonGenerator(JsonGenerator d, SyntaxHighlightingPrettyPrinter prettyPrinter, SyntaxHighlighterIndenter objectIndenter, SyntaxHighlighterIndenter arrayIndenter, SyntaxHighlighter syntaxHighlighter) {
		super(d, false);
		this.prettyPrinter = prettyPrinter;
		this.objectIndenter = objectIndenter;
		this.arrayIndenter = arrayIndenter;
		this.syntaxHighlighter = syntaxHighlighter;
	}

	@Override
	public JsonGenerator writeName(String value) throws JacksonException {
		prettyPrinter.setCommaColor(syntaxHighlighter.forComma());
		objectIndenter.setValueColor(syntaxHighlighter.forFieldName(value));

		super.writeName(value);

		objectIndenter.clearValueColor();
		prettyPrinter.cleanCommaColor();
		return this;
	}

	@Override
	public JsonGenerator writeName(SerializableString name) throws JacksonException {
		prettyPrinter.setCommaColor(syntaxHighlighter.forComma());
		objectIndenter.setValueColor(syntaxHighlighter.forFieldName(name.getValue()));

		super.writeName(name);

		objectIndenter.clearValueColor();
		prettyPrinter.cleanCommaColor();
		return this;
	}

	@Override
	public JsonGenerator writePropertyId(long id) throws JacksonException {
		prettyPrinter.setCommaColor(syntaxHighlighter.forComma());
		objectIndenter.setValueColor(syntaxHighlighter.forFieldName(Long.toString(id)));

		super.writePropertyId(id);

		objectIndenter.clearValueColor();
		prettyPrinter.cleanCommaColor();
		return this;
	}

	@Override
	public JsonGenerator writeArray(int[] array, int offset, int length) throws JacksonException {
		if (array == null) {
			throw new IllegalArgumentException("null array");
		}
		_verifyOffsets(array.length, offset, length);
		writeStartArray();
		for (int i = offset, end = offset + length; i < end; ++i) {
			arrayIndenter.setValueColor(syntaxHighlighter.forNumber(array[i]));
			super.writeNumber(array[i]);
		}
		writeEndArray();

		arrayIndenter.clearValueColor();
		return this;
	}

	@Override
	public JsonGenerator writeArray(long[] array, int offset, int length) throws JacksonException {
		if (array == null) {
			throw new IllegalArgumentException("null array");
		}
		_verifyOffsets(array.length, offset, length);
		writeStartArray();
		for (int i = offset, end = offset + length; i < end; ++i) {
			arrayIndenter.setValueColor(syntaxHighlighter.forNumber(array[i]));
			super.writeNumber(array[i]);
		}
		writeEndArray();

		arrayIndenter.clearValueColor();
		return this;
	}

	@Override
	public JsonGenerator writeArray(double[] array, int offset, int length) throws JacksonException {
		if (array == null) {
			throw new IllegalArgumentException("null array");
		}
		_verifyOffsets(array.length, offset, length);
		writeStartArray();
		for (int i = offset, end = offset + length; i < end; ++i) {
			arrayIndenter.setValueColor(syntaxHighlighter.forNumber(array[i]));
			super.writeNumber(array[i]);
		}
		writeEndArray();

		arrayIndenter.clearValueColor();
		return this;
	}

	@Override
	public JsonGenerator writeBinary(Base64Variant b64variant, byte[] data, int offset, int len) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forBinary());
		super.writeBinary(b64variant, data, offset, len);
		return this;
	}

	@Override
	public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forBinary());
		return super.writeBinary(b64variant, data, dataLength);
	}

	@Override
	public JsonGenerator writeNumber(short v) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
		return this;
	}

	@Override
	public JsonGenerator writeNumber(int v) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
		return this;
	}

	@Override
	public JsonGenerator writeNumber(long v) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
		return this;
	}

	@Override
	public JsonGenerator writeNumber(BigInteger v) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
		return this;
	}

	@Override
	public JsonGenerator writeNumber(double v) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
		return this;
	}

	@Override
	public JsonGenerator writeNumber(float v) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
		return this;
	}

	@Override
	public JsonGenerator writeNumber(BigDecimal v) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
		return this;
	}

	@Override
	public JsonGenerator writeNumber(String encodedValue) throws JacksonException, UnsupportedOperationException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(encodedValue));
		super.writeNumber(encodedValue);
		return this;
	}

	@Override
	public JsonGenerator writeBoolean(boolean value) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forBoolean(value));
		super.writeBoolean(value);
		return this;
	}

	@Override
	public JsonGenerator writeNull() throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNull());
		super.writeNull();
		return this;
	}

	@Override
	public JsonGenerator writeString(String value) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(value));
		super.writeString(value);
		return this;
	}

	@Override
	public JsonGenerator writeString(Reader reader, int len) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(null));
		super.writeString(reader, len);
		return this;
	}

	@Override
	public JsonGenerator writeString(char[] text, int offset, int len) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(new String(text, offset, len)));
		super.writeString(text, offset, len);
		return this;
	}

	@Override
	public JsonGenerator writeString(SerializableString text) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(text.getValue()));
		super.writeString(text);
		return this;
	}

	@Override
	public JsonGenerator writeRawUTF8String(byte[] bytes, int offset, int length) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(new String(bytes, offset, length)));
		super.writeRawUTF8String(bytes,offset, length);
		return this;
	}

	@Override
	public JsonGenerator writeUTF8String(byte[] text, int offset, int length) throws JacksonException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(new String(text, offset, length, StandardCharsets.UTF_8)));
		super.writeUTF8String(text, offset, length);
		return this;
	}

	@Override
	public void close() throws JacksonException {
		flush();
		delegate.writeRaw(AnsiSyntaxHighlight.RESET);
		super.close();
	}
}
