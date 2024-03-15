package com.github.since1986.webvtt;

import com.github.since1986.webvtt.cue.CueTiming;
import com.github.since1986.webvtt.cue.WebVTTCue;
import com.github.since1986.webvtt.cue.setting.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class WebVTTTest {

    @Test
    void testTo() {
        var vtt = new WebVTT(
                new WebVTTHeader("TEST text header"),
                new WebVTTBody(
                        new WebVTTCue(
                                "1",
                                new CueTiming("00:00:00", "00:00:10"),
                                List.of(
                                        new NumberLine(1),
                                        new PercentageLine(1),
                                        new Position(1),
                                        new Vertical(Vertical.Direction.LR),
                                        new Size(1)
                                ),
                                "test payload 1"
                        ),
                        new WebVTTCue(
                                "2",
                                new CueTiming("00:00:00", "00:00:20"),
                                List.of(
                                        new NumberLine(1),
                                        new PercentageLine(1),
                                        new Position(1),
                                        new Vertical(Vertical.Direction.LR),
                                        new Size(1),
                                        new Align(Align._Align.CENTER)
                                ),
                                "test payload 2"
                        )
                )
        );

        System.out.println(vtt.to());
    }
}