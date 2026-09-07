//? fabric {  
package io.github.bizcub.dropRepair.platform;

import io.github.bizcub.dropRepair.DropRepair;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class Fabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getAllLevels().forEach(DropRepair::tick);
        });
    }
}//?}
