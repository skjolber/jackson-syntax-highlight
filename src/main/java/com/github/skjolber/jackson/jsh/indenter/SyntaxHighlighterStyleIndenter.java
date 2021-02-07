package com.github.skjolber.jackson.jsh.indenter;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;

/**
 * @author Olatunji O. Longe
 * @since 07 Feb, 2021, 2:39 a.m.
 */
public interface SyntaxHighlighterStyleIndenter extends Indenter {

    void setValueColor(String color);

    void clearValueColor();

}
