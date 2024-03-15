package com.github.since1986.webvtt;

public record WebVTT(WebVTTHeader header, WebVTTBody body) implements ToString, FromString<WebVTT> {

    @Override
    public String toString() {
        return "%s\n\n%s\n\n".formatted(header, body);
    }

    @Override
    public WebVTT from(String s) {
        return null;
    }
}
