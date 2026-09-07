package io.github.bizcub.dropRepair;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class DropRepair {
    public static final String MOD_ID = /*$ mod_id*/ "drop_repair";

    private static final double RADIUS = 1.5;
    public static final int CHECK_INTERVAL = 10;
    private static final float REPAIR_FRACTION = 0.125F;

    public static void tick(final ServerLevel level) {
        if (level.getGameTime() % CHECK_INTERVAL != 0) {
            return;
        }
        for (ItemEntity entity : level.getEntities(EntityTypes.ITEM,e -> true)) {
            tryRepairNeighbours(level, entity);
        }
    }

    public static void tryRepairNeighbours(ServerLevel level, ItemEntity materialEntity) {
        ItemStack material = materialEntity.getItem();
        if (material.isEmpty() || !materialEntity.isAlive()) {
            return;
        }

        AABB box = materialEntity.getBoundingBox().inflate(RADIUS, 0.5, RADIUS);
        List<ItemEntity> targets = level.getEntitiesOfClass(
                ItemEntity.class, box,
                other -> other != materialEntity
                        && other.isAlive()
                        && isRepairableBy(other.getItem(), material)
        );

        if (targets.isEmpty()) {
            return;
        }

        float fractionPerItem = (material.getCount() * REPAIR_FRACTION) / targets.size();

        for (ItemEntity target : targets) {
            repairStack(target.getItem(), fractionPerItem);
        }

        materialEntity.discard();
    }

    private static boolean isRepairableBy(ItemStack tool, ItemStack material) {
        return tool.isDamageableItem()
                && tool.isDamaged()
                && tool.isValidRepairItem(material);
    }

    private static void repairStack(ItemStack tool, float fraction) {
        int repairAmount = Math.max(1, Math.round(tool.getMaxDamage() * fraction));
        tool.setDamageValue(Math.max(0, tool.getDamageValue() - repairAmount));
    }
}
