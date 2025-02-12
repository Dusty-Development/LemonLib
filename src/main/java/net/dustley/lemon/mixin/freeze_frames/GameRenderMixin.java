package net.dustley.lemon.mixin.freeze_frames;

import net.dustley.lemon.modules.camera_effects.freeze_frames.FreezeFrameManager;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRenderMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
        if (FreezeFrameManager.isFrozen() && !FreezeFrameManager.getOverlayTime()) {
            FreezeFrameManager.renderFreeze();
            ci.cancel();
        }
    }
}
