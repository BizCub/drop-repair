package io.github.bizcub.dropRepair.platform;

import io.github.bizcub.dropRepair.config.ConfigHelperClient;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ForgeClient {

    public static void init(FMLJavaModLoadingContext context) {
        context.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
                new ConfigScreenHandler.ConfigScreenFactory((minecraft, parent) -> ConfigHelperClient.getScreen(parent)));
    }
}
