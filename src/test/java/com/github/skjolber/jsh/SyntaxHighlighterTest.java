package com.github.skjolber.jsh;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.skjolber.jackson.jsh.Hightlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlightingJsonGenerator;

public class SyntaxHighlighterTest {

	@Before
	public void before() {
		System.out.println(Hightlight.SANE);
		System.out.flush();
	}
	
	@Test
	public void curlyBrackets() throws IOException {
		System.out.println("curlyBrackets");
		handle(DefaultSyntaxHighlighter.newBuilder().withCurlyBrackets(Hightlight.RED).build());
	}

	@Test
	public void squareBrackets() throws IOException {
		System.out.println("squareBrackets");
		handle(DefaultSyntaxHighlighter.newBuilder().withSquareBrackets(Hightlight.RED).build());
	}

	@Test
	public void comma() throws IOException {
		System.out.println("comma");
		handle(DefaultSyntaxHighlighter.newBuilder().withComma(Hightlight.RED).build());
	}
	
	@Test
	public void colon() throws IOException {
		System.out.println("colon");
		handle(DefaultSyntaxHighlighter.newBuilder().withColon(Hightlight.RED).build());
	}
	
	@Test
	public void whitespace() throws IOException {
		System.out.println("whitespace");
		handle(DefaultSyntaxHighlighter.newBuilder().withWhitespace(Hightlight.BACKGROUND_RED).build());
	}
	
	@Test
	public void number() throws IOException {
		System.out.println("number");
		handle(DefaultSyntaxHighlighter.newBuilder().withNumber(Hightlight.RED).build());
	}	
	
	@Test
	public void string() throws IOException {
		System.out.println("string");
		handle(DefaultSyntaxHighlighter.newBuilder().withString(Hightlight.RED).build());
	}		
	
	@Test
	public void bool() throws IOException {
		System.out.println("bool");
		handle(DefaultSyntaxHighlighter.newBuilder().withBoolean(Hightlight.RED).build());
	}

	@Test
	public void binary() throws IOException {
		System.out.println("binary");
		handle(DefaultSyntaxHighlighter.newBuilder().withBinary(Hightlight.RED).build());
	}
	
	@Test
	public void testNull() throws IOException {
		System.out.println("null");
		handle(DefaultSyntaxHighlighter.newBuilder().withNull(Hightlight.RED).build());
	}
	
	@Test
	public void testBackground() throws IOException {
		System.out.println("background");
		handle(DefaultSyntaxHighlighter.newBuilder().withBackground(Hightlight.BACKGROUND_RED).build());
	}

	@Test
	public void all() throws IOException {
		System.out.println("all");
		handle(new DefaultSyntaxHighlighter());
	}

	public void handle(DefaultSyntaxHighlighter h) throws IOException {
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
