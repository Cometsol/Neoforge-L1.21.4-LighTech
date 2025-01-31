package com.cometsol.lighttech.Util;

public enum MiscHelpersRedstoneMode {
    IGNORED,
    LOW,
    HIGH,
    PULSE;

    private MiscHelpersRedstoneMode() {
    }

    public MiscHelpersRedstoneMode next() {
        MiscHelpersRedstoneMode[] values = values();
        int nextOrdinal = (this.ordinal() + 1) % values.length;
        return values[nextOrdinal];
    }
}
