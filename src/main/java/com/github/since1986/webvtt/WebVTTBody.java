package com.github.since1986.webvtt;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public record WebVTTBody(WebVTTBodyItem<?>... items) implements ToString, FromString<WebVTTBody> {

    @Override
    public String toString() {
        return Arrays.stream(Optional.ofNullable(items).orElse(new WebVTTBodyItem[]{})).map(String::valueOf).collect(Collectors.joining("\n\n"));
    }

    @Override
    public WebVTTBody from(String s) {
        return null;
    }
}
