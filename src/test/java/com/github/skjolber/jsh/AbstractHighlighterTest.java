package com.github.skjolber.jsh;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.skjolber.jackson.jsh.SyntaxHighlighterResolver;
import com.github.skjolber.jackson.jsh.SyntaxHighlightingJsonGenerator;

public abstract class AbstractHighlighterTest {

	public void handle(SyntaxHighlighterResolver h) throws IOException {
		StringWriter writer = new StringWriter();
		
		JsonGenerator delegate = new JsonFactory().createGenerator(writer);

		SyntaxHighlightingJsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate, h);
		
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

		jsonGenerator.writeFieldName("binaryValue");
		jsonGenerator.writeBinary(new byte[] {0x01, 0x02, 0x03});

		jsonGenerator.writeFieldName("intArray");
		jsonGenerator.writeArray(new int[] {0, 1, 2}, 0, 3);

		jsonGenerator.writeFieldName("doubleArray");
		jsonGenerator.writeArray(new double[] {0.0, 0.1, 0.2}, 0, 3);

		jsonGenerator.writeFieldName("longArray");
		jsonGenerator.writeArray(new long[] {0L, 1L, 2L}, 0, 3);

		jsonGenerator.writeEndObject();
		
		jsonGenerator.close();

		System.out.println(writer);
	}

}