package net.dustley.lemon.content.test_freezer;

import net.dustley.lemon.modules.camera_effects.freeze_frames.FreezeFrameManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Freezer extends Item {
    public Freezer() { super(new Settings().rarity(Rarity.EPIC).maxCount(1)); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        FreezeFrameManager.triggerFreeze(5, new ParryOverlay());
        return super.use(world, user, hand);
    }
}