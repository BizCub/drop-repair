//? neoforge {  
/*package io.github.bizcub.dropRepair.platform;  
  
import io.github.bizcub.dropRepair.DropRepair;
import net.minecraft.server.level.ServerLevel;  
import net.neoforged.bus.api.SubscribeEvent;  
import net.neoforged.fml.common.Mod;  
import net.neoforged.fml.common.EventBusSubscriber;  
import net.neoforged.neoforge.event.tick.LevelTickEvent;  
  
@Mod(DropRepair.MOD_ID)
@EventBusSubscriber(modid = DropRepair.MOD_ID)
public class NeoForgeCommon {

    public NeoForgeCommon() {
        DropRepair.init();
    }
  
    @SubscribeEvent  
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {  
            DropRepair.tick(serverLevel);
        }  
    }  
}*///?}
