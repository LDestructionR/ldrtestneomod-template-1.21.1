package com.ldrtest.ldrtestmod.Block;

import com.ldrtest.ldrtestmod.LDRTESTNEOMod; // 确保导入了你的主类以获取 MOD_ID
import com.ldrtest.ldrtestmod.item.modItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class modBlocks {
    // 确保这里的 MOD_ID 是正确的，通常建议直接从主类引用
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LDRTESTNEOMod.MOD_ID);





    public static final DeferredBlock<Block> MOON_STONE = registerBlocks("moon_stone", () -> new Block(BlockBehaviour.Properties.of().strength(5.0F, 9.0F).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> MOONLIGHT_STONE = registerBlocks("moonlight_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> MOON_BLOCK = registerBlocks("moon_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> MOON_ESSENCE_BLOCK = registerBlocks("moon_essence_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK).mapColor(MapColor.STONE)));







    private static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
        modItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static  <T extends Block> DeferredBlock<T> registerBlocks(String name, Supplier<T> block) {
        DeferredBlock<T> blocks = BLOCKS.register(name, block);
        registerBlockItems(name, blocks);
        return blocks;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }





}