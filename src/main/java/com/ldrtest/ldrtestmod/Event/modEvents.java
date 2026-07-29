package com.ldrtest.ldrtestmod.Event;



import com.ldrtest.ldrtestmod.LDRTESTNEOMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = LDRTESTNEOMod.MOD_ID)
public class modEvents {

    // --- 配置区域 ---

    // 1. 定义月球维度的 ResourceKey
    // ⚠️ 注意：这里的 "ldrtestneo_mod" 必须是你 modid，"moon" 必须是你维度 json 文件的名称
    private static final ResourceKey<Level> MOON_DIMENSION_KEY = ResourceKey.create(
            Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(LDRTESTNEOMod.MOD_ID, "moon")
    );

    // 2. 定义触发高度
    private static final double OVERWORLD_TRIGGER_HEIGHT = 500.0; // 主世界 -> 月球
    private static final double MOON_RETURN_HEIGHT = 495.0;       // 月球 -> 主世界

    // 3. 定义比例 (1:0.25 意味着 月球 = 主世界 / 4)
    private static final double SCALE_FACTOR = 0.25;

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Pre event) {
        var entity = event.getEntity();

        // 只处理玩家
        if (!(entity instanceof ServerPlayer player)) return;

        // ⚠️ 关键修复：使用 player.serverLevel() 获取服务端层级
        // 这比 entity.level() 更安全，且不会触发 AutoCloseable 警告
        ServerLevel currentLevel = player.serverLevel();
        ResourceKey<Level> currentDim = currentLevel.dimension();

        // --- 逻辑分支 A: 在主世界，且高度超过 500 -> 去月球 ---
        if (currentDim == Level.OVERWORLD && player.getY() >= OVERWORLD_TRIGGER_HEIGHT) {
            teleportToMoon(player);
        }
        // --- 逻辑分支 B: 在月球，且高度超过 490 -> 回主世界 ---
        else if (currentDim == MOON_DIMENSION_KEY && player.getY() >= MOON_RETURN_HEIGHT) {
            teleportToOverworld(player);
        }
    }

    /**
     * 传送去月球 (坐标缩小 4 倍)
     */
    private static void teleportToMoon(ServerPlayer player) {
        ServerLevel moonLevel = player.server.getLevel(MOON_DIMENSION_KEY);
        if (moonLevel == null) return; // 防止维度未加载导致崩溃

        // 计算新坐标：主世界坐标 * 0.25
        double newX = player.getX() * SCALE_FACTOR;
        double newZ = player.getZ() * SCALE_FACTOR;
        double newY = 490.0; // 到达月球后的默认落地高度，可按需修改

        // 执行跨维度传送
        player.teleportTo(
                moonLevel,
                newX, newY, newZ,
                player.getYRot(), // 保持水平朝向
                player.getXRot()  // 保持垂直朝向
        );
    }

    /**
     * 传送回主世界 (坐标放大 4 倍)
     */
    private static void teleportToOverworld(ServerPlayer player) {
        ServerLevel overworldLevel = player.server.getLevel(Level.OVERWORLD);
        if (overworldLevel == null) return;

        // 计算新坐标：月球坐标 / 0.25 (即 * 4)
        double newX = player.getX() / SCALE_FACTOR;
        double newZ = player.getZ() / SCALE_FACTOR;
        double newY = 495.0;

        // 执行跨维度传送
        player.teleportTo(
                overworldLevel,
                newX, newY, newZ,
                player.getYRot(),
                player.getXRot()
        );
    }
}