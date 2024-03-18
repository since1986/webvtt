package com.github.since1986.webvtt.comment;

import com.github.since1986.webvtt.Escape;
import com.github.since1986.webvtt.Unescape;
import com.github.since1986.webvtt.VTTBodyItem;

import static com.github.since1986.webvtt.cue.CueTiming.TIMING_DIRECTION_SYMBOL;

/**
 * <p><a href="https://developer.mozilla.org/en-US/docs/Web/API/WebVTT_API#webvtt_comments">Comments</a> are an optional component that can be used to add information to a WebVTT file. Comments are intended for those reading the file and are not seen by users. Comments may contain newlines but cannot contain a blank line, which is equivalent to two consecutive newlines. A blank line signifies the end of a comment.</p>
 * <br>
 * <p>A comment cannot contain the string -->, the ampersand character (&), or the less-than sign (<). If you wish to use such characters, you need to escape them using for example &amp; for ampersand and &lt; for less-than. It is also recommended that you use the greater-than escape sequence (&gt;) instead of the greater-than character (>) to avoid confusion with tags.</p>
 * <br>
 * <p>A comment consists of three parts:</p>
 * <ul>
 * <li>The string NOTE.</li>
 * <li>A space or a newline.</li>
 * <li>Zero or more characters other than those noted above.</li>
 * </ul>
 */
public record VTTComment(String payload, boolean newLine) implements VTTBodyItem<VTTComment> {

    public static final String NOTE_MARKER = "NOTE";

    public VTTComment {
        VTTComment.check(payload);
    }

    static void check(String payload) {
        var index = payload.indexOf(TIMING_DIRECTION_SYMBOL);
        if (index != -1) {
            throw new IllegalArgumentException("Found an invalid character [%s] at position %d in the payload string.".formatted(TIMING_DIRECTION_SYMBOL, index));
        }
    }

    @Override
    public String toString() {
        return "%s%s%s".formatted(NOTE_MARKER, newLine ? "\n" : " ", Escape.escape(payload));
    }

    public static VTTComment from(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Cannot create a vtt comment from null or empty string");
        }
        if (!s.startsWith(NOTE_MARKER)) {
            throw new IllegalArgumentException("VVT comment must start with '%s'".formatted(NOTE_MARKER));
        }
        var mustIndex = s.indexOf(NOTE_MARKER) + 1;
        if (s.length() < mustIndex) {
            throw new IllegalArgumentException("VVT comment must have at least %d characters".formatted(mustIndex + 1));
        }
        var mustValue = s.substring(mustIndex, mustIndex + 1);
        if (!mustValue.equals(" ") && !mustValue.equals("\n")) {
            throw new IllegalArgumentException("VVT comment must contain a space or a newline after note marker.");
        }

        var unescaped = Unescape.unescape(s);
        return new VTTComment(unescaped, mustValue.equals("\n"));
    }
}
