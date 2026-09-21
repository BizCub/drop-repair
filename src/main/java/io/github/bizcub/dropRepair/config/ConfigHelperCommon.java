package io.github.bizcub.dropRepair.config;

/*? fabric*/ import net.fabricmc.loader.api.FabricLoader;
/*? forge*/ //import net.minecraftforge.fml.ModList;
/*? neoforge*/ //import net.neoforged.fml.ModList;

public class ConfigHelperCommon {
    public static boolean isModLoaded(String modId) {
        /*? fabric*/ return FabricLoader.getInstance().isModLoaded(modId);
        /*? (forge && <26.1) || neoforge*/ //return ModList.get().isLoaded(modId);
        /*? forge && >=26.1*/ //return ModList.isLoaded(modId);
    }

    public static boolean isConfigLoaded() {
        return isModLoaded("simple_config_lib");
    }
}
