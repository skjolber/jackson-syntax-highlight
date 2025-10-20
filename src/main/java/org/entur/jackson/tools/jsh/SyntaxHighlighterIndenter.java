package org.entur.jackson.tools.jsh;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.util.DefaultPrettyPrinter;

public class SyntaxHighlighterIndenter implements DefaultPrettyPrinter.Indenter {

	private final DefaultPrettyPrinter.Indenter delegate;
	protected SyntaxHighlighter highligheter;
	protected String valueColor;

	public SyntaxHighlighterIndenter(SyntaxHighlighter highligheter, DefaultPrettyPrinter.Indenter delegate) {
		this.delegate = delegate;
		this.highligheter = highligheter;
	}

	@Override
	public void writeIndentation(JsonGenerator jg, int level)  {
		String color = highligheter.forWhitespace();
		jg.writeRaw(color);
		delegate.writeIndentation(jg, level);

		if (valueColor != null) {
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
