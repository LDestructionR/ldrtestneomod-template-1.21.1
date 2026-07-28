package com.ldrtest.ldrtestmod.Block;

import com.ldrtest.ldrtestmod.LDRTESTNEOMod; // 确保导入了你的主类以获取 MOD_ID
import com.ldrtest.ldrtestmod.item.modItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.function.Supplier;

public class modBlocks {
    // 确保这里的 MOD_ID 是正确的，通常建议直接从主类引用
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LDRTESTNEOMod.MOD_ID);





    public static final DeferredBlock<Block> MOON_STONE = registerBlocks("moon_stone", () -> new Block(BlockBehaviour.Properties
            .of()
            .strength(3.0F, 99.0F)
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()
            ),false);
    public static final DeferredBlock<Block> HUANG_BLOCK = registerBlocks("huang_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 4.0F).mapColor(MapColor.GOLD)),false);
    public static final DeferredBlock<Block> MOONLIGHT_STONE = registerBlocks("moonlight_stone", () -> new DropExperienceBlock(
            UniformInt.of(9, 81),
            BlockBehaviour.Properties
                    .of()
                    .strength(5.0F, 99.0F)
                    .mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    ),true);
    public static final DeferredBlock<Block> MOON_BLOCK = registerBlocks("moon_block", () -> new Block(BlockBehaviour.Properties
            .of()
            .strength(32.0F, 9999.0F)
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()),true);
    public static final DeferredBlock<Block> MOON_ESSENCE_BLOCK = registerBlocks("moon_essence_block", () -> new Block(BlockBehaviour.Properties
            .of()
            .strength(64.0F, 9999.0F)
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()),true);







/**
 * 注册方块及其对应物品的方法
 * 这是一个泛型方法，用于注册方块和其对应的物品，并可选择性地设置防火属性
 *
 * @param <T> 泛型类型，继承自Block
 * @param name 方块的注册名称
 * @param blockSupplier 方块的提供者，用于创建方块实例
 * @param fireResistant 是否具有防火属性
 * @return 返回一个DeferredBlock对象，用于后续的引用和操作
 */
    private static <T extends Block> DeferredBlock<T> registerBlocks(String name, Supplier<T> blockSupplier, boolean fireResistant) {
    // 注册方块，使用提供的名称和方块提供者
        DeferredBlock<T> block = BLOCKS.register(name, blockSupplier);

    // 注册对应的物品
        modItems.ITEMS.register(name, () -> {
        // 创建物品属性
            Item.Properties props = fireResistant
                // 如果需要防火，创建具有防火属性的物品属性
                    ? new Item.Properties().fireResistant()
                // 否则创建普通物品属性
                    : new Item.Properties();
        // 返回一个新的BlockItem实例，使用已注册的方块和创建的属性
            return new BlockItem(block.get(), props);
        });

    // 返回已注册的DeferredBlock对象
        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }





}