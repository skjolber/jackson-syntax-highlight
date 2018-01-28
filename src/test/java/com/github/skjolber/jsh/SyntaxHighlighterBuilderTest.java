package com.github.skjolber.jsh;

import org.junit.Assert;
import org.junit.Test;

import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class SyntaxHighlighterBuilderTest {

	@Test
	public void testBuilder() {
		SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(DefaultSyntaxHighlighter.ANSI_BLUE)
			.build();
		
		Assert.assertEquals(highlighter.forNumber(), DefaultSyntaxHighlighter.ANSI_BLUE);
	
	}
}
