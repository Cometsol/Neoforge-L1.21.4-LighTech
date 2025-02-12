package com.cometsol.lighttech.Datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;

public class DataGenerators {
    public static void gatherData(GatherDataEvent.Client event){
        DataGenerator generators = event.getGenerator();
        PackOutput output = generators.getPackOutput();

        LighttechBlockTags lighttechBlockTags = new LighttechBlockTags(output, event.getLookupProvider());
        generators.addProvider(true, lighttechBlockTags);
        generators.addProvider(true, new LighttechItemTags(output, event.getLookupProvider(), lighttechBlockTags));
        generators.addProvider(true, new AdvancementProvider(
                output, event.getLookupProvider(),
                List.of(new LightTechAdvancementGen())
        ));

    }

}
