package org.entur.jackson3.jsh;

import org.junit.jupiter.api.Test;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.core.PrettyPrinter;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.databind.ObjectWriter;
import tools.jackson.databind.json.JsonMapper;

import java.io.StringWriter;

/**
 * Code used in readme.
 */


public class ReadmeTest {

    private ObjectWriteContext objectWriteContext(PrettyPrinter pp) {
        return new ObjectWriteContext.Base() {
            @Override
            public PrettyPrinter getPrettyPrinter() { return pp; }
        };
    }

    @Test
    public void constructViaJsonMapper() {
        DefaultSyntaxHighlighter syntaxHighlighter = DefaultSyntaxHighlighter.newBuilder()
                .withNumber(AnsiSyntaxHighlight.RED)
                .withString(AnsiSyntaxHighlight.BLUE)
                .build();

        JsonMapper mapper = JsonMapper.builderWithJackson2Defaults()
                .defaultPrettyPrinter(new SyntaxHighlightingPrettyPrinter(syntaxHighlighter))
                .build();
        ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();

        StringWriter writer = new StringWriter();

        JsonGenerator delegate = objectWriter.createGenerator(writer);

        SyntaxHighlightingJsonGenerator wrap = SyntaxHighlightingJsonGenerator.create(delegate);

        MyObject object = new MyObject();
        object.setNumber(123);
        object.setString("value");

        wrap.writePOJO(object);

        wrap.close();

        System.out.println(writer);
    }
    @Test

    public void constructViaObjectWriteContext() {
        DefaultSyntaxHighlighter syntaxHighlighter = DefaultSyntaxHighlighter.newBuilder()
                .withNumber(AnsiSyntaxHighlight.RED)
                .withString(AnsiSyntaxHighlight.BLUE)
                .build();

        JsonFactory jsonFactory = new JsonFactory();

        SyntaxHighlightingPrettyPrinter prettyPrinter = new SyntaxHighlightingPrettyPrinter(syntaxHighlighter);

        StringWriter writer = new StringWriter();

        JsonGenerator delegate = jsonFactory.createGenerator(objectWriteContext(prettyPrinter), writer);

        SyntaxHighlightingJsonGenerator wrap = SyntaxHighlightingJsonGenerator.create(delegate);

        wrap.writeStartObject();
        wrap.writeNumberProperty("number", 123);
        wrap.writeStringProperty("string", "value");
        wrap.writeEndObject();

        wrap.close();

        System.out.println(writer);
    }

}
