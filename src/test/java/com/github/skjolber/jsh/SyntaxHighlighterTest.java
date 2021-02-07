package com.github.skjolber.jsh;

import java.io.IOException;

import com.github.skjolber.jackson.jsh.indenter.SyntaxHighlighterStyle;
import org.junit.Test;

import com.github.skjolber.jackson.jsh.AnsiSyntaxHighlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class SyntaxHighlighterTest extends AbstractHighlighterTest {

	@Test
	public void curlyBracketsPretty() throws IOException {
		System.out.println("curlyBrackets");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withCurlyBrackets(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void squareBracketsPretty() throws IOException {
		System.out.println("squareBrackets");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withSquareBrackets(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void commaPretty() throws IOException {
		System.out.println("comma");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withComma(AnsiSyntaxHighlight.RED).build());
	}
	
	@Test
	public void commaBackgroundPretty() throws IOException {
		System.out.println("comma");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withComma(AnsiSyntaxHighlight.BACKGROUND_RED).build());
	}
	
	@Test
	public void colonPretty() throws IOException {
		System.out.println("colon");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withColon(AnsiSyntaxHighlight.RED).build());
	}
	
	@Test
	public void whitespacePretty() throws IOException {
		System.out.println("whitespace");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withWhitespace(AnsiSyntaxHighlight.BACKGROUND_RED).build());
	}
	
	@Test
	public void numberPretty() throws IOException {
		System.out.println("number");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withNumber(AnsiSyntaxHighlight.RED).build());
	}	
	
	@Test
	public void stringPretty() throws IOException {
		System.out.println("string");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withString(AnsiSyntaxHighlight.RED).build());
	}		
	
	@Test
	public void boolPretty() throws IOException {
		System.out.println("bool");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withBoolean(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void binaryPretty() throws IOException {
		System.out.println("binary");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withBinary(AnsiSyntaxHighlight.RED).build());
	}
	
	@Test
	public void testNullPretty() throws IOException {
		System.out.println("null");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withNull(AnsiSyntaxHighlight.RED).build());
	}
	
	@Test
	public void testBackgroundPretty() throws IOException {
		System.out.println("background");
		handlePrettified(DefaultSyntaxHighlighter.newBuilder().withBackground(AnsiSyntaxHighlight.BACKGROUND_RED).build());
	}

	@Test
	public void allPretty() throws IOException {
		System.out.println("all");
		handlePrettified(new DefaultSyntaxHighlighter());
	}
	
	@Test
	public void singleLineBackgroundPretty() throws IOException {
		System.out.println("singleLine");
		handlePrettified(new SingleLineSyntaxHighlighter());
	}

	@Test
	public void curlyBracketsMini() throws IOException {
		System.out.println("curlyBrackets");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withCurlyBrackets(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void squareBracketsMini() throws IOException {
		System.out.println("squareBrackets");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withSquareBrackets(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void commaMini() throws IOException {
		System.out.println("comma");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withComma(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void commaBackgroundMini() throws IOException {
		System.out.println("comma");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withComma(AnsiSyntaxHighlight.BACKGROUND_RED).build());
	}

	@Test
	public void colonMini() throws IOException {
		System.out.println("colon");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withColon(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void whitespaceMini() throws IOException {
		System.out.println("whitespace");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withWhitespace(AnsiSyntaxHighlight.BACKGROUND_RED).build());
	}

	@Test
	public void numberMini() throws IOException {
		System.out.println("number");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withNumber(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void stringMini() throws IOException {
		System.out.println("string");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withString(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void boolMini() throws IOException {
		System.out.println("bool");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withBoolean(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void binaryMini() throws IOException {
		System.out.println("binary");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withBinary(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void testNullMini() throws IOException {
		System.out.println("null");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withNull(AnsiSyntaxHighlight.RED).build());
	}

	@Test
	public void testBackgroundMini() throws IOException {
		System.out.println("background");
		handleMinified(DefaultSyntaxHighlighter.newBuilder().withBackground(AnsiSyntaxHighlight.BACKGROUND_RED).build());
	}

	@Test
	public void allMini() throws IOException {
		System.out.println("all");
		handleMinified(new DefaultSyntaxHighlighter());
	}

	@Test
	public void singleLineBackgroundMini() throws IOException {
		System.out.println("singleLine");
		handleMinified(new SingleLineSyntaxHighlighter());
	}

	private void handlePrettified(SyntaxHighlighter h) throws IOException {
		super.handle(h, null, SyntaxHighlighterStyle.PRETTIFIED);
	}

	private void handleMinified(SyntaxHighlighter h) throws IOException {
		super.handle(h, null, SyntaxHighlighterStyle.MINIFIED);
	}

}
