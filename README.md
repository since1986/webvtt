# Java WebVTT Library

🚧 Yet another Java WebVTT implementation.
This is an open-source library designed for structured creation and parsing of WebVTT (Web Video Text Tracks) files using Java.

## Features

* Structured creation of WebVTT files.
* Parse WebVTT files. (🚧 under construction)
* Validate WebVTT syntax.

## Getting Started

### Prerequisites

To use this library, you will need:
**Java 17** or higher (Because I used two features: `Record` and `Text Blocks`)

### Installing

You can add this library to your project as a dependency via Maven:

```xml
<dependency>
    <groupId>com.github.since1986</groupId>
    <artifactId>webvtt</artifactId>
    <version>1.0.2</version>
</dependency>
```

Or using Gradle:

```groovy
dependencies {
    implementation 'com.github.since1986:webvtt:1.0.2'
}
```

### Usage

Here is a simple example of how to create a WebVTT:

```
var vtt = new VTT(
        new VTTHeader(null),
        new VTTBody(
                new VTTCue(
                        "1",
                        new CueTiming(new Time(0, 1, 1, 10), new Time(0, 1, 1, 11)),
                        List.of(
                                new NumberLine(1),
                                new PercentageLine(1),
                                new Position(1),
                                new Vertical(Vertical._Vertical.LR),
                                new Size(1)
                        ),
                        "test payload 1"
                ),
                new VTTComment("这是一个换行注释 > < & &gt; &lt; &amp;", true),
                new VTTCue(
                        "2",
                        new CueTiming(new Time(0, 1, 1, 11), new Time(9, 1, 1, 20)),
                        List.of(
                                new NumberLine(1),
                                new PercentageLine(1),
                                new Position(1),
                                new Vertical(Vertical._Vertical.LR),
                                new Size(1),
                                new Align(Align._Align.CENTER)
                        ),
                        "test payload 2"
                ),
                new VTTComment("这是一个连行注释", false),
                new VTTComment("这是又一个连行注释", false),
                new VTTComment("""
                        这是一个多
                        行注释
                        """, true)
        )
);

// System.out.println(vtt.to());
```

## Contributing

Welcome contributions to this project. Please fork the repository and submit a pull request.

## License

This project is licensed under the Apache License Version 2.0 License see the [LICENSE](LICENSE) file for details.

## Acknowledgments

* Thanks to all the contributors who have helped to improve this library.

## Resources

[Web Video Text Tracks Format (WebVTT)](https://developer.mozilla.org/en-US/docs/Web/API/WebVTT_API)