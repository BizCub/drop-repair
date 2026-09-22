package io.github.bizcub.dropRepair.config;

import io.github.bizcub.dropRepair.DropRepair;
import io.github.bizcub.simpleConfigLib.autoconfig.ConfigHolder;
import io.github.bizcub.simpleConfigLib.autoconfig.annotation.*;

import java.util.List;

@AutoConfig(name = DropRepair.MOD_ID, snakeCaseKeys = true, translate = true)
public class SimpleConfig implements Config {

    public static ConfigHolder<SimpleConfig> getInstance() {
        return ConfigHolder.register(SimpleConfig.class);
    }

    @Tooltip
    public float repairFraction = Config.super.repairFraction();

    @Tooltip
    public double radius = Config.super.radius();

    @Tooltip
    @Slider(min = 1, max = 100)
    public int checkInterval = Config.super.checkInterval();

    @Tooltip
    @ListConfig(translateElements = true)
    public List<RepairEntry> repairMaterials = Config.super.repairMaterials();

    @Override
    public float repairFraction() {
        return this.repairFraction;
    }

    @Override
    public double radius() {
        return this.radius;
    }

    @Override
    public int checkInterval() {
        return this.checkInterval;
    }

    @Override
    public List<RepairEntry> repairMaterials() {
        return this.repairMaterials;
    }
}
