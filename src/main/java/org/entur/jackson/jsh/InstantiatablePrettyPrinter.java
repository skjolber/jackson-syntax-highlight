package org.entur.jackson.jsh;

import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;

public class InstantiatablePrettyPrinter extends DefaultPrettyPrinter {

    private final SyntaxHighlighter syntaxHighlighter;

    public InstantiatablePrettyPrinter(SyntaxHighlighter syntaxHighlighter) {
        this.syntaxHighlighter = syntaxHighlighter;
    }

    public DefaultPrettyPrinter createInstance() {
        SyntaxHighlighterIndenter objectIndenter = new SyntaxHighlighterIndenter(syntaxHighlighter, new DefaultIndenter());
        SyntaxHighlighterIndenter arrayIndenter = new SyntaxHighlighterIndenter(syntaxHighlighter, new DefaultPrettyPrinter.FixedSpaceIndenter());

        return new SyntaxHighlightingPrettyPrinter(syntaxHighlighter, objectIndenter, arrayIndenter, null);
    }
}
