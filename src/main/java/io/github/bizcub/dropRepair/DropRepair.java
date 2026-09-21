package io.github.bizcub.dropRepair;

import io.github.bizcub.dropRepair.config.Config;
import io.github.bizcub.dropRepair.config.ConfigHelperCommon;
import io.github.bizcub.dropRepair.config.SimpleConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class DropRepair {
    public static final String MOD_ID = /*$ mod_id*/ "drop_repair";

    public static void init() {
        if (ConfigHelperCommon.isConfigLoaded()) {
            Config.set(SimpleConfig.getInstance().get());
        }
    }

    public static void tick(final ServerLevel level) {
        if (level.getGameTime() % Config.get().checkInterval() != 0) {
            return;
        }
        for (ItemEntity entity : level.getEntities(EntityTypes.ITEM, e -> true)) {
            tryRepairNeighbours(level, entity);
        }
    }

    public static void tryRepairNeighbours(ServerLevel level, ItemEntity materialEntity) {
        ItemStack material = materialEntity.getItem();
        if (material.isEmpty() || !materialEntity.isAlive()) {
            return;
        }

        double radius = Config.get().radius();
        AABB box = materialEntity.getBoundingBox().inflate(radius, 0.5, radius);
        List<ItemEntity> targets = level.getEntitiesOfClass(
                ItemEntity.class, box,
                other -> other != materialEntity
                        && other.isAlive()
                        && isRepairableBy(other.getItem(), material)
        );

        if (targets.isEmpty()) {
            return;
        }

        float fractionPerItem = (material.getCount() * Config.get().repairFraction()) / targets.size();

        for (ItemEntity target : targets) {
            repairStack(target.getItem(), fractionPerItem);
        }

        materialEntity.discard();
    }

    private static boolean isRepairableBy(ItemStack tool, ItemStack material) {
        return tool.isDamageableItem()
                && tool.isDamaged()
                //~ if >=1.21.2 'getItem().isValidRepairItem(tool, material)' -> 'isValidRepairItem(material)'
                && tool.isValidRepairItem(material);
    }

    private static void repairStack(ItemStack tool, float fraction) {
        int repairAmount = Math.max(1, Math.round(tool.getMaxDamage() * fraction));
        tool.setDamageValue(Math.max(0, tool.getDamageValue() - repairAmount));
    }
}
