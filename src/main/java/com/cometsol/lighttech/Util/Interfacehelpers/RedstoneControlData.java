package com.cometsol.lighttech.Util.Interfacehelpers;

import com.cometsol.lighttech.Util.MiscHelpers;
import com.cometsol.lighttech.Util.MiscHelpersRedstoneMode;
import java.util.Objects;

public class RedstoneControlData {
    public boolean receivingRedstone = false;
    public boolean checkedRedstone = false;
    public boolean pulsed = false;
    public MiscHelpersRedstoneMode redstoneMode;

    public RedstoneControlData() {
        this.redstoneMode = MiscHelpersRedstoneMode.IGNORED;
    }

    public RedstoneControlData(MiscHelpersRedstoneMode redstoneMode) {
        this.redstoneMode = MiscHelpersRedstoneMode.IGNORED;
        this.redstoneMode = redstoneMode;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.receivingRedstone, this.pulsed, this.redstoneMode});
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            RedstoneControlData that = (RedstoneControlData)o;
            return this.receivingRedstone == that.receivingRedstone && this.pulsed == that.pulsed && this.redstoneMode == that.redstoneMode;
        } else {
            return false;
        }
    }
}