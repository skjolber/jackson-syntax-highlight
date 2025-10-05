package org.entur.jackson3.jsh;

import tools.jackson.core.TokenStreamContext;

public interface TokenStreamContextListener {

	void startObject(TokenStreamContext outputContext);

	void endObject(TokenStreamContext outputContext);

	void startArray(TokenStreamContext outputContext);

	void endArray(TokenStreamContext outputContext);

}
