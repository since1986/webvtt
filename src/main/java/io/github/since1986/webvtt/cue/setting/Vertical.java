package io.github.since1986.webvtt.cue.setting;

public record Vertical(_Vertical vertical) implements EnumSetting {

    @Override
    public String key() {
        return "vertical";
    }

    @Override
    public _Vertical value() {
        return vertical;
    }

    public enum _Vertical {
        RL, LR
    }

    public static Vertical from(String s) {
        return null;
    }
}