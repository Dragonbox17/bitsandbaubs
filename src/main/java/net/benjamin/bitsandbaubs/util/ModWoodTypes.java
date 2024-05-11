package net.benjamin.bitsandbaubs.util;

import net.benjamin.bitsandbaubs.BitsAndBaubs;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType CORRUPTED = WoodType.register(
            new WoodType(BitsAndBaubs.MOD_ID + ":corrupted", BlockSetType.OAK));
}
