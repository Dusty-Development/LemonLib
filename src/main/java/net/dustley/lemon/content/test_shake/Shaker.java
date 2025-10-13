package net.dustley.lemon.content.test_shake;

import net.dustley.lemon.modules.camera_effects.screen_shake.ScreenShake;
import net.dustley.lemon.modules.camera_effects.screen_shake.ScreenShakeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DebugStickItem;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class Shaker extends DebugStickItem {
    public Shaker(Item.Settings settings) {
        super(settings.rarity(Rarity.EPIC).maxCount(1));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ScreenShakeManager.createScreenShake(new ScreenShake(40, 0.5f));
        return super.use(world, user, hand);
    }

}
