package com.github.since1986.webvtt;

public record WebVTTHeader(String textHeader) implements ToString, FromString<WebVTTHeader> {

    public static final String FILE_HEADER = "WEBVTT";

    @Override
    public String toString() {
        return "%s %s".formatted(FILE_HEADER, textHeader);
    }

    @Override
    public WebVTTHeader from(String s) {
        return null;
    }

    @Override
    public String to() {
        return toString();
    }
}
