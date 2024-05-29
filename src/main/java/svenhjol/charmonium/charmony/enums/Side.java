package svenhjol.charmonium.charmony.enums;

import java.util.Locale;

public enum Side {
    CLIENT,
    COMMON,
    SERVER;

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}