package com.github.skjolber.jsh;

import java.io.IOException;

import org.junit.Test;

public class JsonStreamContextListenerTest extends AbstractHighlighterTest {

	@Test
	public void testHighlistSingleLine() throws IOException {
		
		MyJsonStreamContextListener l = new MyJsonStreamContextListener();
		
		handle(l, l);
	}
}
