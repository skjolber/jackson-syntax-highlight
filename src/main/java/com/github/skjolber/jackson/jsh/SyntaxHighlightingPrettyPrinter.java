package com.github.skjolber.jackson.jsh;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;

public class SyntaxHighlightingPrettyPrinter implements PrettyPrinter {

	protected static final String ANSI_RESET = "\u001B[0m";
	
	private PrettyPrinter prettyPrinter;
	private String color;
	private final String base;

	public SyntaxHighlightingPrettyPrinter(PrettyPrinter prettyPrinter) {
		this(prettyPrinter, ANSI_RESET);
	}

	public SyntaxHighlightingPrettyPrinter(PrettyPrinter prettyPrinter, String base) {
		super();
		this.prettyPrinter = prettyPrinter;
		this.base = base;
	}

	public void writeRootValueSeparator(JsonGenerator gen) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.writeRootValueSeparator(gen);
	}

	public void writeStartObject(JsonGenerator gen) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.writeStartObject(gen);
	}

	public void writeEndObject(JsonGenerator gen, int nrOfEntries) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.writeEndObject(gen, nrOfEntries);
	}

	public void writeObjectEntrySeparator(JsonGenerator gen) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.writeObjectEntrySeparator(gen);
		if(color != null) {
			gen.writeRaw(color);
		}
	}

	public void writeObjectFieldValueSeparator(JsonGenerator gen) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.writeObjectFieldValueSeparator(gen);
		if(color != null) {
			gen.writeRaw(color);
		}
	}

	public void writeStartArray(JsonGenerator gen) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.writeStartArray(gen);
	}

	public void writeEndArray(JsonGenerator gen, int nrOfValues) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.writeEndArray(gen, nrOfValues);
	}

	public void writeArrayValueSeparator(JsonGenerator gen) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.writeArrayValueSeparator(gen);
		if(color != null) {
			gen.writeRaw(color);
		}		
	}

	public void beforeArrayValues(JsonGenerator gen) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.beforeArrayValues(gen);
		if(color != null) {
			gen.writeRaw(color);
		}		
	}

	public void beforeObjectEntries(JsonGenerator gen) throws IOException {
		gen.writeRaw(base);
		prettyPrinter.beforeObjectEntries(gen);
		if(color != null) {
			gen.writeRaw(color);
		}		
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void clearColor() {
		this.color = null;
	}
}
