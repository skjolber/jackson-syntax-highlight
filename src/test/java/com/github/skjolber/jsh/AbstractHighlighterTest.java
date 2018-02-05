package com.github.skjolber.jsh;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.skjolber.jackson.jsh.JsonStreamContextListener;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlightingJsonGenerator;

public abstract class AbstractHighlighterTest {

	public void handle(SyntaxHighlighter h, JsonStreamContextListener listener) throws IOException {
		StringWriter writer = new StringWriter();
		
		JsonGenerator delegate = new JsonFactory().createGenerator(writer);

		SyntaxHighlightingJsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate, h, listener);
		
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
		
		jsonGenerator.writeNumberField("bigDecimalField", BigDecimal.ONE);
		jsonGenerator.writeNumberField("floatField", Float.MAX_VALUE);
		jsonGenerator.writeNumberField("shortField", Short.MAX_VALUE);
		jsonGenerator.writeNumberField("longField", 1L);

		jsonGenerator.writeFieldName("bigInteger");
		jsonGenerator.writeNumber(BigInteger.ONE);

		jsonGenerator.writeFieldName("bigDecimal");
		jsonGenerator.writeNumber(BigDecimal.ONE);

		jsonGenerator.writeFieldName("float");
		jsonGenerator.writeNumber(Float.MAX_VALUE);

		jsonGenerator.writeFieldName("short");
		jsonGenerator.writeNumber(Short.MAX_VALUE);

		jsonGenerator.writeFieldName("long");
		jsonGenerator.writeNumber(1L);

		jsonGenerator.writeFieldName("double");
		jsonGenerator.writeNumber(1d);

		jsonGenerator.writeFieldName("encodedNumber");
		jsonGenerator.writeNumber("1");

		jsonGenerator.writeFieldName("string");
		jsonGenerator.writeString("string");

		
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
