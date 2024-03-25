package io.github.since1986.webvtt.cue.setting;

import io.github.since1986.webvtt.cue.CueSetting;

public record Size(int size) implements PercentageSetting {

    @Override
    public String key() {
        return "size";
    }

    @Override
    public Integer value() {
        return size;
    }

    public static CueSetting<Integer> from(String s) {
        return null;
    }
}