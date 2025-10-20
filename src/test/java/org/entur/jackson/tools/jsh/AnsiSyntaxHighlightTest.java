package org.entur.jackson.tools.jsh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnsiSyntaxHighlightTest {

	@Test
	public void testBackground() {
		assertTrue(AnsiSyntaxHighlight.isBackground(AnsiSyntaxHighlight.BACKGROUND_CYAN));
	}
}
