package io.github.bizcub.dropRepair.platform;

import io.github.bizcub.dropRepair.DropRepair;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(DropRepair.MOD_ID)
@EventBusSubscriber(modid = DropRepair.MOD_ID)
public class ForgeCommon {

    public ForgeCommon(FMLJavaModLoadingContext context) {
        DropRepair.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ForgeClient.init(context);
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent.Post event) {
        if (event.level() instanceof ServerLevel serverLevel) {
            DropRepair.tick(serverLevel);
        }
    }
}
