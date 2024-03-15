package com.github.since1986.webvtt.cue.timing;

import com.github.since1986.webvtt.ToString;


public record Time(Integer hour, Integer minute, Integer second, Integer millisecond) implements ToString, Comparable<Time> {

    /**
     * <a href="https://developer.mozilla.org/en-US/docs/Web/API/WebVTT_API#cue_timings">cue timing</a>
     * The timestamps must be in one of two formats:
     * <ul>
     * <li>mm:ss.ttt</li>
     * <li>hh:mm:ss.ttt</li>
     * </ul>
     *
     * @param hour        Represents hours and must be at least two digits. It can be greater than two digits (e.g., 9999:00:00.000).
     * @param minute      Represents minutes and must be between 00 and 59, inclusive.
     * @param second      Represents seconds and must be between 00 and 59, inclusive.
     * @param millisecond Represents milliseconds and must be between 000 and 999, inclusive.
     */
    public Time {
        if (hour < 0 || hour > 9999) {
            throw new IllegalArgumentException("The hour value in the cue timing must range from 0(inclusive) to 9999(inclusive).");
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("The minute value in the cue timing must range from 0(inclusive) to 59(inclusive).");
        }
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("The second value in the cue timing must range from 0(inclusive) to 59(inclusive).");
        }
        if (millisecond < 0 || millisecond > 999) {
            throw new IllegalArgumentException("The millisecond value in the cue timing must range from 0(inclusive) to 999(inclusive).");
        }
    }

    @Override
    public String toString() {
        if (hour == null) {
            return "%02d:%02d.%03d".formatted(minute, second, millisecond);
        }
        if (hour < 10) {
            return "%02d:%02d:%02d.%03d".formatted(hour, minute, second, millisecond);
        }
        return "%s:%02d:%02d.%03d".formatted(String.valueOf(hour), minute, second, millisecond);
    }

    @Override
    public int compareTo(Time another) {
        if (this.hour > another.hour) {
            return 1;
        }
        if (this.hour < another.hour) {
            return -1;
        }
        if (this.minute > another.minute) {
            return 1;
        }
        if (this.minute < another.minute) {
            return -1;
        }
        if (this.second > another.second) {
            return 1;
        }
        if (this.second < another.second) {
            return -1;
        }
        return this.millisecond.compareTo(another.millisecond);
    }

    public static Time from(String s) {
        return null;
    }
}
