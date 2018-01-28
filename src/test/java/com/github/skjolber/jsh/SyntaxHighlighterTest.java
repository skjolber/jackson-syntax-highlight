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
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlightingJsonGenerator;

public class SyntaxHighlighterTest {

	@Before
	public void before() {
		System.out.println(SyntaxHighlighter.ANSI_RESET);
	}
	
	@Test
	public void curlyBrackets() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withCurlyBrackets(SyntaxHighlighter.ANSI_RED).build());
	}

	@Test
	public void squareBrackets() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withSquareBrackets(SyntaxHighlighter.ANSI_RED).build());
	}

	@Test
	public void comma() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withComma(SyntaxHighlighter.ANSI_RED).build());
	}
	
	@Test
	public void colon() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withColon(SyntaxHighlighter.ANSI_RED).build());
	}
	
	@Test
	public void whitespace() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withWhitespace(SyntaxHighlighter.ANSI_RED_BACKGROUND).build());
	}
	
	@Test
	public void number() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withNumber(SyntaxHighlighter.ANSI_RED_BACKGROUND).build());
	}	
	
	@Test
	public void string() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withString(SyntaxHighlighter.ANSI_RED_BACKGROUND).build());
	}		
	
	@Test
	public void bool() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withBoolean(SyntaxHighlighter.ANSI_RED_BACKGROUND).build());
	}

	@Test
	public void binary() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withBinary(SyntaxHighlighter.ANSI_RED_BACKGROUND).build());
	}
	
	@Test
	public void testNull() throws IOException {
		handle(DefaultSyntaxHighlighter.newBuilder().withNull(SyntaxHighlighter.ANSI_RED_BACKGROUND).build());
	}

	@Test
	public void all() throws IOException {
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

		jsonGenerator.writeEndObject();
		
		jsonGenerator.close();

		System.out.println(writer);
	}
}
