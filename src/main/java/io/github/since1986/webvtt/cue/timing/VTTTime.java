package io.github.since1986.webvtt.cue.timing;

import java.util.Optional;

public record VTTTime(long value) implements Comparable<VTTTime> {

    private static final long TOTAL_MILLISECONDS_IN_ONE_HOUR = 60L * 60L * 1000L;
    private static final long TOTAL_MILLISECONDS_IN_ONE_MINUTE = 60L * 1000L;
    private static final long TOTAL_MILLISECONDS_IN_ONE_SECOND = 1000L;

    private static final long MAX_VALUE = 9999L * TOTAL_MILLISECONDS_IN_ONE_HOUR + 59L * TOTAL_MILLISECONDS_IN_ONE_MINUTE + 59L * TOTAL_MILLISECONDS_IN_ONE_SECOND + 999L;

    public VTTTime {
        VTTTime.check(value);
    }

    public static VTTTime zero() {
        return new VTTTime(0L);
    }

    public static VTTTime maxValue() {
        return new VTTTime(MAX_VALUE);
    }

    public static VTTTime from(VTTRawTime raw) {
        var hourBase = Optional.ofNullable(raw.hour()).orElse(0L) * 60L * 60L * 1000L;
        var minuteBase = raw.minute() * 60L * 1000L;
        var secondBase = raw.second() * 1000L;
        var totalMillisecond = hourBase + minuteBase + secondBase + raw.millisecond();
        return new VTTTime(totalMillisecond);
    }

    static void check(long value) {
        if (value < 0L || value > MAX_VALUE) {
            throw new IllegalArgumentException("The value of VTTTime must be within the range of %d(inclusive) to %d(inclusive).".formatted(0L, MAX_VALUE));
        }
    }

    @Override
    public long value() {
        return value;
    }

    public VTTRawTime to() {
        // 要进行反向操作，即从毫秒数转换回小时、分钟、秒和毫秒的格式，你需要分别将总毫秒数除以每个单位所表示的毫秒数，并取整到该单位表示的最大数量，再取余数来确定下一个更小单位的数量。以下是如何进行转换的步骤：
        //    将总毫秒数除以1小时所包含的毫秒数 (1小时 = 3600000毫秒) 来得到小时数，并取整。
        //    取上一步计算后的余数，再除以1分钟所包含的毫秒数 (1分钟 = 60000毫秒) 来得到分钟数，并取整。
        //    取上一步计算后的余数，再除以1秒所包含的毫秒数 (1秒 = 1000毫秒) 来得到秒数，并取整。
        //    最后剩下的余数即为毫秒数。

        var hours = value() / TOTAL_MILLISECONDS_IN_ONE_HOUR;
        var minutes = value() % TOTAL_MILLISECONDS_IN_ONE_HOUR / TOTAL_MILLISECONDS_IN_ONE_MINUTE;
        var seconds = value() % TOTAL_MILLISECONDS_IN_ONE_HOUR % TOTAL_MILLISECONDS_IN_ONE_MINUTE / TOTAL_MILLISECONDS_IN_ONE_SECOND;
        var leftMillisecond = value() % TOTAL_MILLISECONDS_IN_ONE_HOUR % TOTAL_MILLISECONDS_IN_ONE_MINUTE % TOTAL_MILLISECONDS_IN_ONE_SECOND;

        return new VTTRawTime(hours == 0L ? null : hours, minutes, seconds, leftMillisecond);
    }

    public VTTTime plusHours(long hoursToAdd) {
        return new VTTTime(this.value() + hoursToAdd * TOTAL_MILLISECONDS_IN_ONE_HOUR);
    }

    public VTTTime plusMinutes(long minuteToAdd) {
        return new VTTTime(this.value() + minuteToAdd * TOTAL_MILLISECONDS_IN_ONE_MINUTE);
    }

    public VTTTime plusSeconds(long secondsToAdd) {
        return new VTTTime(this.value() + secondsToAdd * TOTAL_MILLISECONDS_IN_ONE_SECOND);
    }

    public VTTTime plusMilliseconds(long millisecondsToAdd) {
        return new VTTTime(this.value() + millisecondsToAdd);
    }

    @Override
    public int compareTo(VTTTime another) {
        return Long.compare(this.value(), another.value());
    }

    public boolean after(VTTTime another) {
        return compareTo(another) > 0;
    }

    public boolean before(VTTTime another) {
        return compareTo(another) < 0;
    }
}
