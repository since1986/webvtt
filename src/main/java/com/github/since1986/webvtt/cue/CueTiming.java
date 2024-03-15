package com.github.since1986.webvtt.cue;

import com.github.since1986.webvtt.FromString;
import com.github.since1986.webvtt.ToString;

public record CueTiming(String start, String end) implements ToString, FromString<CueTiming> {

    public static final String DIRECTION_SYMBOL = "-->";

    @Override
    public String toString() {
        return "%s %s %s".formatted(start, DIRECTION_SYMBOL, end);
    }

    @Override
    public CueTiming from(String s) {
        return null;
    }
}