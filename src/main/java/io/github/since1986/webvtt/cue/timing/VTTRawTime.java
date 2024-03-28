package io.github.since1986.webvtt.cue.timing;

import io.github.since1986.webvtt.ToString;

import java.util.Optional;


public record VTTRawTime(Long hour, Long minute, Long second, Long millisecond) implements ToString, Comparable<VTTRawTime> {

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
    public VTTRawTime {
        VTTRawTime.check(hour, minute, second, millisecond);
    }

    public static VTTRawTime zero() {
        return new VTTRawTime(0L, 0L, 0L, 0L);
    }

    public static VTTRawTime maxValue() {
        return new VTTRawTime(9999L, 59L, 59L, 999L);
    }

    static void check(Long hour, Long minute, Long second, Long millisecond) {
        if (hour != null && (hour < 0 || hour > 9999)) {
            throw new IllegalArgumentException("The hour value in the cue timing must range from 0(inclusive) to 9999(inclusive).");
        }
        if (minute == null) {
            throw new IllegalArgumentException("The cue timing must include the minute.");
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("The minute value in the cue timing must range from 0(inclusive) to 59(inclusive).");
        }
        if (second == null) {
            throw new IllegalArgumentException("The cue timing must include the second.");
        }
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("The second value in the cue timing must range from 0(inclusive) to 59(inclusive).");
        }
        if (millisecond == null) {
            throw new IllegalArgumentException("The cue timing must include the millisecond.");
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

    public static VTTRawTime from(String s) {
        return null;
    }

    @Override
    public int compareTo(VTTRawTime another) {
        if (Optional.ofNullable(this.hour).orElse(0L) > Optional.ofNullable(another.hour).orElse(0L)) {
            return 1;
        }
        if (Optional.ofNullable(this.hour).orElse(0L) < Optional.ofNullable(another.hour).orElse(0L)) {
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

    public boolean after(VTTRawTime another) {
        return compareTo(another) > 0;
    }

    public boolean before(VTTRawTime another) {
        return compareTo(another) < 0;
    }
}
