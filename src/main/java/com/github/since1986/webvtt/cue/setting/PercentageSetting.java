package com.github.since1986.webvtt.cue.setting;

import com.github.since1986.webvtt.cue.CueSetting;

public interface PercentageSetting extends CueSetting<Integer> {

    @Override
    default String to() {
        return "%s:%s%%".formatted(key(), value());
    }
}
