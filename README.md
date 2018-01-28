[![Build Status](https://travis-ci.org/skjolber/jackson-syntax-highlight.svg?branch=master)](https://travis-ci.org/skjolber/jackson-syntax-highlight)

# jackson-syntax-highlight
Simple utility for syntax highlighting for JSON output in Java. Enables color output in console via ANSI color codes.

Features: 
  * works with the popular Jackson JSON library.
  * configurable color schemes
     * datatype
       * string
       * number
       * boolean
       * null
     * field name
     * comma
     * brackets
     * colon
     * whitespace
       

Primarily intended for adding coloring while doing minimal changes to existing applications. 

## License
[Apache 2.0]

# Obtain
The project is based on [Maven] and is available at central Maven repository.

```xml
<dependency>
    <groupId>com.github.skjolber.jackson</groupId>
    <artifactId>jackson-highlight</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

# Usage
The highlighter wraps a normal `JsonGenerator`. Per default pretty-printing is enabled.

```java
// construct output generator
JsonGenerator delegate = new JsonFactory().createGenerator(writer);

// wrap with syntax highlighter
JsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate);

// write JSON output
jsonGenerator.writeStartObject(); // start root object
jsonGenerator.writeFieldName("name");
jsonGenerator.writeNumber(123);
jsonGenerator.writeEndObject();
// .. etc
```
If you prefer to configure the colors yourself, supply an instance of `SyntaxHighlighter` using the builder:

```java
SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
			.newBuilder()
			.withNumber(SyntaxHighlighter.ANSI_BLUE)
			.build();
		
JsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate, highlighter);
```


# History

 - [1.0.0]: Initial version

[Apache 2.0]:          	http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:       	https://github.com/skjolber/jackson-syntax-highlight/issues
[Maven]:                http://maven.apache.org/
[1.0.0]:				https://github.com/skjolber/jackson-syntax-highlight/releases/tag/jackson-syntax-highlight-1.0.0