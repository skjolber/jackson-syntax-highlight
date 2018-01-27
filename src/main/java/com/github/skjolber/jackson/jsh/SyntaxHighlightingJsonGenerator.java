package com.github.skjolber.jackson.jsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;

public class SyntaxHighlightingJsonGenerator extends JsonGeneratorDelegate {

	protected final SyntaxHighlighter syntaxHighlighter;
	protected SyntaxHighlightingPrettyPrinter prettyPrinter;

	public SyntaxHighlightingJsonGenerator(JsonGenerator d) {
		this(d, new DefaultSyntaxHighlighter());
	}

	public SyntaxHighlightingJsonGenerator(JsonGenerator d, SyntaxHighlighter highlighter) {
		this(d, highlighter, new DefaultPrettyPrinter());
	}

	public SyntaxHighlightingJsonGenerator(JsonGenerator d, SyntaxHighlighter highlighter, PrettyPrinter prettyPrinter) {
		super(d, false);

		this.syntaxHighlighter = highlighter;
		setPrettyPrinter(prettyPrinter);
	}

	@Override
	public void writeFieldName(String name) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forField(name, super.getOutputContext()));
		try {
			super.writeFieldName(name);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeFieldId(long id) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forField(Long.toString(id), delegate.getOutputContext()));
		try {
			delegate.writeFieldId(id);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeArray(int[] array, int offset, int length) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeArray(array, offset, length);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeArray(long[] array, int offset, int length) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeArray(array, offset, length);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeArray(double[] array, int offset, int length) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeArray(array, offset, length);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeBinary(Base64Variant b64variant, byte[] data, int offset, int len) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forBinary(delegate.getOutputContext()));
		try {
			super.writeBinary(b64variant, data, offset, len);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forBinary(delegate.getOutputContext()));
		try {
			return super.writeBinary(b64variant, data, dataLength);
		} finally {
			prettyPrinter.clearColor();
		}

	}

	@Override
	public void writeNumber(short v) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeNumber(v);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeNumber(int v) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeNumber(v);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeNumber(long v) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeNumber(v);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeNumber(BigInteger v) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeNumber(v);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeNumber(double v) throws IOException, UnsupportedOperationException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeNumber(v);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeNumber(float v) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeNumber(v);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeNumber(BigDecimal v) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeNumber(v);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeNumber(String encodedValue) throws IOException, UnsupportedOperationException {
		prettyPrinter.setColor(syntaxHighlighter.forNumber(delegate.getOutputContext()));
		try {
			super.writeNumber(encodedValue);
		} finally {
			prettyPrinter.clearColor();
		}

	}

	@Override
	public void writeBoolean(boolean state) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forBoolean(delegate.getOutputContext()));
		try {
			super.writeBoolean(state);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeNull() throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forNull(delegate.getOutputContext()));
		try {
			super.writeNull();
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeString(String text) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forString(delegate.getOutputContext()));
		try {
			super.writeString(text);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeString(Reader reader, int len) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forString(delegate.getOutputContext()));
		try {
			delegate.writeString(reader, len);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeString(char[] text, int offset, int len) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forString(delegate.getOutputContext()));
		try {
			delegate.writeString(text, offset, len);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeString(SerializableString text) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forString(delegate.getOutputContext()));
		try {
			delegate.writeString(text);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public void writeUTF8String(byte[] text, int offset, int length) throws IOException {
		prettyPrinter.setColor(syntaxHighlighter.forString(delegate.getOutputContext()));
		try {
			delegate.writeUTF8String(text, offset, length);
		} finally {
			prettyPrinter.clearColor();
		}
	}

	@Override
	public JsonGenerator setPrettyPrinter(PrettyPrinter pp) {
		this.prettyPrinter = new SyntaxHighlightingPrettyPrinter(pp);
		return super.setPrettyPrinter(prettyPrinter);
	}
}
