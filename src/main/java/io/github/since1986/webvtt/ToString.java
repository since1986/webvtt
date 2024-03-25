package io.github.since1986.webvtt;

public interface ToString {

    default String to() {
        return toString();
    }
}
