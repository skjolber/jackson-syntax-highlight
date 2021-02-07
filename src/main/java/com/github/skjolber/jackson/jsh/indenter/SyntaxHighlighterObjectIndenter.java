package com.github.skjolber.jackson.jsh.indenter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

import java.io.IOException;

/**
 * An indenter that prints prettified json and maintains the ANSI text colors
 */
public class SyntaxHighlighterObjectIndenter extends DefaultIndenter implements SyntaxHighlighterStyleIndenter {

	private static final long serialVersionUID = 1L;

	protected SyntaxHighlighter highlighter;
	protected String valueColor;

	public SyntaxHighlighterObjectIndenter(SyntaxHighlighter highlighter) {
		this.highlighter = highlighter;
	}

	@Override
	public void writeIndentation(JsonGenerator jg, int level) throws IOException {
		String color = highlighter.forWhitespace();
		jg.writeRaw(color);
		super.writeIndentation(jg, level);

		if(valueColor != null) {
			jg.writeRaw(valueColor);
		}
	}

	/**
	 * Set the color which is set after the indentation.
	 *
	 * @param color color to set
	 */
	@Override
	public void setValueColor(String color) {
		this.valueColor = color;
	}

	@Override
	public void clearValueColor() {
		this.valueColor = null;
	}

}
