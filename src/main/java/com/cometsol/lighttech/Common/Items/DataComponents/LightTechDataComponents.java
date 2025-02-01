package com.cometsol.lighttech.Common.Items.DataComponents;

/*import com.cometsol.lighttech.Common.Items.Interfaces.Ability;
import com.cometsol.lighttech.Common.Items.Interfaces.ToolRecords;
import com.cometsol.lighttech.Common.Items.Interfaces.ToolRecords.AbilityBinding;
import com.cometsol.lighttech.Common.Items.Interfaces.ToolRecords.AbilityCooldown;
import com.cometsol.lighttech.Util.NBTHelpers.BoundInventory;
import com.cometsol.lighttech.Util.NBTHelpers.PortalDestination;*/
import com.cometsol.lighttech.Util.GlobalVec3;
import com.cometsol.lighttech.LightTech;
import com.mojang.serialization.Codec;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.fluids.SimpleFluidContent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class LightTechDataComponents {
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, LightTech.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> ENTITIYTYPE;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FLOATINGTICKS;
    /*public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> LAVAREPAIR_LAVAPOS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ToolRecords.AbilityCooldown>>> ABILITY_COOLDOWNS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<NBTHelpers.BoundInventory>> BOUND_INVENTORY;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> TOOL_ENABLED;
    */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<GlobalVec3>> BOUND_GLOBAL_VEC3;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<GlobalPos>> BOUND_GLOBAL_POS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<String>>> LEFT_CLICK_ABILITIES;
    /*
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ToolRecords.AbilityBinding>>> ABILITY_BINDINGS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> POCKETGEN_COUNTER;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> POCKETGEN_FUELMULT;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> POCKETGEN_MAXBURN;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FUELCANISTER_FUELLEVEL;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Double>> FUELCANISTER_BURNSPEED;
    */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> COPY_AREA_SETTINGS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> COPY_OFFSET_SETTINGS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> COPY_FILTER_SETTINGS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> COPY_REDSTONE_SETTINGS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CustomData>> COPIED_MACHINE_DATA;
    /*
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUID>> PORTALGUN_UUID;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> PORTALGUN_FAVORITE;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<NBTHelpers.PortalDestination>>> PORTAL_GUN_FAVORITES;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<NBTHelpers.PortalDestination>> PORTAL_GUN_PREVIOUS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> PORTAL_GUN_STAY_OPEN;
    */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SimpleFluidContent>> FLUID_CONTAINER;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FORGE_ENERGY;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FLUID_CANISTER_MODE;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<String>>> STUPEFY_TARGETS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemContainerContents>> ITEMSTACK_HANDLER;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemContainerContents>> TOOL_CONTENTS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotionContents>> POTION_CONTENTS;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> POTION_AMOUNT;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> EPIC_ARROW;
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CustomData>> CUSTOM_DATA_1;
    /*
    public static final Map<Ability, DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>>> ABILITY_TOGGLES;
    public static final Map<Ability, DeferredHolder<DataComponentType<?>, DataComponentType<Integer>>> ABILITY_CUSTOM_SETTINGS;
    public static final Map<Ability, DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>>> ABILITY_UPGRADE_INSTALLS;
    public static final Map<Ability, DeferredHolder<DataComponentType<?>, DataComponentType<Integer>>> ABILITY_VALUES;
    public static final Map<Ability, DeferredHolder<DataComponentType<?>, DataComponentType<Integer>>> ABILITY_BINDING_MODES;
    */
    public LightTechDataComponents() {
    }
    /*
    public static void genAbilityData() {
        for(Ability ability : Ability.values()) {
            DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> ABILITY_TOGGLE = COMPONENTS.register(ability.getName() + "_toggle", () -> DataComponentType.builder().persistent(Codec.BOOL.orElse(true)).networkSynchronized(ByteBufCodecs.BOOL).build());
            DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ABILITY_VALUE = COMPONENTS.register(ability.getName() + "_value", () -> DataComponentType.builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
            DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ABILITY_BINDING_MODE = COMPONENTS.register(ability.getName() + "_bindingmode", () -> DataComponentType.builder().persistent(Codec.INT.orElse(0)).networkSynchronized(ByteBufCodecs.VAR_INT).build());
            ABILITY_TOGGLES.put(ability, ABILITY_TOGGLE);
            ABILITY_VALUES.put(ability, ABILITY_VALUE);
            ABILITY_BINDING_MODES.put(ability, ABILITY_BINDING_MODE);
            if (ability.hasCustomSetting()) {
                DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ABILITY_CUSTOM_SETTING = COMPONENTS.register(ability.getName() + "_custom_setting", () -> DataComponentType.builder().persistent(Codec.INT.orElse(0)).networkSynchronized(ByteBufCodecs.VAR_INT).build());
                ABILITY_CUSTOM_SETTINGS.put(ability, ABILITY_CUSTOM_SETTING);
            }

            if (ability.requiresUpgrade()) {
                DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> ABILITY_UPGRADE_INSTALLED = COMPONENTS.register(ability.getName() + "_upgrade_installed", () -> DataComponentType.builder().persistent(Codec.BOOL.orElse(true)).networkSynchronized(ByteBufCodecs.BOOL).build());
                ABILITY_UPGRADE_INSTALLS.put(ability, ABILITY_UPGRADE_INSTALLED);
            }
        }

    }
    private static <T> @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, Codec<T> codec) {
        return register(name, codec, (StreamCodec)null);
    }
    private static <T> @NotNull DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, Codec<T> codec, @Nullable StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return streamCodec == null ? COMPONENTS.register(name, () -> DataComponentType.builder().persistent(codec).build()) : COMPONENTS.register(name, () -> DataComponentType.builder().persistent(codec).networkSynchronized(streamCodec).build());
    }*/

    static {
        ENTITIYTYPE = COMPONENTS.register("entitytype", () -> DataComponentType.<String>builder().persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8).build());
        FLOATINGTICKS = COMPONENTS.register("floatingticks", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
        /*
        LAVAREPAIR_LAVAPOS = COMPONENTS.register("lavapos", () -> DataComponentType.builder().persistent(BlockPos.CODEC).networkSynchronized(BlockPos.STREAM_CODEC).build());
        ABILITY_COOLDOWNS = COMPONENTS.register("ability_cooldowns", () -> DataComponentType.builder().persistent(AbilityCooldown.LIST_CODEC).networkSynchronized(AbilityCooldown.STREAM_CODEC.apply(ByteBufCodecs.list())).build());
        BOUND_INVENTORY = COMPONENTS.register("bound_inventory", () -> DataComponentType.builder().persistent(BoundInventory.CODEC).networkSynchronized(BoundInventory.STREAM_CODEC).build());
        TOOL_ENABLED = COMPONENTS.register("tool_enabled", () -> DataComponentType.builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
         */
        BOUND_GLOBAL_VEC3 = COMPONENTS.register("bound_global_vec3", () -> DataComponentType.<GlobalVec3>builder().persistent(GlobalVec3.CODEC).networkSynchronized(GlobalVec3.STREAM_CODEC).build());
        BOUND_GLOBAL_POS = COMPONENTS.register("bound_global_pos", () -> DataComponentType.<GlobalPos>builder().persistent(GlobalPos.CODEC).networkSynchronized(GlobalPos.STREAM_CODEC).build());
        LEFT_CLICK_ABILITIES = COMPONENTS.register("left_click_abilities", () -> DataComponentType.<List<String>>builder().persistent(Codec.STRING.listOf()).networkSynchronized(ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list())).build());
        /*
        ABILITY_BINDINGS = COMPONENTS.register("ability_bindings", () -> DataComponentType.builder().persistent(AbilityBinding.LIST_CODEC).networkSynchronized(AbilityBinding.STREAM_CODEC.apply(ByteBufCodecs.list())).build());
        POCKETGEN_COUNTER = COMPONENTS.register("pocketgen_counter", () -> DataComponentType.builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
        POCKETGEN_FUELMULT = COMPONENTS.register("pocketgen_fuelmult", () -> DataComponentType.builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
        POCKETGEN_MAXBURN = COMPONENTS.register("pocketgen_maxburn", () -> DataComponentType.builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
        FUELCANISTER_FUELLEVEL = COMPONENTS.register("fuelcanister_fuellevel", () -> DataComponentType.builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
        FUELCANISTER_BURNSPEED = COMPONENTS.register("fuelcanister_burnspeed", () -> DataComponentType.builder().persistent(Codec.DOUBLE).networkSynchronized(ByteBufCodecs.DOUBLE).build());
        */
        COPY_AREA_SETTINGS = COMPONENTS.register("copy_area_settings", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
        COPY_OFFSET_SETTINGS = COMPONENTS.register("copy_offset_settings", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
        COPY_FILTER_SETTINGS = COMPONENTS.register("copy_filter_settings", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
        COPY_REDSTONE_SETTINGS = COMPONENTS.register("copy_redstone_settings", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
        COPIED_MACHINE_DATA = COMPONENTS.register("copied_machine_data", () -> DataComponentType.<CustomData>builder().persistent(CustomData.CODEC).networkSynchronized(CustomData.STREAM_CODEC).build());
        /*
        PORTALGUN_UUID = COMPONENTS.register("portalgun_uuid", () -> DataComponentType.builder().persistent(UUIDUtil.CODEC).networkSynchronized(UUIDUtil.STREAM_CODEC).build());
        PORTALGUN_FAVORITE = COMPONENTS.register("portalgun_favorite", () -> DataComponentType.builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
        PORTAL_GUN_FAVORITES = COMPONENTS.register("portal_gun_favorites", () -> DataComponentType.builder().persistent(PortalDestination.CODEC.listOf()).networkSynchronized(PortalDestination.STREAM_CODEC.apply(ByteBufCodecs.list())).build());
        PORTAL_GUN_PREVIOUS = COMPONENTS.register("portal_gun_previous", () -> DataComponentType.builder().persistent(PortalDestination.CODEC).networkSynchronized(PortalDestination.STREAM_CODEC).build());
        PORTAL_GUN_STAY_OPEN = COMPONENTS.register("portal_gun_stay_open", () -> DataComponentType.builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
         */
        FLUID_CONTAINER = COMPONENTS.register("fluid_container", () -> DataComponentType.<SimpleFluidContent>builder().persistent(SimpleFluidContent.CODEC).networkSynchronized(SimpleFluidContent.STREAM_CODEC).build());
        FORGE_ENERGY = COMPONENTS.register("forge_energy", () -> DataComponentType.<Integer>builder().persistent(Codec.INT.orElse(0)).networkSynchronized(ByteBufCodecs.VAR_INT).build());
        FLUID_CANISTER_MODE = COMPONENTS.register("fluid_canister_mode", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
        STUPEFY_TARGETS = COMPONENTS.register("stupefy_targets", () -> DataComponentType.<List<String>>builder().persistent(Codec.STRING.listOf()).networkSynchronized(ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list())).build());
        ITEMSTACK_HANDLER = COMPONENTS.register("itemstack_handler", () -> DataComponentType.<ItemContainerContents>builder().persistent(ItemContainerContents.CODEC).networkSynchronized(ItemContainerContents.STREAM_CODEC).cacheEncoding().build());
        TOOL_CONTENTS = COMPONENTS.register("tool_contents", () -> DataComponentType.<ItemContainerContents>builder().persistent(ItemContainerContents.CODEC).networkSynchronized(ItemContainerContents.STREAM_CODEC).cacheEncoding().build());
        POTION_CONTENTS = COMPONENTS.register("potion_contents", () -> DataComponentType.<PotionContents>builder().persistent(PotionContents.CODEC).networkSynchronized(PotionContents.STREAM_CODEC).cacheEncoding().build());
        POTION_AMOUNT = COMPONENTS.register("potion_amount", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
        EPIC_ARROW = COMPONENTS.register("epic_arrow", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
        CUSTOM_DATA_1 = COMPONENTS.register("custom_data_1", () -> DataComponentType.<CustomData>builder().persistent(CustomData.CODEC).networkSynchronized(CustomData.STREAM_CODEC).build());
        /*
        ABILITY_TOGGLES = new HashMap();
        ABILITY_CUSTOM_SETTINGS = new HashMap();
        ABILITY_UPGRADE_INSTALLS = new HashMap();
        ABILITY_VALUES = new HashMap();
        ABILITY_BINDING_MODES = new HashMap();
        */
    }
}
