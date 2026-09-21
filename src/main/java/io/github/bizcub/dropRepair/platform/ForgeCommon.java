//? forge {
/*package io.github.bizcub.dropRepair.platform;

import io.github.bizcub.dropRepair.DropRepair;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(DropRepair.MOD_ID)
@EventBusSubscriber(modid = DropRepair.MOD_ID)
public class ForgeCommon {

    public ForgeCommon() {
        DropRepair.init();

        if (FMLEnvironment.dist.isClient()) {
            ForgeClient.init();
        }
    }

    @SubscribeEvent //~ if >=1.20.2 'LevelTickEvent' -> 'LevelTickEvent.Post'
    public static void onLevelTick(TickEvent.LevelTickEvent.Post event) {
        //~ if >=26.1.2 '.level' -> '.level()'
        if (event.level() instanceof ServerLevel serverLevel) {
            DropRepair.tick(serverLevel);
        }
    }
}*///?}
