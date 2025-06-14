package net.dustley.lemon.content.test_shake;

import net.dustley.lemon.LemonLib;
import net.dustley.lemon.modules.camera_effects.screen_shake.ScreenShake;
import net.dustley.lemon.modules.camera_effects.screen_shake.ScreenShakeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DebugStickItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class Shaker extends DebugStickItem {
    public Shaker() {
        super(new Settings().rarity(Rarity.EPIC).maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, LemonLib.id("shaker"))));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ScreenShakeManager.createScreenShake(new ScreenShake(40, 0.5f));
        return super.use(world, user, hand);
    }

}
