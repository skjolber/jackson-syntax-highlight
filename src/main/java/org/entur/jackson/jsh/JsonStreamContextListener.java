package org.entur.jackson.jsh;

import tools.jackson.core.TokenStreamContext;
import tools.jackson.core.json.JsonWriteContext;

public interface JsonStreamContextListener {

	void startObject(TokenStreamContext outputContext);

	void endObject(TokenStreamContext outputContext);

	void startArray(TokenStreamContext outputContext);

	void endArray(TokenStreamContext outputContext);

}
