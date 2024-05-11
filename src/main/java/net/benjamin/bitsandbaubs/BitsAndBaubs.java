package net.benjamin.bitsandbaubs;

import com.mojang.logging.LogUtils;
import net.benjamin.bitsandbaubs.block.ModBlocks;
import net.benjamin.bitsandbaubs.block.entity.ModBlockEntities;
import net.benjamin.bitsandbaubs.entity.ModEntities;
import net.benjamin.bitsandbaubs.entity.client.AirShipRenderer;
import net.benjamin.bitsandbaubs.entity.client.TerracottaGolemRenderer;
import net.benjamin.bitsandbaubs.item.BitsAndBaubsModTab;
import net.benjamin.bitsandbaubs.item.ModItems;
import net.benjamin.bitsandbaubs.loot.ModLootModifiers;
import net.benjamin.bitsandbaubs.recipe.ModRecipes;
import net.benjamin.bitsandbaubs.screen.AlchemyTableScreen;
import net.benjamin.bitsandbaubs.screen.ModMenuTypes;
import net.benjamin.bitsandbaubs.util.ModWoodTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BitsAndBaubs.MOD_ID)
public class BitsAndBaubs
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "bitsandbaubs";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public BitsAndBaubs()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BitsAndBaubsModTab.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModEntities.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            Sheets.addWoodType(ModWoodTypes.CORRUPTED);

            EntityRenderers.register(ModEntities.TERRACOTTA_GOLEM.get(), TerracottaGolemRenderer::new);
            EntityRenderers.register(ModEntities.AIR_SHIP.get(), p_174010_ -> new AirShipRenderer(p_174010_));

            MenuScreens.register(ModMenuTypes.ALCHEMY_MENU.get(), AlchemyTableScreen::new);
        }
    }
}
