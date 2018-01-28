package com.github.skjolber.jackson.jsh;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public class SyntaxHighlightingPrettyPrinter extends DefaultPrettyPrinter {

	private SyntaxHighlightingJsonGenerator generator;
	private SyntaxHighlighterObjectIndenter objectIndenter;
	private SyntaxHighlighterArrayIndenter arrayIndenter;

	private String valueColor;
	
	public SyntaxHighlightingPrettyPrinter(SyntaxHighlightingJsonGenerator generator, SyntaxHighlighterObjectIndenter objectIndenter, SyntaxHighlighterArrayIndenter arrayIndenter) {
		this.generator = generator;
		_objectIndenter = objectIndenter;
		_arrayIndenter = arrayIndenter;
		
		this.objectIndenter = objectIndenter;
		this.arrayIndenter = arrayIndenter;
	}

	public void writeRootValueSeparator(JsonGenerator gen) throws IOException {
		super.writeRootValueSeparator(gen);
	}

	public void writeStartObject(JsonGenerator gen) throws IOException {
		gen.writeRaw(generator.forCurlyBrackets());
		super.writeStartObject(gen);
	}

	public void writeEndObject(JsonGenerator gen, int nrOfEntries) throws IOException {
		String color = generator.forCurlyBrackets();
		
		gen.writeRaw(color);

		objectIndenter.setPostColor(color);

		super.writeEndObject(gen, nrOfEntries);
		
		objectIndenter.clearPostColor();
		
		generator.popSyntaxHighlighter();
	}

	public void writeObjectEntrySeparator(JsonGenerator gen) throws IOException {
		// new highlighter is already pushed, so look at the previous
		SyntaxHighlighter highlighter = generator.popPreviousSyntaxHighlighter();

		String comma = highlighter.forComma();
		if(comma == null) {
			comma = SyntaxHighlighter.ANSI_RESET;
		}
		
		gen.writeRaw(comma);

		objectIndenter.setPostColor(generator.forFieldName());
		super.writeObjectEntrySeparator(gen);
	}

	public void writeObjectFieldValueSeparator(JsonGenerator gen) throws IOException {
		if(_spacesInObjectEntries) {
			gen.writeRaw(generator.forWhitespace());
			gen.writeRaw(' ');
			gen.writeRaw(generator.forColon());
			gen.writeRaw(_separators.getObjectFieldValueSeparator());
			gen.writeRaw(generator.forWhitespace());
			gen.writeRaw(' ');
		} else {
			gen.writeRaw(generator.forColon());
			super.writeObjectFieldValueSeparator(gen);
		}

		if(valueColor != null) {
			gen.writeRaw(valueColor);
			valueColor = null;
		}

	}

	public void writeStartArray(JsonGenerator gen) throws IOException {
		gen.writeRaw(generator.forSquareBrackets());
		super.writeStartArray(gen);
	}

	public void writeEndArray(JsonGenerator gen, int nrOfValues) throws IOException {
		String color = generator.forSquareBrackets();
		
		gen.writeRaw(color);

		arrayIndenter.setPostColor(color);

		super.writeEndArray(gen, nrOfValues);
		
		arrayIndenter.clearPostColor();
		
		generator.popSyntaxHighlighter();		
	}

	public void writeArrayValueSeparator(JsonGenerator gen) throws IOException {
		gen.writeRaw(generator.forComma());		
		
		if(valueColor != null) {
			arrayIndenter.setPostColor(valueColor);
		}

		super.writeArrayValueSeparator(gen);
	}

	public void beforeArrayValues(JsonGenerator gen) throws IOException {
		if(valueColor != null) {
			arrayIndenter.setPostColor(valueColor);
		}
		super.beforeArrayValues(gen);
	}

	public void beforeObjectEntries(JsonGenerator gen) throws IOException {
		super.beforeObjectEntries(gen);
	}

	public void setValueColor(String valueColor) {
		this.valueColor = valueColor;
	}
}
