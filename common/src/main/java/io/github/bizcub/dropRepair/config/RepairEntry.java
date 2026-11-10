package io.github.bizcub.dropRepair.config;

import io.github.bizcub.simpleConfigLib.autoconfig.annotation.ListConfig;
import io.github.bizcub.simpleConfigLib.autoconfig.annotation.Tooltip;

import java.util.ArrayList;
import java.util.List;

public class RepairEntry {

    @Tooltip
    public String item = "";

    @Tooltip
    @ListConfig(expanded = true)
    public List<String> materials = new ArrayList<>(List.of(""));
}
