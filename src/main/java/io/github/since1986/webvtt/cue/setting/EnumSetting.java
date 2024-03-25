package io.github.since1986.webvtt.cue.setting;

import io.github.since1986.webvtt.cue.CueSetting;

public interface EnumSetting extends CueSetting<Enum<?>> {

    @Override
    default String to() {
        return "%s:%s".formatted(key(), value().name().toLowerCase());
    }
}
