package com.github.skjolber.jackson.jsh.indenter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.FixedSpaceIndenter;
import com.github.skjolber.jackson.jsh.highlighter.SyntaxHighlighter;

import java.io.IOException;

public class SyntaxHighlighterArrayIndenter extends FixedSpaceIndenter {

	private static final long serialVersionUID = 1L;

	protected SyntaxHighlighter highligheter;

	protected String valueColor;

	public SyntaxHighlighterArrayIndenter() {
		super();
	}

	public SyntaxHighlighterArrayIndenter(SyntaxHighlighter highligheter) {
		this.highligheter = highligheter;
	}

	@Override
	public void writeIndentation(JsonGenerator jg, int level) throws IOException {
		String color = highligheter.forWhitespace();
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

	public void setValueColor(String color) {
		this.valueColor = color;
	}

	public void clearValueColor() {
		this.valueColor = null;
	}
}
