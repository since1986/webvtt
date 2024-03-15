package com.github.since1986.webvtt.cue.setting;

public record Vertical(Vertical.Direction direction) implements EnumSetting {

    @Override
    public Vertical from(String s) {
        return null;
    }


    @Override
    public String key() {
        return "vertical";
    }

    @Override
    public Vertical.Direction value() {
        return direction;
    }

    public enum Direction {
        RL, LR
    }
}