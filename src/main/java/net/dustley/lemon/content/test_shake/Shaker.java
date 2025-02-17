package net.dustley.lemon.content.test_shake;

import net.dustley.lemon.modules.camera_effects.screen_shake.ScreenShake;
import net.dustley.lemon.modules.camera_effects.screen_shake.ScreenShakeManager;
import net.dustley.lemon.modules.math.easing.Easing;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DebugStickItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Shaker extends DebugStickItem {
    public Shaker() { super(new Settings().rarity(Rarity.EPIC).maxCount(1)); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ScreenShakeManager.createScreenShake(new ScreenShake(40, 0.5f));
        return super.use(world, user, hand);
    }

}
