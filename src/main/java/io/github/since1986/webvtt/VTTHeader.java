package io.github.since1986.webvtt;

import java.util.Optional;

public record VTTHeader(String textHeader) implements ToString {

    public static final String FILE_HEADER = "WEBVTT";

    @Override
    public String toString() {
        return "%s %s".formatted(FILE_HEADER, Optional.ofNullable(textHeader).orElse(""));
    }

    public static VTTHeader from(String s) {
        return null;
    }
}
