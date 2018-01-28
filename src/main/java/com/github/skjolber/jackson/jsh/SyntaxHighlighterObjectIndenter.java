package com.github.skjolber.jackson.jsh;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;

public class SyntaxHighlighterObjectIndenter extends DefaultIndenter {
	
	private static final long serialVersionUID = 1L;
	
	private SyntaxHighlighter highligheter;
	private String postColor;

	public SyntaxHighlighterObjectIndenter() {
		super();
	}

	public void setHighligheter(SyntaxHighlighter highligheter) {
		this.highligheter = highligheter;
	}
	
	@Override
	public void writeIndentation(JsonGenerator jg, int level) throws IOException {
		String color = highligheter.forWhitespace();
		jg.writeRaw(color);
		super.writeIndentation(jg, level);
		
		if(postColor != null) {
			jg.writeRaw(postColor);
		}
	}
	
	public void setPostColor(String postColor) {
		this.postColor = postColor;
	}

	public void clearPostColor() {
		this.postColor = null;
	}
	
}
