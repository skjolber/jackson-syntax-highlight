package com.github.skjolber.jsh;

import org.junit.Assert;
import org.junit.Test;

import com.github.skjolber.jackson.jsh.AnsiSyntaxHightlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class SyntaxHighlighterBuilderTest {

	@Test
	public void testBuilderSingleColor() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(AnsiSyntaxHightlight.BLUE)
			.build();
		
		Assert.assertEquals(AnsiSyntaxHightlight.ESC_START + AnsiSyntaxHightlight.CLEAR + AnsiSyntaxHightlight.SEPERATOR + AnsiSyntaxHightlight.BLUE + AnsiSyntaxHightlight.ESC_END, highlighter.forNumber(1));
	}
	
	@Test
	public void testBuilderForegroundBackground() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(AnsiSyntaxHightlight.BLUE)
			.withBackground(AnsiSyntaxHightlight.BACKGROUND_BLACK)
			.build();
		
		Assert.assertEquals(highlighter.forNumber(1), AnsiSyntaxHightlight.ESC_START + AnsiSyntaxHightlight.CLEAR + AnsiSyntaxHightlight.SEPERATOR + AnsiSyntaxHightlight.BLUE + AnsiSyntaxHightlight.SEPERATOR + AnsiSyntaxHightlight.BACKGROUND_BLACK + AnsiSyntaxHightlight.ESC_END);
	}
	
	@Test
	public void testBuilderBackground() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withBackground(AnsiSyntaxHightlight.BACKGROUND_BLACK)
			.build();
		
		Assert.assertEquals(AnsiSyntaxHightlight.ESC_START + AnsiSyntaxHightlight.CLEAR + AnsiSyntaxHightlight.SEPERATOR + AnsiSyntaxHightlight.BACKGROUND_BLACK + AnsiSyntaxHightlight.ESC_END, highlighter.forNumber(1));
	}
}
