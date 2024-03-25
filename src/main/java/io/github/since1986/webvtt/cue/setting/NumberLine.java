package io.github.since1986.webvtt.cue.setting;

import io.github.since1986.webvtt.cue.CueSetting;

public record NumberLine(int number) implements CueSetting<Integer> {

    @Override
    public String key() {
        return "line";
    }

    @Override
    public Integer value() {
        return number;
    }

    public static CueSetting<Integer> from(String s) {
        return null;
    }
}