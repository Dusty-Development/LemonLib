package net.dustley.lemon.content.registry;

import net.dustley.lemon.LemonLib;
import net.dustley.lemon.content.test_stick.TestStick;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;


public class ModItems {

    // Items //
    public static final Item TESTER = registerItem("tester", new TestStick());

    // Utility Functions //
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, LemonLib.identifier(name), item);
    }

    // Initializer //
    public static void registerModItems() {
        LemonLib.LOGGER.info("Registering Mod Items for " + LemonLib.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(TESTER));
    }
}
