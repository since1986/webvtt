package io.github.since1986.webvtt.cue.setting;


public record PercentageLine(int percent) implements PercentageSetting {

    @Override
    public String key() {
        return "line";
    }

    @Override
    public Integer value() {
        return percent;
    }

    public static PercentageLine from(String s) {
        return null;
    }
}