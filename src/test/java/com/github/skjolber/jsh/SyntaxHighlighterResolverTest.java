package com.github.skjolber.jsh;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.skjolber.jackson.jsh.SyntaxHighlightingJsonGenerator;

public class SyntaxHighlighterResolverTest {

	@Test
	public void testResolver() throws IOException {
		
		MySyntaxHighlighterResolver resolver = new MySyntaxHighlighterResolver();
		
		StringWriter writer = new StringWriter();
		
		JsonGenerator delegate = new JsonFactory().createGenerator(writer);

		SyntaxHighlightingJsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate, resolver);
		
		jsonGenerator.writeStartObject(); 

		MyObject object = new MyObject();
		object.setNumber(123);
		object.setString("string");

		jsonGenerator.setCodec(new ObjectMapper());
		
		jsonGenerator.writeObjectField("object", object);
		
		jsonGenerator.writeFieldName("arrayValue");
		jsonGenerator.writeStartArray();
		jsonGenerator.writeNumber(2);
		jsonGenerator.writeNumber(3);
		jsonGenerator.writeNumber(4);
		jsonGenerator.writeEndArray();

		jsonGenerator.writeFieldName("booleanValue");
		jsonGenerator.writeBoolean(true);

		jsonGenerator.writeFieldName("nullValue");
		jsonGenerator.writeNull();

		jsonGenerator.writeFieldName("stringValue");
		jsonGenerator.writeString("string");

		jsonGenerator.writeFieldName("binaryValue");
		jsonGenerator.writeBinary(new byte[] {0x01, 0x02, 0x03});

		jsonGenerator.writeEndObject();
		
		jsonGenerator.close();

		System.out.println(writer);
	}
}
