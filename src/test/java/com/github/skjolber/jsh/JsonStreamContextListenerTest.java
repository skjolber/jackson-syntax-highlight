package com.github.skjolber.jsh;

import java.io.IOException;

import org.junit.Test;

public class JsonStreamContextListenerTest extends AbstractHighlighterTest {

	@Test
	public void testHighlistSubtree() throws IOException {
		
		SubtreeJsonStreamContextListener l = new SubtreeJsonStreamContextListener();
		
		handle(l, l);
	}
}
