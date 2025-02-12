package net.dustley.lemon.modules.camera_effects.freeze_frames;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class DefaultOverlay implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext context, RenderTickCounter counter) {
        if (FreezeFrameManager.getOverlay() != null) {
            FreezeFrameManager.getOverlay().onHudRender(context, counter);
        }
    }
}
