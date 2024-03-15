package com.github.since1986.webvtt.cue.setting;

import com.github.since1986.webvtt.cue.CueSetting;

public record NumberLine(int number) implements CueSetting<Integer> {

    @Override
    public CueSetting<Integer> from(String s) {
        return null;
    }

    @Override
    public String key() {
        return "line";
    }

    @Override
    public Integer value() {
        return number;
    }
}