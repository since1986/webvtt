package io.github.since1986.webvtt;

public record VTT(VTTHeader header, VTTBody body) implements ToString {

    @Override
    public String toString() {
        return "%s\n\n%s\n\n".formatted(header, body);
    }

    public static VTT from(String s) {
        return null;
    }
}
