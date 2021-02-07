package com.github.skjolber.jsh;

import java.io.IOException;

import com.github.skjolber.jackson.jsh.indenter.SyntaxHighlighterStyle;
import org.junit.Test;

public class JsonStreamContextListenerTest extends AbstractHighlighterTest {

	@Test
	public void testHighlistSubtreePrettified() throws IOException {
		
		SubtreeJsonStreamContextListener l = new SubtreeJsonStreamContextListener();

		handle(l, l, SyntaxHighlighterStyle.PRETTIFIED);
	}

	@Test
	public void testHighlistSubtreeMinified() throws IOException {

		SubtreeJsonStreamContextListener l = new SubtreeJsonStreamContextListener();

		handle(l, l, SyntaxHighlighterStyle.MINIFIED);
	}
}
