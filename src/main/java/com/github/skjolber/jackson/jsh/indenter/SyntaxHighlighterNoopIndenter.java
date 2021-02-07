package com.github.skjolber.jackson.jsh.indenter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

import java.io.IOException;

/**
 * An indenter that prints json to a single line (un-prettified) and maintains the ANSI text colors
 */
public class SyntaxHighlighterNoopIndenter extends DefaultPrettyPrinter.NopIndenter implements SyntaxHighlighterStyleIndenter {

    private static final long serialVersionUID = 1L;

    protected SyntaxHighlighter highligheter;
    protected String valueColor;

    public SyntaxHighlighterNoopIndenter(SyntaxHighlighter highlighter) {
        this.highligheter = highlighter;
    }

    @Override
    public void writeIndentation(JsonGenerator jg, int level) throws IOException {
        String color = highligheter.forWhitespace();
        jg.writeRaw(color);
        super.writeIndentation(jg, level);

        if (valueColor != null) {
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
