package io.github.since1986.webvtt;

import io.github.since1986.webvtt.cue.VTTCue;
import io.github.since1986.webvtt.cue.timing.VTTRawTime;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public record VTTBody(VTTBodyItem<?>... items) implements ToString {

    @Override
    public String toString() {
        return Arrays.stream(Optional.ofNullable(items).orElse(new VTTBodyItem[]{})).map(String::valueOf).collect(Collectors.joining("\n\n"));
    }

    public VTTBody {
        VTTBody.check(items);
    }

    static void check(VTTBodyItem<?>... items) {
        // https://developer.mozilla.org/en-US/docs/Web/API/WebVTT_API#cue_timings
        // "and the start time must be greater than or equal to all previous start times. Cues may have overlapping timings."
        if (items == null || items.length == 0) {
            return;
        }
        VTTRawTime lastCheckedCueStart = null;
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                throw new IllegalStateException("The body item must not be null (at item index %d).".formatted(i));
            }
            if (items[i] instanceof VTTCue) {
                var currentCueStart = ((VTTCue) items[i]).timing().start();
                if (lastCheckedCueStart == null) {
                    lastCheckedCueStart = currentCueStart;
                } else if (currentCueStart.compareTo(lastCheckedCueStart) < 0) {
                    throw new IllegalStateException("The start time must be greater than or equal to all previous start times. (at item index %d)".formatted(i));
                }
            }
        }
    }

    public static VTTBody from(String s) {
        return null;
    }
}
