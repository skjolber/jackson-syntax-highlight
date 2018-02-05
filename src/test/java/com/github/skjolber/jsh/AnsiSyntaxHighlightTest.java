package com.github.skjolber.jsh;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.skjolber.jackson.jsh.AnsiSyntaxHighlight;

public class AnsiSyntaxHighlightTest {

	@Test
	public void testBackground() {
		assertTrue(AnsiSyntaxHighlight.isBackground(AnsiSyntaxHighlight.BACKGROUND_CYAN));
	}
}
