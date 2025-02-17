package net.dustley.lemon.content.registry;

import net.dustley.lemon.LemonLib;
import net.dustley.lemon.content.test_freezer.Freezer;
import net.dustley.lemon.content.test_shake.Shaker;
import net.dustley.lemon.content.test_stick.TestStick;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {

    // Items //
    public static final Item TESTER = registerItem("tester", new TestStick());
    public static final Item FREEZER = registerItem("freezer", new Freezer());
    public static final Item SHAKER = registerItem("shaker", new Shaker());

    // Utility Functions //
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, LemonLib.id(name), item);
    }

    // Initializer //
    public static void registerModItems() {
        LemonLib.LOGGER.info("Registering Mod Items for " + LemonLib.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(TESTER);
            entries.add(FREEZER);
            entries.add(SHAKER);
        });
    }
}
