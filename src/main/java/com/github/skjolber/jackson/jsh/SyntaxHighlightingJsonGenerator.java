package com.github.skjolber.jackson.jsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;

/**
 * Syntax highlighting {@linkplain JsonGenerator} wrapper. 
 * 
 *  Always uses the same highlighting colors.
 * 
 */

public class SyntaxHighlightingJsonGenerator extends JsonGeneratorDelegate {

	/*
	 * Implementation note: The indent and value is triggered in the same
	 * method call, so the indenter must be rigged so that they write the 
	 * correct color after the whitespace. 
	 * 
	 */
	
	protected SyntaxHighlightingPrettyPrinter prettyPrinter;
	protected SyntaxHighlighterObjectIndenter objectIndenter;
	protected SyntaxHighlighterArrayIndenter arrayIndenter;
	
	protected SyntaxHighlighter syntaxHighlighter;

	public SyntaxHighlightingJsonGenerator(JsonGenerator d) {
		this(d, new DefaultSyntaxHighlighter(), null);
	}

	public SyntaxHighlightingJsonGenerator(JsonGenerator d, SyntaxHighlighter syntaxHighlighter) {
		this(d, syntaxHighlighter, null);
	}

	public SyntaxHighlightingJsonGenerator(JsonGenerator d, SyntaxHighlighter syntaxHighlighter, JsonStreamContextListener listener) {
		super(d, false);

		this.syntaxHighlighter = syntaxHighlighter;
		
		this.objectIndenter = new SyntaxHighlighterObjectIndenter(syntaxHighlighter);
		this.arrayIndenter = new SyntaxHighlighterArrayIndenter(syntaxHighlighter);
		
		this.prettyPrinter = new SyntaxHighlightingPrettyPrinter(syntaxHighlighter, objectIndenter, arrayIndenter, listener);
		setPrettyPrinter(prettyPrinter);
	}

	@Override
	public void writeFieldName(String value) throws IOException {
		prettyPrinter.setCommaColor(syntaxHighlighter.forComma());
		objectIndenter.setValueColor(syntaxHighlighter.forFieldName(value));

		super.writeFieldName(value);

		objectIndenter.clearValueColor();
		prettyPrinter.cleanCommaColor();
	}

	@Override
	public void writeFieldName(SerializableString name) throws IOException {
		prettyPrinter.setCommaColor(syntaxHighlighter.forComma());
		objectIndenter.setValueColor(syntaxHighlighter.forFieldName(name.getValue()));

		super.writeFieldName(name);

		objectIndenter.clearValueColor();
		prettyPrinter.cleanCommaColor();
	}

	@Override
	public void writeFieldId(long id) throws IOException {
		prettyPrinter.setCommaColor(syntaxHighlighter.forComma());
		objectIndenter.setValueColor(syntaxHighlighter.forFieldName(Long.toString(id)));

		super.writeFieldId(id);

		objectIndenter.clearValueColor();
		prettyPrinter.cleanCommaColor();
	}

	@Override
	public void writeArray(int[] array, int offset, int length) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        _verifyOffsets(array.length, offset, length);
        writeStartArray();
        for (int i = offset, end = offset+length; i < end; ++i) {
    		arrayIndenter.setValueColor(syntaxHighlighter.forNumber(array[i]));
            super.writeNumber(array[i]);
        }
        writeEndArray();		
		
		arrayIndenter.clearValueColor();
	}

	@Override
	public void writeArray(long[] array, int offset, int length) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        _verifyOffsets(array.length, offset, length);
        writeStartArray();
        for (int i = offset, end = offset+length; i < end; ++i) {
    		arrayIndenter.setValueColor(syntaxHighlighter.forNumber(array[i]));
            super.writeNumber(array[i]);
        }
        writeEndArray();		
		
		arrayIndenter.clearValueColor();
	}

	@Override
	public void writeArray(double[] array, int offset, int length) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        _verifyOffsets(array.length, offset, length);
        writeStartArray();
        for (int i = offset, end = offset+length; i < end; ++i) {
    		arrayIndenter.setValueColor(syntaxHighlighter.forNumber(array[i]));
            super.writeNumber(array[i]);
        }
        writeEndArray();		
		
		arrayIndenter.clearValueColor();
	}

	@Override
	public void writeBinary(Base64Variant b64variant, byte[] data, int offset, int len) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forBinary());
		super.writeBinary(b64variant, data, offset, len);
	}

	@Override
	public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forBinary());
		return super.writeBinary(b64variant, data, dataLength);

	}

	@Override
	public void writeNumber(short v) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
	}

	@Override
	public void writeNumber(int v) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
	}

	@Override
	public void writeNumber(long v) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
	}

	@Override
	public void writeNumber(BigInteger v) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
	}

	@Override
	public void writeNumber(double v) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
	}

	@Override
	public void writeNumber(float v) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
	}

	@Override
	public void writeNumber(BigDecimal v) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(v));
		super.writeNumber(v);
	}

	@Override
	public void writeNumber(String encodedValue) throws IOException, UnsupportedOperationException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNumber(encodedValue));
		super.writeNumber(encodedValue);

	}

	@Override
	public void writeBoolean(boolean value) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forBoolean(value));
		super.writeBoolean(value);
	}

	@Override
	public void writeNull() throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forNull());
		super.writeNull();
	}

	@Override
	public void writeString(String value) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(value));
		super.writeString(value);
	}

	@Override
	public void writeString(Reader reader, int len) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(null));
		delegate.writeString(reader, len);
	}

	@Override
	public void writeString(char[] text, int offset, int len) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(new String(text, offset, len)));
		delegate.writeString(text, offset, len);
	}

	@Override
	public void writeString(SerializableString text) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(text.getValue()));
		delegate.writeString(text);
	}

	@Override
	public void writeUTF8String(byte[] text, int offset, int length) throws IOException {
		prettyPrinter.setValueColor(syntaxHighlighter.forString(new String(text, offset, length, StandardCharsets.UTF_8)));
		delegate.writeUTF8String(text, offset, length);
	}

}
