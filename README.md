# jackson-syntax-highlight
Simple utility for generating syntax-highlighted [JSON] text using the [Jackson](https://github.com/FasterXML/jackson) library. Inlines [ANSI] color-codes visible in ANSI-enabled consoles.

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

The library is primarily intended for adding coloring while doing minimal changes to existing applications. For example, coloring of status codes during unit testing.

## License
[Apache 2.0]

## Obtain
The project is built with [Maven] and is available on the central Maven repository. 

<details>
  <summary>Maven coordinates</summary>

Add the property
```xml
<jackson-syntax-highlight.version>1.0.7</jackson-syntax-highlight.version>
```

then add

```xml
<dependency>
    <groupId>com.github.skjolber.jackson</groupId>
    <artifactId>jackson-syntax-highlight</artifactId>
    <version>1.0.7</version>
</dependency>
```
</details>

or

<details>
  <summary>Gradle coordinates</summary>

For

```groovy
ext {
  jacksonSyntaxHighlightVersion = '1.0.7'
}
```

add

```groovy
api ("com.github.skjolber.jackson:jackson-syntax-highlight:${jacksonSyntaxHighlightVersion}")
```
</details>

# Usage
The highlighter wraps a normal [JsonGenerator]. Pretty-printing is enabled by default.

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
Supply an instance of `SyntaxHighlighter` using the builder:

```java
SyntaxHighlighter highlighter = DefaultSyntaxHighlighter
                                    .newBuilder()
                                    .withNumber(AnsiSyntaxHighlight.BLUE)
                                    .build();
		
JsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate, highlighter);
```

In addition, the JSON structure can be tracked via [JsonStreamContextListener](src/main/java/com/github/skjolber/jackson/jsh/JsonStreamContextListener.java), for stateful coloring of subtrees. 

## Highlighting an object
Write a full object using `writeObject`, i.e.

```java
JsonGenerator jsonGenerator = new SyntaxHighlightingJsonGenerator(delegate, highlighter, prettyprint);
jsonGenerator.writeObject(obj);
```

## See also

 * [logback-logstash-syntax-highlighting-decorators]

# History

 - 1.0.7: Do not set default colors.
 - 1.0.6: Add option for single-line output
 - 1.0.3 to 1.0.5: Bump Jackson dependency due to security issue 
 - 1.0.2: More tests, minor fixes.
 - 1.0.1: Various improvements, works better with [logback-logstash-syntax-highlighting-decorators] for Logback logging.
 - 1.0.0: Initial version

[Apache 2.0]:          	http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:       	https://github.com/skjolber/jackson-syntax-highlight/issues
[Maven]:                http://maven.apache.org/
[1.0.5]:				https://github.com/skjolber/jackson-syntax-highlight/releases/tag/jackson-syntax-highlight-1.0.5
[SyntaxHighlighter]:	src/main/java/com/github/skjolber/jackson/jsh/SyntaxHighlighter.java
[Jackson]:				https://github.com/FasterXML/jackson
[ANSI]:					https://en.wikipedia.org/wiki/ANSI_escape_code
[JSON]:					https://no.wikipedia.org/wiki/JSON
[JsonGenerator]:		https://github.com/FasterXML/jackson-core/blob/master/src/main/java/com/fasterxml/jackson/core/JsonGenerator.java
[logback-logstash-syntax-highlighting-decorators]: https://github.com/skjolber/logback-logstash-syntax-highlighting-decorators
