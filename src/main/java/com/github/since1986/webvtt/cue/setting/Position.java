package com.github.since1986.webvtt.cue.setting;

import com.github.since1986.webvtt.cue.CueSetting;

public record Position(int position) implements PercentageSetting {

    @Override
    public CueSetting<Integer> from(String s) {
        return null;
    }

    @Override
    public String key() {
        return "position";
    }

    @Override
    public Integer value() {
        return position;
    }
}