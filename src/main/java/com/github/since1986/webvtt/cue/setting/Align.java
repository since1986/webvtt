package com.github.since1986.webvtt.cue.setting;

import com.github.since1986.webvtt.cue.CueSetting;

public record Align(_Align align) implements EnumSetting {

    @Override
    public String key() {
        return "align";
    }

    @Override
    public _Align value() {
        return align;
    }

    public enum _Align {
        START, CENTER, END
    }

    public static CueSetting<Enum<?>> from(String s) {
        return null;
    }
}