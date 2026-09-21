//? fabric {
package io.github.bizcub.dropRepair.platform;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.bizcub.dropRepair.config.ConfigHelperClient;

public class FabricClient {

    public static class ModMenu implements ModMenuApi {

        @Override
        public ConfigScreenFactory<?> getModConfigScreenFactory() {
            return ConfigHelperClient::getScreen;
        }
    }
}//?}
