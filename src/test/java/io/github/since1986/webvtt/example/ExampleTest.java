package io.github.since1986.webvtt.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.since1986.webvtt.VTT;
import io.github.since1986.webvtt.VTTBody;
import io.github.since1986.webvtt.VTTBodyItem;
import io.github.since1986.webvtt.VTTHeader;
import io.github.since1986.webvtt.cue.CueTiming;
import io.github.since1986.webvtt.cue.VTTCue;
import io.github.since1986.webvtt.cue.setting.PercentageLine;
import io.github.since1986.webvtt.cue.setting.Size;
import io.github.since1986.webvtt.cue.timing.VTTTime;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ExampleTest {

    @Test
    void testVTTTime() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("vtt-time-test.json")) {
            var objectMapper = new ObjectMapper();
            var inputList = objectMapper.readValue(inputStream, new TypeReference<List<Map<String, String>>>() {
            });

            // Each cue spans 200ms
            var cues = new ArrayList<VTTCue>();
            var start = VTTTime.zero();
            for (int i = 0; i < inputList.size(); i++) {
                var current = inputList.get(i);
                var end = start.plusMilliseconds(200L);

                var payload = "%d - %s,%s".formatted(i, current.get("latitude"), current.get("longitude"));
                var cue = new VTTCue(
                        String.valueOf(i),
                        new CueTiming(start.to(), end.to()),
                        List.of(
                                new PercentageLine(60),
                                new Size(20)
                        ),
                        payload
                );
                cues.add(cue);

                start = end;
            }

            var vtt = new VTT(
                    new VTTHeader("测试每 200ms 打印一次字幕"),
                    new VTTBody(cues.toArray(new VTTBodyItem[]{}))
            );

            Files.writeString(Path.of("input.vtt"), vtt.to(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
    }
}
