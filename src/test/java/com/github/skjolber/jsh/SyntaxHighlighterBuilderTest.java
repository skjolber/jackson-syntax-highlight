package com.github.skjolber.jsh;

import org.junit.Assert;
import org.junit.Test;

import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.AnsiSyntaxHightlight;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class SyntaxHighlighterBuilderTest {

	@Test
	public void testBuilderSingleColor() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(AnsiSyntaxHightlight.BLUE)
			.build();
		
		Assert.assertEquals(highlighter.forNumber(1), AnsiSyntaxHightlight.SANE + AnsiSyntaxHightlight.BLUE);
	}
	
	@Test
	public void testBuilderBackground() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(AnsiSyntaxHightlight.BLUE)
			.withBackground(AnsiSyntaxHightlight.BACKGROUND_BLACK)
			.build();
		
		Assert.assertEquals(highlighter.forNumber(1), AnsiSyntaxHightlight.SANE + AnsiSyntaxHightlight.BLUE + AnsiSyntaxHightlight.BACKGROUND_BLACK);
	}
}
