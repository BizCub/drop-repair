package io.github.bizcub.dropRepair;

import io.github.bizcub.dropRepair.config.SimpleConfig;
import io.github.bizcub.dropRepair.config.SimpleConfig.RepairEntry;
import io.github.bizcub.simpleConfigLib.util.Id;
import io.github.bizcub.simpleConfigLib.util.ItemUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class DropRepair {
    public static final String MOD_ID = "drop_repair";

    private static SimpleConfig config;

    public static void init() {
        config = SimpleConfig.getInstance().get();
    }

    public static void tick(final ServerLevel level) {
        if (level.getGameTime() % config.checkInterval != 0) {
            return;
        }
        for (ItemEntity entity : level.getEntities(EntityTypes.ITEM, e -> true)) {
            tryRepairNeighbours(level, entity);
        }
    }

    public static void tryRepairNeighbours(ServerLevel level, ItemEntity materialEntity) {
        ItemStack material = materialEntity.getItem().copy();
        if (material.isEmpty() || !materialEntity.isAlive()) {
            return;
        }

        double radius = config.radius;
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

        List<ItemEntity> damagedTargets = new ArrayList<>(targets);
        while (!material.isEmpty() && !damagedTargets.isEmpty()) {
            float fractionPerItem = config.repairFraction / damagedTargets.size();
            for (ItemEntity target : damagedTargets) {
                ItemStack tool = target.getItem().copy();
                repairStack(tool, fractionPerItem);
                target.setItem(tool);
            }
            material.shrink(1);
            damagedTargets.removeIf(target -> !target.getItem().isDamaged());
        }

        for (ItemEntity target : targets) {
            level.sendParticles(ParticleTypes.GLOW,
                    target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(),
                    9, 0.03, 0.03, 0.03, 0.08);
        }

        if (material.isEmpty()) {
            materialEntity.discard();
        } else {
            materialEntity.setItem(material);
        }
    }

    private static boolean isRepairableBy(ItemStack tool, ItemStack material) {
        return tool.isDamageableItem()
                && tool.isDamaged()
                && (tool.isValidRepairItem(material) || matchesRepairEntry(tool, material));
    }

    private static boolean matchesRepairEntry(ItemStack tool, ItemStack material) {
        for (RepairEntry entry : config.repairMaterials) {
            if (ItemUtil.idOf(tool).equals(Id.parse(entry.item))
                    && entry.materials.stream()
                    .map(Id::parse)
                    .anyMatch(id -> id.equals(ItemUtil.idOf(material)))) {
                return true;
            }
        }
        return false;
    }

    private static void repairStack(ItemStack tool, float fraction) {
        int repairAmount = Math.max(1, Math.round(tool.getMaxDamage() * fraction));
        tool.setDamageValue(Math.max(0, tool.getDamageValue() - repairAmount));
    }
}
