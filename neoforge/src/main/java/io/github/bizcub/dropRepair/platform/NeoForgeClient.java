package io.github.bizcub.dropRepair.platform;

import io.github.bizcub.dropRepair.DropRepair;
import io.github.bizcub.dropRepair.config.ConfigHelperClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = DropRepair.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeClient {

    public NeoForgeClient(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class,
                (container, parent) -> ConfigHelperClient.getScreen(parent));
    }
}
