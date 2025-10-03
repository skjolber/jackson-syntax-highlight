package org.entur.jackson.jsh;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

import tools.jackson.core.JsonGenerator;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.PrettyPrinter;
import tools.jackson.core.TokenStreamFactory;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.json.JsonFactoryBuilder;
import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.core.util.JsonGeneratorDecorator;
import tools.jackson.databind.ObjectWriter;
import tools.jackson.databind.cfg.GeneratorSettings;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractHighlighterTest {

	private ObjectWriteContext objectWriteContext(PrettyPrinter pp) {
		return new ObjectWriteContext.Base() {
			@Override
			public PrettyPrinter getPrettyPrinter() { return pp; }
		};
	}

	public void handle(SyntaxHighlighter syntaxHighlighter, JsonStreamContextListener listener) throws IOException {

		InstantiatablePrettyPrinter instantiatablePrettyPrinter = new InstantiatablePrettyPrinter(syntaxHighlighter);

		JsonFactory jsonFactory = JsonFactory.builder().build();
		SyntaxHighlightingPrettyPrinter prettyPrinter = (SyntaxHighlightingPrettyPrinter) instantiatablePrettyPrinter.createInstance();
		ObjectWriteContext objectWriteContext = objectWriteContext(prettyPrinter);

		StringWriter writer = new StringWriter();
		JsonGenerator delegate = jsonFactory.createGenerator(objectWriteContext, writer);

		SyntaxHighlightingJsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate, prettyPrinter, prettyPrinter.getObjectIndenter(), prettyPrinter.getArrayIndenter(), prettyPrinter.getSyntaxHighlighter());
		write(jsonGenerator);
		System.out.println(writer);
	}

	private void write(SyntaxHighlightingJsonGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeStartObject(); 

		MyObject object = new MyObject();
		object.setNumber(123);
		object.setString("myString");

		// jsonGenerator.setCodec(new ObjectMapper());
		// jsonGenerator.writeObjectField("object", object);
		
		jsonGenerator.writeName("arrayValue");
		jsonGenerator.writeStartArray();
		jsonGenerator.writeNumber(2);
		jsonGenerator.writeNumber(3);
		jsonGenerator.writeNumber(4);
		jsonGenerator.writeEndArray();

		jsonGenerator.writeName("booleanValue");
		jsonGenerator.writeBoolean(true);

		jsonGenerator.writeName("nullValue");
		jsonGenerator.writeNull();
		
		jsonGenerator.writeNumberProperty("bigDecimalField", BigDecimal.ONE);
		jsonGenerator.writeNumberProperty("floatField", Float.MAX_VALUE);
		jsonGenerator.writeNumberProperty("shortField", Short.MAX_VALUE);
		jsonGenerator.writeNumberProperty("longField", 1L);

		jsonGenerator.writeName("bigInteger");
		jsonGenerator.writeNumber(BigInteger.ONE);

		jsonGenerator.writeName("bigDecimal");
		jsonGenerator.writeNumber(BigDecimal.ONE);

		jsonGenerator.writeName("float");
		jsonGenerator.writeNumber(Float.MAX_VALUE);

		jsonGenerator.writeName("short");
		jsonGenerator.writeNumber(Short.MAX_VALUE);

		jsonGenerator.writeName("long");
		jsonGenerator.writeNumber(1L);

		jsonGenerator.writeName("double");
		jsonGenerator.writeNumber(1d);

		jsonGenerator.writeName("encodedNumber");
		jsonGenerator.writeNumber("1");

		jsonGenerator.writeName("string");
		jsonGenerator.writeString("myString");

		
		jsonGenerator.writeName("binaryValue");
		jsonGenerator.writeBinary(new byte[] {0x01, 0x02, 0x03});

		jsonGenerator.writeName("intArray");
		jsonGenerator.writeArray(new int[] {0, 1, 2}, 0, 3);

		jsonGenerator.writeName("doubleArray");
		jsonGenerator.writeArray(new double[] {0.0, 0.1, 0.2}, 0, 3);

		jsonGenerator.writeName("longArray");
		jsonGenerator.writeArray(new long[] {0L, 1L, 2L}, 0, 3);

		jsonGenerator.writeEndObject();
		
		jsonGenerator.close();
	}

}
