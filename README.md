[![Build Status](https://travis-ci.org/skjolber/jackson-syntax-highlight.svg?branch=master)](https://travis-ci.org/skjolber/jackson-syntax-highlight)

# jackson-syntax-highlight
Simple utility for generating syntax-highlighted [JSON] text. Inlines [ANSI] color-codes visible in ANSI-enabled consoles.

Features: 
  * works with the popular [Jackson] JSON library.
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

Primarily intended for adding coloring while doing minimal changes to existing applications. For example, coloring of status codes during unit testing.

## License
[Apache 2.0]

# Obtain
The project is based on [Maven] and is available at central Maven repository.

```xml
<dependency>
    <groupId>com.github.skjolber.jackson</groupId>
    <artifactId>jackson-syntax-highlight</artifactId>
    <version>1.0.4</version>
</dependency>
```

# Usage
The highlighter wraps a normal [JsonGenerator]. Per default pretty-printing is enabled.

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
                                    .withNumber(AnsiSyntaxHighlight.BLUE)
                                    .build();
		
JsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate, highlighter);
```

In addition, the JSON structure can be tracked via [JsonStreamContextListener](src/main/java/com/github/skjolber/jackson/jsh/JsonStreamContextListener.java), for stateful coloring of subtrees. 

# History

 - [1.0.4]: Bump Jackson dependency due to security issue 
 - 1.0.3: Bump Jackson dependency due to security issue 
 - 1.0.2: More tests, minor fixes.
 - 1.0.1: Various improvements, works better with [logback-logstash-syntax-highlighting-decorators] for Logback logging.
 - 1.0.0: Initial version

[Apache 2.0]:          	http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:       	https://github.com/skjolber/jackson-syntax-highlight/issues
[Maven]:                http://maven.apache.org/
[1.0.4]:				https://github.com/skjolber/jackson-syntax-highlight/releases/tag/jackson-syntax-highlight-1.0.4
[SyntaxHighlighter]:	src/main/java/com/github/skjolber/jackson/jsh/SyntaxHighlighter.java
[Jackson]:				https://github.com/FasterXML/jackson
[ANSI]:					https://en.wikipedia.org/wiki/ANSI_escape_code
[JSON]:					https://no.wikipedia.org/wiki/JSON
[JsonGenerator]:		https://github.com/FasterXML/jackson-core/blob/master/src/main/java/com/fasterxml/jackson/core/JsonGenerator.java
[logback-logstash-syntax-highlighting-decorators]: https://github.com/skjolber/logback-logstash-syntax-highlighting-decorators
