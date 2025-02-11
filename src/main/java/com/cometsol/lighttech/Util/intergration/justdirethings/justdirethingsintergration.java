package com.cometsol.lighttech.Util.intergration.justdirethings;

import net.neoforged.fml.ModList;

public class justdirethingsintergration {
    private static final String ID = "justdirethings";

    public justdirethingsintergration(){
    }

    public static boolean isLoaded(){
        return ModList.get().isLoaded(ID);
    }
}
