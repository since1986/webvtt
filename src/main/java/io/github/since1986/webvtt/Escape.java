package io.github.since1986.webvtt;

public final class Escape {

    private Escape() {
    }

    /**
     * <a href="https://developer.mozilla.org/en-US/docs/Web/API/WebVTT_API#cue_payload">see...</a>
     * @param raw raw text
     */
    public static String escape(final String raw) {
        if (raw == null) {
            return null;
        }

        // To replace standalone ampersands ("&") while ensuring that entities like "&gt;", "&lt;", and "&amp;" are not affected, you can use lookaround assertions in the `replaceAll()` method. A negative lookbehind and a negative lookahead will ensure that the ampersand is not part of these entities.
        // This regular expression uses a negative lookbehind `(?<![&])` to assert that the ampersand is not preceded by another ampersand. The negative lookahead `(?!([a-zA-Z]+|#\\d+|#x[0-9a-fA-F]+);)` asserts that the ampersand is not followed by one or more letters or a number sign followed by one or more digits (which would form a numeric entity) or a number sign followed by 'x' and one or more hexadecimal characters, and then a semicolon – these are typical structures of HTML entities.
        // Please test this method thoroughly to ensure it works as expected for your specific use case.
        return raw
                .replaceAll("(?<!&)&(?!([a-zA-Z]+|#\\\\d+|#x[0-9a-fA-F]+);)", "&amp;") // Regex gen by ChatGPT.
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\\u200E", "&lrm;") // https://unicode-explorer.com/c/200E
                .replaceAll("\\u200F", "&rlm;") // https://unicode-explorer.com/c/200F
                .replaceAll("\\u00A0", "&nbsp;"); // https://unicode-explorer.com/c/00A0
    }
}
