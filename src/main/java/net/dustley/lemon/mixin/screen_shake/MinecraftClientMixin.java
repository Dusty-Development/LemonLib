package net.dustley.lemon.mixin.screen_shake;

import net.dustley.lemon.modules.camera_effects.screen_shake.ScreenShakeManager;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
final class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void clientTick(CallbackInfo ci) {
        ScreenShakeManager.clientTick(MinecraftClient.getInstance().gameRenderer.getCamera());
    }

}