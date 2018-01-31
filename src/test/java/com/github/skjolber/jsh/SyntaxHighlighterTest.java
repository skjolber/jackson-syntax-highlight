package com.github.skjolber.jsh;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.Hightlight;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class SyntaxHighlighterTest extends AbstractHighlighterTest {

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
	public void commaBackground() throws IOException {
		System.out.println("comma");
		handle(DefaultSyntaxHighlighter.newBuilder().withComma(Hightlight.BACKGROUND_RED).build());
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
	
	@Test
	public void singleLineBackground() throws IOException {
		System.out.println("singleLine");
		handle(new SingleLineSyntaxHighlighter());
	}

	private void handle(SyntaxHighlighter h) throws IOException {
		super.handle(h, null);
	}

}
