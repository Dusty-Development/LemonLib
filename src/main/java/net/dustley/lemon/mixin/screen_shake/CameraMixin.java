package net.dustley.lemon.mixin.screen_shake;

import net.dustley.lemon.modules.camera_effects.screen_shake.ScreenShakeManager;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraMixin {
    @Inject(method = "update", at = @At("RETURN"))
    private void screenshake(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        ScreenShakeManager.cameraTick((Camera) (Object) this);
    }
}
