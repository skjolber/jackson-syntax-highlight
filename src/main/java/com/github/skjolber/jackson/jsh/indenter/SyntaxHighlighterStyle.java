package com.github.skjolber.jackson.jsh.indenter;

import com.github.skjolber.jackson.jsh.highlighter.SyntaxHighlighter;

/**
 * @author Olatunji O. Longe
 * @since 07 Feb, 2021, 3:00 a.m.
 */
public class SyntaxHighlighterStyle {

    public static SyntaxHighlighterStyleIndenter getIndenter(SyntaxHighlighter highlighter) {
        if(Boolean.parseBoolean(highlighter.forPretty())){
            return new SyntaxHighlighterObjectIndenter(highlighter);
        }
        return new SyntaxHighlighterNoopIndenter(highlighter);
    }
}
