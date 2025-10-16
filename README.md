![Build Status](https://github.com/entur/jackson-syntax-highlight/actions/workflows/maven.yml/badge.svg) 
[![Maven Central](https://img.shields.io/maven-central/v/org.entur.jackson/jackson-syntax-highlight.svg)](https://mvnrepository.com/artifact/org.entur.jackson/jackson-syntax-highlight)

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

For Jackson `2.x` support, see the `jackson2.x` branch. Note that the `jackson2.x` branch is on another coordinate and package, so both versions can be used in parallel.

## License
[Apache 2.0]

## Obtain
The project is built with [Maven] and is available on the central Maven repository. 

<details>
  <summary>Maven coordinates</summary>

Add the property
```xml
<jackson-syntax-highlight.version>3.0.x</jackson-syntax-highlight.version>
```

then add

```xml
<dependency>
    <groupId>org.entur.jackson3</groupId>
    <artifactId>jackson-syntax-highlight</artifactId>
    <version>${jackson-syntax-highlight.version}</version>
</dependency>
```
</details>

or

<details>
  <summary>Gradle coordinates</summary>

For

```groovy
ext {
  jacksonSyntaxHighlightVersion = '3.0.x'
}
```

add

```groovy
api ("org.entur.jackson3:jackson-syntax-highlight:${jacksonSyntaxHighlightVersion}")
```
</details>

# Usage
The highlighter requires two changes to a regular setup.

```java
SyntaxHighlighter syntaxHighlighter = DefaultSyntaxHighlighter.newBuilder()
        .withNumber(AnsiSyntaxHighlight.RED)
        .withString(AnsiSyntaxHighlight.BLUE)
        .build();

// construct output generator
JsonMapper mapper = JsonMapper.builderWithJackson2Defaults()
        .defaultPrettyPrinter(new SyntaxHighlightingPrettyPrinter(syntaxHighlighter))
        .build();
ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();

// create stream and create generator
gWriter writer = new StringWriter();
JsonGenerator delegate = objectWriter.createGenerator(writer);
SyntaxHighlightingJsonGenerator jsonGenerator = SyntaxHighlightingJsonGenerator.create(delegate);

// write JSON output
jsonGenerator.writeStartObject(); // start root object
jsonGenerator.writeFieldName("name");
jsonGenerator.writeNumber(123);

// your code here

jsonGenerator.writeEndObject();
jsonGenerator.close();

// print writer contents.
```

In addition, the JSON structure can be tracked via [TokenStreamContextListener](src/main/java/org/entur/jackson3/jsh/TokenStreamContextListener.java), for stateful coloring of subtrees.

## Highlighting an object
Write a full object using `writeObject`, i.e.

```java
jsonGenerator.writePOJO(obj);
```

## See also

 * [logback-logstash-syntax-highlighting-decorators]

# History

 - 3.x: Support for Jackson 3.
 - 1.1.0: Forked from [jackson-syntax-highlight](https://github.com/skjolber/jackson-syntax-highlight) due to too few maintainers.

[Apache 2.0]:          	http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:       	https://github.com/entur/jackson-syntax-highlight/issues
[Maven]:                http://maven.apache.org/
[SyntaxHighlighter]:	src/main/java/org/entur/jackson3/jsh/SyntaxHighlighter.java
[Jackson]:				https://github.com/FasterXML/jackson
[ANSI]:					https://en.wikipedia.org/wiki/ANSI_escape_code
[JSON]:					https://no.wikipedia.org/wiki/JSON
[JsonGenerator]:		https://github.com/FasterXML/jackson-core/blob/master/src/main/java/com/fasterxml/jackson/core/JsonGenerator.java
[logback-logstash-syntax-highlighting-decorators]: https://github.com/entur/logback-logstash-syntax-highlighting-decorators
