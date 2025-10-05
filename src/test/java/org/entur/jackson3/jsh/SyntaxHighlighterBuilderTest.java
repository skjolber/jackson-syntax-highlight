package org.entur.jackson3.jsh;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.entur.jackson3.jsh.AnsiSyntaxHighlight;
import org.entur.jackson3.jsh.DefaultSyntaxHighlighter;
import org.entur.jackson3.jsh.SyntaxHighlighter;

public class SyntaxHighlighterBuilderTest {

	@Test
	public void testBuilderSingleColor() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(AnsiSyntaxHighlight.BLUE)
			.build();
		
		assertEquals(AnsiSyntaxHighlight.ESC_START + AnsiSyntaxHighlight.CLEAR + AnsiSyntaxHighlight.SEPERATOR + AnsiSyntaxHighlight.BLUE + AnsiSyntaxHighlight.ESC_END, highlighter.forNumber(1));
	}
	
	@Test
	public void testBuilderForegroundBackground() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(AnsiSyntaxHighlight.BLUE)
			.withBackground(AnsiSyntaxHighlight.BACKGROUND_BLACK)
			.build();
		
		assertEquals(highlighter.forNumber(1), AnsiSyntaxHighlight.ESC_START + AnsiSyntaxHighlight.CLEAR + AnsiSyntaxHighlight.SEPERATOR + AnsiSyntaxHighlight.BLUE + AnsiSyntaxHighlight.SEPERATOR + AnsiSyntaxHighlight.BACKGROUND_BLACK + AnsiSyntaxHighlight.ESC_END);
	}
	
	@Test
	public void testBuilderBackground() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withBackground(AnsiSyntaxHighlight.BACKGROUND_BLACK)
			.build();
		
		assertEquals(AnsiSyntaxHighlight.ESC_START + AnsiSyntaxHighlight.CLEAR + AnsiSyntaxHighlight.SEPERATOR + AnsiSyntaxHighlight.BACKGROUND_BLACK + AnsiSyntaxHighlight.ESC_END, highlighter.forNumber(1));
	}
}
