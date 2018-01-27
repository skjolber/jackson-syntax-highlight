package com.github.skjolber.jsh;

import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlightingJsonGenerator;

public class SyntaxHighlightingJsonGeneratorTest {

	@Test
	public void testGenerateJson() throws Exception {
		
		StringWriter writer = new StringWriter();
		
		JsonGenerator delegate = new JsonFactory().createGenerator(writer);
		
		SyntaxHighlightingJsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate, new DefaultSyntaxHighlighter(), new DefaultPrettyPrinter());
		
		jsonGenerator.writeStartObject(); // start root object
		jsonGenerator.writeFieldName("name");
		jsonGenerator.writeNumber(123);
		
		jsonGenerator.writeNumberField("abc", 1D);
		jsonGenerator.writeBooleanField("d", true);
		
		jsonGenerator.writeStringField("key", "value");
		
		jsonGenerator.writeFieldName("bac");
		jsonGenerator.writeStartArray();
		jsonGenerator.writeNumber(2);
		jsonGenerator.writeNumber(3);
		jsonGenerator.writeNumber(4);
		jsonGenerator.writeEndArray();

		jsonGenerator.writeFieldName("name");
		jsonGenerator.writeNull();

		MyObject object = new MyObject();
		object.setNumber(123);
		object.setString("string");
		
		jsonGenerator.setCodec(new ObjectMapper());
		
		jsonGenerator.writeObjectField("object", object);
		
		jsonGenerator.writeEndObject();
		
		jsonGenerator.close();
		
		System.out.println(writer);
	}
}
