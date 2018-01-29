package com.github.skjolber.jsh;

import org.junit.Assert;
import org.junit.Test;

import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.Hightlight;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class SyntaxHighlighterBuilderTest {

	@Test
	public void testBuilderSingleColor() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(Hightlight.BLUE)
			.build();
		
		Assert.assertEquals(highlighter.forNumber(), Hightlight.SANE + Hightlight.BLUE);
	}
	
	@Test
	public void testBuilderBackground() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(Hightlight.BLUE)
			.withBackground(Hightlight.BACKGROUND_BLACK)
			.build();
		
		Assert.assertEquals(highlighter.forNumber(), Hightlight.SANE + Hightlight.BLUE + Hightlight.BACKGROUND_BLACK);
	}
}
