package com.github.since1986.webvtt.cue.setting;


public record PercentageLine(int percent) implements PercentageSetting {

    @Override
    public PercentageLine from(String s) {
        return null;
    }

    @Override
    public String key() {
        return "line";
    }

    @Override
    public Integer value() {
        return percent;
    }
}