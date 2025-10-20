package org.entur.jackson.tools.jsh;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;

/**
 * 
 * Pretty-printer. Must be used together with {@linkplain SyntaxHighlightingJsonGenerator}.
 *
 */

public class SyntaxHighlightingPrettyPrinter extends DefaultPrettyPrinter {

	private static final long serialVersionUID = 1L;

	protected SyntaxHighlighter syntaxHighlighter;
	protected SyntaxHighlighterIndenter objectIndenter;
	protected SyntaxHighlighterIndenter arrayIndenter;
	protected TokenStreamContextListener listener;

	private String valueColor;
	private String commaColor;

	public SyntaxHighlightingPrettyPrinter(SyntaxHighlighter syntaxHighlighter) {
		this(syntaxHighlighter, new SyntaxHighlighterIndenter(syntaxHighlighter, new DefaultIndenter()), new SyntaxHighlighterIndenter(syntaxHighlighter, new DefaultPrettyPrinter.FixedSpaceIndenter()));
	}

    public SyntaxHighlightingPrettyPrinter(SyntaxHighlighter syntaxHighlighter, SyntaxHighlighterIndenter objectIndenter,
			SyntaxHighlighterIndenter arrayIndenter) {
		this.syntaxHighlighter = syntaxHighlighter;
		_objectIndenter = objectIndenter;
		_arrayIndenter = arrayIndenter;

		this.objectIndenter = objectIndenter;
		this.arrayIndenter = arrayIndenter;
	}

	public void setTokenStreamContextListener(TokenStreamContextListener listener) {
		this.listener = listener;
	}

	public DefaultPrettyPrinter createInstance() {
		return new SyntaxHighlightingPrettyPrinter(syntaxHighlighter);
	}

	public void writeRootValueSeparator(JsonGenerator gen) {
		gen.writeRaw(AnsiSyntaxHighlight.RESET);
		super.writeRootValueSeparator(gen);
	}

	public void writeStartObject(JsonGenerator gen) {
		gen.writeRaw(syntaxHighlighter.forCurlyBrackets());
		super.writeStartObject(gen);

		if (listener != null) {
			listener.startObject(gen.streamWriteContext());
		}
	}

	public void writeEndObject(JsonGenerator gen, int nrOfEntries) {
		if (listener != null) {
			listener.endObject(gen.streamWriteContext().getParent());
		}

		String color = syntaxHighlighter.forCurlyBrackets();

		gen.writeRaw(color);

		objectIndenter.setValueColor(color);

		super.writeEndObject(gen, nrOfEntries);

		objectIndenter.clearValueColor();
	}

	public void writeObjectEntrySeparator(JsonGenerator gen) {
		gen.writeRaw(commaColor);

		super.writeObjectEntrySeparator(gen);
	}

	public void writeObjectNameValueSeparator(JsonGenerator gen) throws JacksonException {
		if (_objectNameValueSeparator != null) {
			gen.writeRaw(syntaxHighlighter.forWhitespace());
			gen.writeRaw(syntaxHighlighter.forColon());
			gen.writeRaw(_objectNameValueSeparator);
			gen.writeRaw(syntaxHighlighter.forWhitespace());
		} else {
			gen.writeRaw(syntaxHighlighter.forColon());
			gen.writeRaw(_objectNameValueSeparator);
		}

		if (valueColor != null) {
			gen.writeRaw(valueColor);
			valueColor = null;
		}
	}

	public void writeStartArray(JsonGenerator gen) {
		gen.writeRaw(syntaxHighlighter.forSquareBrackets());
		super.writeStartArray(gen);

		if (listener != null) {
			listener.startArray(gen.streamWriteContext());
		}
	}

	public void writeEndArray(JsonGenerator gen, int nrOfValues) {
		if (listener != null) {
			listener.endArray(gen.streamWriteContext().getParent());
		}

		String color = syntaxHighlighter.forSquareBrackets();

		gen.writeRaw(color);

		arrayIndenter.setValueColor(color);

		super.writeEndArray(gen, nrOfValues);

		arrayIndenter.clearValueColor();
	}

	public void writeArrayValueSeparator(JsonGenerator gen) {
		gen.writeRaw(syntaxHighlighter.forComma());

		if (valueColor != null) {
			arrayIndenter.setValueColor(valueColor);
		}

		super.writeArrayValueSeparator(gen);
	}

	public void beforeArrayValues(JsonGenerator gen) {
		if (valueColor != null) {
			arrayIndenter.setValueColor(valueColor);
		}
		super.beforeArrayValues(gen);
	}

	public void beforeObjectEntries(JsonGenerator gen) {
		super.beforeObjectEntries(gen);
	}

	public void setValueColor(String valueColor) {
		this.valueColor = valueColor;
	}

	public void setCommaColor(String commaColor) {
		this.commaColor = commaColor;
	}

	public void cleanCommaColor() {
		this.commaColor = null;
	}

	public SyntaxHighlighter getSyntaxHighlighter() {
		return syntaxHighlighter;
	}

	public SyntaxHighlighterIndenter getArrayIndenter() {
		return arrayIndenter;
	}

	public SyntaxHighlighterIndenter getObjectIndenter() {
		return objectIndenter;
	}
}
