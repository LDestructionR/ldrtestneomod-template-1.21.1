package com.ldrtest.ldrtestmod.item;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

import net.neoforged.bus.api.IEventBus;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;



public class modItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("ldrtestneo_mod");

    public static final DeferredItem<Item> MOONLIGHT_ITEM = ITEMS.register("moonlight_item",() -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> MOONLIGHT_INGOT = ITEMS.register("moonlight_ingot",() -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> MOON_ESSENCE = ITEMS.register("moon_essence",() -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> MOON_INGOT = ITEMS.register("moon_ingot",() -> new Item(new Item.Properties().fireResistant()));

    //public static final Item BB = registerItem("bb", new Item(new Item.Properties()));                不可用
    //public static final DeferredItem<Item> MOONLIGHT_SWORD = ITEMS.register("moonlight_sword",() -> new SwordItem(modTiers.MOONLIGHT new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
