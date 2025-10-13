package net.dustley.lemon.content.registry;

import net.dustley.lemon.LemonLib;
import net.dustley.lemon.content.test_shake.Shaker;
import net.dustley.lemon.content.test_stick.TestStick;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public interface ModItems {

    // Items //
    Item TESTER = register("tester", TestStick::new);
    Item SHAKER = register("shaker", Shaker::new);

    // Utility Functions //
    static Item register(String id, Function<Item.Settings, Item> factory) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, LemonLib.id(id));
        Item item = factory.apply(new Item.Settings().registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    // Initializer //
    static void registerModItems() {
        LemonLib.LOGGER.info("Registering Mod Items for " + LemonLib.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(TESTER);
            entries.add(SHAKER);
        });
    }
}
