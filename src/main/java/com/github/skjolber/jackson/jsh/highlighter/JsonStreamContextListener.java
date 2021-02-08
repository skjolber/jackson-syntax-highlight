package com.github.skjolber.jackson.jsh;

import com.fasterxml.jackson.core.JsonStreamContext;

public interface JsonStreamContextListener {

	void startObject(JsonStreamContext outputContext);
	void endObject(JsonStreamContext outputContext);
	
	void startArray(JsonStreamContext outputContext);
	void endArray(JsonStreamContext outputContext);

}
