package com.github.skjolber.jsh;

import org.junit.Assert;
import org.junit.Test;

import com.github.skjolber.jackson.jsh.highlighter.AnsiSyntaxHighlight;
import com.github.skjolber.jackson.jsh.highlighter.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.highlighter.SyntaxHighlighter;

public class SyntaxHighlighterBuilderTest {

	@Test
	public void testBuilderSingleColor() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(AnsiSyntaxHighlight.BLUE)
			.build();
		
		Assert.assertEquals(AnsiSyntaxHighlight.ESC_START + AnsiSyntaxHighlight.CLEAR + AnsiSyntaxHighlight.SEPERATOR + AnsiSyntaxHighlight.BLUE + AnsiSyntaxHighlight.ESC_END, highlighter.forNumber(1));
	}
	
	@Test
	public void testBuilderForegroundBackground() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(AnsiSyntaxHighlight.BLUE)
			.withBackground(AnsiSyntaxHighlight.BACKGROUND_BLACK)
			.build();
		
		Assert.assertEquals(highlighter.forNumber(1), AnsiSyntaxHighlight.ESC_START + AnsiSyntaxHighlight.CLEAR + AnsiSyntaxHighlight.SEPERATOR + AnsiSyntaxHighlight.BLUE + AnsiSyntaxHighlight.SEPERATOR + AnsiSyntaxHighlight.BACKGROUND_BLACK + AnsiSyntaxHighlight.ESC_END);
	}
	
	@Test
	public void testBuilderBackground() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withBackground(AnsiSyntaxHighlight.BACKGROUND_BLACK)
			.build();
		
		Assert.assertEquals(AnsiSyntaxHighlight.ESC_START + AnsiSyntaxHighlight.CLEAR + AnsiSyntaxHighlight.SEPERATOR + AnsiSyntaxHighlight.BACKGROUND_BLACK + AnsiSyntaxHighlight.ESC_END, highlighter.forNumber(1));
	}
}
