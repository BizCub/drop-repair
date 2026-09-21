package io.github.bizcub.dropRepair.config;

import io.github.bizcub.dropRepair.DropRepair;
import io.github.bizcub.simpleConfigLib.autoconfig.gui.ConfigScreens;
import net.minecraft.client.gui.screens.Screen;

public class ConfigHelperClient {

    public static Screen getScreen(Screen parent) {
        return ConfigHelperCommon.isConfigLoaded()
                ? ConfigScreens.open(DropRepair.MOD_ID, parent)
                : parent;
    }
}
