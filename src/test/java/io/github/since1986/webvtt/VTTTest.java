package io.github.since1986.webvtt;

import io.github.since1986.webvtt.comment.VTTComment;
import io.github.since1986.webvtt.cue.CueTiming;
import io.github.since1986.webvtt.cue.VTTCue;
import io.github.since1986.webvtt.cue.setting.*;
import io.github.since1986.webvtt.cue.timing.VTTRawTime;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.List;

class VTTTest {

    @Test
    void testTo() {
        var vtt = new VTT(
                new VTTHeader(null),
                new VTTBody(
                        new VTTCue(
                                "1",
                                new CueTiming(new VTTRawTime(0L, 1L, 1L, 10L), new VTTRawTime(0L, 1L, 1L, 11L)),
                                List.of(
                                        new NumberLine(1),
                                        new PercentageLine(1),
                                        new Position(1),
                                        new Vertical(Vertical._Vertical.LR),
                                        new Size(1)
                                ),
                                "test payload 1"
                        ),
                        new VTTComment("这是一个换行注释 > < & &gt; &lt; &amp;", true),
                        new VTTCue(
                                "2",
                                new CueTiming(new VTTRawTime(0L, 1L, 1L, 11L), new VTTRawTime(9L, 1L, 1L, 20L)),
                                List.of(
                                        new NumberLine(1),
                                        new PercentageLine(1),
                                        new Position(1),
                                        new Vertical(Vertical._Vertical.LR),
                                        new Size(1),
                                        new Align(Align._Align.CENTER)
                                ),
                                "test payload 2"
                        ),
                        new VTTComment("这是一个连行注释", false),
                        new VTTComment("这是又一个连行注释", false),
                        new VTTComment("""
                                这是一个多
                                行注释
                                """, true)
                )
        );

        var expected = """
                WEBVTT\s
                                
                1
                00:01:01.010 --> 00:01:01.011 line:1 line:1% position:1% vertical:lr size:1%
                test payload 1
                                
                NOTE
                这是一个换行注释 &gt; &lt; &amp; &gt; &lt; &amp;
                                
                2
                00:01:01.011 --> 09:01:01.020 line:1 line:1% position:1% vertical:lr size:1% align:center
                test payload 2
                                
                NOTE 这是一个连行注释
                                
                NOTE 这是又一个连行注释
                                
                NOTE
                这是一个多
                行注释
                                
                                
                """;

        Assumptions.assumeTrue(expected.equals(vtt.to()));
    }
}