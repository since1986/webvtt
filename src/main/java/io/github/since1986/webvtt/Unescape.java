package io.github.since1986.webvtt;

public final class Unescape {

    private Unescape() {
    }

    public static String unescape(final String escaped) {
        if (escaped == null) {
            return null;
        }
        return escaped
                .replaceAll("&nbsp;", "\u00A0")
                .replaceAll("&rlm;", "\u200F")
                .replaceAll("&lrm;", "\u200E")
                .replaceAll("&gt;", ">")
                .replaceAll("&lt;", "<")
                .replaceAll("&amp;", "&");
    }
}
