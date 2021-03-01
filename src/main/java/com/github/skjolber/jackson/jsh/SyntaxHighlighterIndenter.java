package com.github.skjolber.jackson.jsh;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;

public class SyntaxHighlighterIndenter implements Indenter {

	private final Indenter delegate;
	protected SyntaxHighlighter highligheter;
	protected String valueColor;

	public SyntaxHighlighterIndenter(SyntaxHighlighter highligheter, Indenter delegate) {
		this.delegate = delegate;
		this.highligheter = highligheter;
	}
	
	@Override
	public void writeIndentation(JsonGenerator jg, int level) throws IOException {
		String color = highligheter.forWhitespace();
		jg.writeRaw(color);
		delegate.writeIndentation(jg, level);
		
		if(valueColor != null) {
			jg.writeRaw(valueColor);
		}
	}
	
	/**
	 * Set the color which is set after the indentation.
	 * 
	 * @param color color to set
	 */
	
	public void setValueColor(String color) {
		this.valueColor = color;
	}

	public void clearValueColor() {
		this.valueColor = null;
	}

	@Override
	public boolean isInline() {
		return delegate.isInline();
	}
		
}
