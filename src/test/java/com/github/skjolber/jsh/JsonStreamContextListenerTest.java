package com.github.skjolber.jsh;

import java.io.IOException;

import com.github.skjolber.jackson.jsh.indenter.SyntaxHighlighterStyle;
import org.junit.Test;

public class JsonStreamContextListenerTest extends AbstractHighlighterTest {

	@Test
	public void testHighlightSubtreePrettified() throws IOException {
		
		SubtreeJsonStreamContextListener l = new SubtreeJsonStreamContextListener();

		handle(l, l);
	}

	@Test
	public void testHighlightSubtreeMinified() throws IOException {

		SubtreeJsonStreamContextListener l = new SubtreeJsonStreamContextListener(false);

		handle(l, l);
	}
}
