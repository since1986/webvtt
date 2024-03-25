package io.github.since1986.webvtt.cue;

import io.github.since1986.webvtt.ToString;

public interface CueSetting<T> extends ToString {

    @Override
    default String to() {
        return "%s:%s".formatted(key(), value());
    }

    String key();

    T value();
}
