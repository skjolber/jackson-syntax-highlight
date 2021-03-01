package com.github.skjolber.jackson.jsh;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

/**
 * 
 * Pretty-printer which technically can produce output with or without indent / linebreaks.
 *
 */

public class SyntaxHighlightingPrettyPrinter extends DefaultPrettyPrinter {

	private static final long serialVersionUID = 1L;
	
	protected SyntaxHighlighter syntaxHighlighter;
	protected SyntaxHighlighterIndenter objectIndenter;
	protected SyntaxHighlighterIndenter arrayIndenter;
	protected JsonStreamContextListener listener;
	
	private String valueColor;
	private String commaColor;
	
	public SyntaxHighlightingPrettyPrinter(SyntaxHighlighter syntaxHighlighter, SyntaxHighlighterIndenter objectIndenter, SyntaxHighlighterIndenter arrayIndenter, JsonStreamContextListener listener) {
		this.syntaxHighlighter = syntaxHighlighter;
		_objectIndenter = objectIndenter;
		_arrayIndenter = arrayIndenter;
		
		this.objectIndenter = objectIndenter;
		this.arrayIndenter = arrayIndenter;
		this.listener = listener;
	}

	public void writeRootValueSeparator(JsonGenerator gen) throws IOException {
		gen.writeRaw(AnsiSyntaxHighlight.RESET);
		super.writeRootValueSeparator(gen);
	}

	public void writeStartObject(JsonGenerator gen) throws IOException {
		gen.writeRaw(syntaxHighlighter.forCurlyBrackets());
		super.writeStartObject(gen);
		
		if(listener != null) {
			listener.startObject(gen.getOutputContext());
		}
	}

	public void writeEndObject(JsonGenerator gen, int nrOfEntries) throws IOException {
		if(listener != null) {
			listener.endObject(gen.getOutputContext().getParent());
		}
		
		String color = syntaxHighlighter.forCurlyBrackets();
		
		gen.writeRaw(color);

		objectIndenter.setValueColor(color);

		super.writeEndObject(gen, nrOfEntries);
		
		objectIndenter.clearValueColor();
	}

	public void writeObjectEntrySeparator(JsonGenerator gen) throws IOException {
		gen.writeRaw(commaColor);

		super.writeObjectEntrySeparator(gen);
	}

	public void writeObjectFieldValueSeparator(JsonGenerator gen) throws IOException {
		if(_spacesInObjectEntries) {
			gen.writeRaw(syntaxHighlighter.forWhitespace());
			gen.writeRaw(' ');
			gen.writeRaw(syntaxHighlighter.forColon());
			gen.writeRaw(_separators.getObjectFieldValueSeparator());
			gen.writeRaw(syntaxHighlighter.forWhitespace());
			gen.writeRaw(' ');
		} else {
			gen.writeRaw(syntaxHighlighter.forColon());
            gen.writeRaw(_separators.getObjectFieldValueSeparator());
		}

		if(valueColor != null) {
			gen.writeRaw(valueColor);
			valueColor = null;
		}
	}

	public void writeStartArray(JsonGenerator gen) throws IOException {
		gen.writeRaw(syntaxHighlighter.forSquareBrackets());
		super.writeStartArray(gen);
		
		if(listener != null) {
			listener.startArray(gen.getOutputContext());
		}
	}

	public void writeEndArray(JsonGenerator gen, int nrOfValues) throws IOException {
		if(listener != null) {
			listener.endArray(gen.getOutputContext().getParent());
		}

		String color = syntaxHighlighter.forSquareBrackets();
		
		gen.writeRaw(color);

		arrayIndenter.setValueColor(color);

		super.writeEndArray(gen, nrOfValues);
		
		arrayIndenter.clearValueColor();
	}

	public void writeArrayValueSeparator(JsonGenerator gen) throws IOException {
		gen.writeRaw(syntaxHighlighter.forComma());		
		
		if(valueColor != null) {
			arrayIndenter.setValueColor(valueColor);
		}

		super.writeArrayValueSeparator(gen);
	}

	public void beforeArrayValues(JsonGenerator gen) throws IOException {
		if(valueColor != null) {
			arrayIndenter.setValueColor(valueColor);
		}
		super.beforeArrayValues(gen);
	}

	public void beforeObjectEntries(JsonGenerator gen) throws IOException {
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
}
