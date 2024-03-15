package com.github.since1986.webvtt.cue;

import com.github.since1986.webvtt.FromString;
import com.github.since1986.webvtt.ToString;

public interface CueSetting<T> extends ToString, FromString<CueSetting<T>> {

    String key();

    T value();

    @Override
    default String to() {
        return "%s:%s".formatted(key(), value());
    }
}
