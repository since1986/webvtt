package io.github.since1986.webvtt.cue;

import io.github.since1986.webvtt.ToString;
import io.github.since1986.webvtt.cue.timing.VTTRawTime;

public record CueTiming(VTTRawTime start, VTTRawTime end) implements ToString {

    public static final String TIMING_DIRECTION_SYMBOL = "-->";

    public CueTiming {
        CueTiming.check(start, end);
    }

    static void check(VTTRawTime start, VTTRawTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("The start and end values are required.");
        }
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("The end must be after the start.");
        }
    }

    @Override
    public String toString() {
        return "%s %s %s".formatted(start, TIMING_DIRECTION_SYMBOL, end);
    }

    public static CueTiming from(String s) {
        return null;
    }
}