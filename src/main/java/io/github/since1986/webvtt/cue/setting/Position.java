package io.github.since1986.webvtt.cue.setting;

import io.github.since1986.webvtt.cue.CueSetting;

public record Position(int position) implements PercentageSetting {

    @Override
    public String key() {
        return "position";
    }

    @Override
    public Integer value() {
        return position;
    }

    public static CueSetting<Integer> from(String s) {
        return null;
    }
}