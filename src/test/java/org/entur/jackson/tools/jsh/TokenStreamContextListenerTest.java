package org.entur.jackson.tools.jsh;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenStreamContextListenerTest extends AbstractHighlighterTest {

	@Test
	public void testHighlistSubtree() throws IOException {
		
		SubtreeTokenStreamContextListener l = new SubtreeTokenStreamContextListener();
		
		handle(l, l);
	}
}
