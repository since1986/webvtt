package com.github.since1986.webvtt.cue;

import com.github.since1986.webvtt.ToString;
import com.github.since1986.webvtt.cue.timing.Time;

public record CueTiming(Time start, Time end) implements ToString {

    public static final String TIMING_DIRECTION_SYMBOL = "-->";

    static void check(Time start, Time end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("The start and end values are required.");
        }
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("The end must be after the start.");
        }
    }

    public CueTiming {
        CueTiming.check(start, end);
    }

    @Override
    public String toString() {
        return "%s %s %s".formatted(start, TIMING_DIRECTION_SYMBOL, end);
    }

    public static CueTiming from(String s) {
        return null;
    }
}