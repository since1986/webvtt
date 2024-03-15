package com.github.since1986.webvtt.cue;

import com.github.since1986.webvtt.ToString;
import com.github.since1986.webvtt.WebVTTBodyItem;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <a href="https://developer.mozilla.org/en-US/docs/Web/API/WebVTT_API#webvtt_cues">...</a>
 * <p>
 * A cue is a single subtitle block that has a single start time, end time, and textual payload. A cue consists of five components:
 *
 * <li>An optional cue identifier followed by a newline.</li>
 * <li>Cue timings.</li>
 * <li>Optional cue settings with at least one space before the first and between each setting.</li>
 * <li>A single newline.</li>
 * <li>The cue payload text.</li>
 */

public record WebVTTCue(String identifier, CueTiming timing, List<? extends CueSetting<?>> settings, String payload) implements WebVTTBodyItem<WebVTTCue> {

    @Override
    public String toString() {
        return "%s\n%s %s\n%s".formatted(
                identifier,
                timing.to(),
                Optional.ofNullable(settings).orElse(new LinkedList<>()).stream().map(ToString::to).collect(Collectors.joining(" ")),
                payload
        );
    }

    @Override
    public WebVTTCue from(String s) {
        return null;
    }
}
