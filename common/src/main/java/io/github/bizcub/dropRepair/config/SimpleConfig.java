package io.github.bizcub.dropRepair.config;

import io.github.bizcub.dropRepair.DropRepair;
import io.github.bizcub.simpleConfigLib.autoconfig.ConfigHolder;
import io.github.bizcub.simpleConfigLib.autoconfig.annotation.AutoConfig;
import io.github.bizcub.simpleConfigLib.autoconfig.annotation.ListConfig;
import io.github.bizcub.simpleConfigLib.autoconfig.annotation.Slider;
import io.github.bizcub.simpleConfigLib.autoconfig.annotation.Tooltip;

import java.util.ArrayList;
import java.util.List;

@AutoConfig(name = DropRepair.MOD_ID, snakeCaseKeys = true, translate = true)
public class SimpleConfig {

    public static ConfigHolder<SimpleConfig> getInstance() {
        return ConfigHolder.register(SimpleConfig.class);
    }

    @Tooltip
    public float repairFraction = 0.125F;

    @Tooltip
    public double radius = 1.5;

    @Tooltip
    @Slider(min = 1, max = 100)
    public int checkInterval = 10;

    @Tooltip
    @ListConfig(translateElements = true)
    public List<RepairEntry> repairMaterials = new ArrayList<>();

    public static class RepairEntry {

        @Tooltip
        public String item = "";

        @Tooltip
        @ListConfig(expanded = true)
        public List<String> materials = new ArrayList<>(List.of(""));
    }
}
