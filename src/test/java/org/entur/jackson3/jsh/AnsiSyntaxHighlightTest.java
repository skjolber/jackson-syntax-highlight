package org.entur.jackson3.jsh;

import org.junit.jupiter.api.Test;

import org.entur.jackson3.jsh.AnsiSyntaxHighlight;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnsiSyntaxHighlightTest {

	@Test
	public void testBackground() {
		assertTrue(AnsiSyntaxHighlight.isBackground(AnsiSyntaxHighlight.BACKGROUND_CYAN));
	}
}
