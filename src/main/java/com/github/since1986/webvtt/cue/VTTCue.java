package com.github.since1986.webvtt.cue;

import com.github.since1986.webvtt.Escape;
import com.github.since1986.webvtt.ToString;
import com.github.since1986.webvtt.VTTBodyItem;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.since1986.webvtt.cue.CueTiming.TIMING_DIRECTION_SYMBOL;

/**
 * <p>A <a href="https://developer.mozilla.org/en-US/docs/Web/API/WebVTT_API#webvtt_cues">cue</a> is a single subtitle block that has a single start time, end time, and textual payload. A cue consists of five components:</p>
 * <ul>
 * <li>An optional cue identifier followed by a newline.</li>
 * <li>Cue timings.</li>
 * <li>Optional cue settings with at least one space before the first and between each setting.</li>
 * <li>A single newline.</li>
 * <li>The cue payload text.</li>
 * </ul>
 */

public record VTTCue(String identifier, CueTiming timing, List<? extends CueSetting<?>> settings, String payload) implements VTTBodyItem<VTTCue> {

    public VTTCue {
        var indexInIdentifier = identifier.indexOf(TIMING_DIRECTION_SYMBOL);
        if (indexInIdentifier != -1) {
            throw new IllegalArgumentException("Found an invalid character [%s] at position %d in the identifier string.".formatted(TIMING_DIRECTION_SYMBOL, indexInIdentifier));
        }

        var indexInPayload = payload.indexOf(TIMING_DIRECTION_SYMBOL);
        if (indexInPayload != -1) {
            throw new IllegalArgumentException("Found an invalid character [%s] at position %d in the payload string.".formatted(TIMING_DIRECTION_SYMBOL, indexInPayload));
        }

        if (timing.to() == null || timing.to().isEmpty()) {
            throw new IllegalArgumentException("The timing are required.");
        }
    }

    @Override
    public String toString() {
        return "%s\n%s %s\n%s".formatted(
                Optional.ofNullable(Escape.escape(identifier)).orElse(""),
                timing.to(),
                Optional.ofNullable(settings).orElse(new LinkedList<>()).stream().map(ToString::to).collect(Collectors.joining(" ")),
                Optional.ofNullable(Escape.escape(payload)).orElse("")
        );
    }

    public static VTTCue from(String s) {
        return null;
    }
}
