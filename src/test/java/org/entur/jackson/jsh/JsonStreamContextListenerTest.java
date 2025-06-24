package org.entur.jackson.jsh;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonStreamContextListenerTest extends AbstractHighlighterTest {

	@Test
	public void testHighlistSubtree() throws IOException {
		
		SubtreeJsonStreamContextListener l = new SubtreeJsonStreamContextListener();
		
		handle(l, l);
	}
}
