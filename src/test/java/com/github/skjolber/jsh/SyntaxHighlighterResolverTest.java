package com.github.skjolber.jsh;

import java.io.IOException;

import org.junit.Test;

public class SyntaxHighlighterResolverTest extends AbstractHighlighterTest {

	@Test
	public void testResolver() throws IOException {
		handle(new MySyntaxHighlighterResolver());
	}
}
