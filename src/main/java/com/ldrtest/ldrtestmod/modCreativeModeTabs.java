package com.ldrtest.ldrtestmod;

import com.ldrtest.ldrtestmod.Block.modBlocks;
import com.ldrtest.ldrtestmod.item.modItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;

import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class modCreativeModeTabs {
    // 创建一个延迟注册表，用于存放创造模式标签页（CreativeModeTab），它们都将在 "ldrtestneo_mod" 命名空间下注册
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,"ldrtestneo_mod");

    // 创建一个 ID 为 "ldrtestneo_mod:example_tab" 的创造模式标签页，用于展示示例物品，并将其位置安排在“战斗”标签页之后
    public static final Supplier<CreativeModeTab> LDRTESTMOD_TAB =
            CREATIVE_MODE_TABS.register("ldrtestmod_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ldrtestneo_mod")) // 你的创造模式标签页标题所使用的语言键（用于多语言翻译）
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> modItems.MOON_ESSENCE.get().getDefaultInstance())// 标签页图标所使用的物品
            .displayItems((parameters, output) -> {
                output.accept(modItems.MOONLIGHT_ITEM); // 将示例物品添加到该标签页中。对于自定义标签页，推荐使用这种方法而不是事件监听
                output.accept(modItems.MOON_INGOT);
                output.accept(modItems.MOON_ESSENCE);
                output.accept(modItems.MOONLIGHT_INGOT);
                output.accept(modBlocks.MOON_ESSENCE_BLOCK);
                output.accept(modBlocks.MOON_BLOCK);
                output.accept(modBlocks.MOONLIGHT_STONE);
                output.accept(modBlocks.MOON_STONE);
                output.accept(modBlocks.HUANG_BLOCK);
            }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
