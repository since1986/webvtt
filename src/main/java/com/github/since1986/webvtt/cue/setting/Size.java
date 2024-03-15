package com.github.since1986.webvtt.cue.setting;

import com.github.since1986.webvtt.cue.CueSetting;

public record Size(int size) implements PercentageSetting {

    @Override
    public CueSetting<Integer> from(String s) {
        return null;
    }

    @Override
    public String key() {
        return "size";
    }

    @Override
    public Integer value() {
        return size;
    }
}