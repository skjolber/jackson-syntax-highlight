package com.github.skjolber.jackson.jsh.indenter;

import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

/**
 * @author Olatunji O. Longe
 * @since 07 Feb, 2021, 3:00 a.m.
 */
public enum SyntaxHighlighterStyle {

    PRETTIFIED, MINIFIED;

    public SyntaxHighlighterStyleIndenter getIndenter(SyntaxHighlighter highlighter) {
        if (this == SyntaxHighlighterStyle.MINIFIED) {
            return new SyntaxHighlighterNoopIndenter(highlighter);
        }
        return new SyntaxHighlighterObjectIndenter(highlighter);
    }
}
