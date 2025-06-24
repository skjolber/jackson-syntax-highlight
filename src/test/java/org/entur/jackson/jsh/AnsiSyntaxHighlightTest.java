package org.entur.jackson.jsh;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.entur.jackson.jsh.AnsiSyntaxHighlight;

public class AnsiSyntaxHighlightTest {

	@Test
	public void testBackground() {
		assertTrue(AnsiSyntaxHighlight.isBackground(AnsiSyntaxHighlight.BACKGROUND_CYAN));
	}
}
