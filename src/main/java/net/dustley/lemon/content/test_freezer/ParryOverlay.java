package net.dustley.lemon.content.test_freezer;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dustley.lemon.LemonLib;
import net.dustley.lemon.modules.camera_effects.freeze_frames.FreezeFrameManager;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class ParryOverlay implements HudRenderCallback {

    private static final Identifier PARRY_OVERLAY = LemonLib.id("textures/parry.png");

    @Override
    public void onHudRender(DrawContext context, RenderTickCounter counter) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null || client.player == null) {
            return; // Ensure the client and player are valid
        }

        RenderSystem.enableBlend();

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        // Draw the texture stretched over the entire screen
        if (FreezeFrameManager.getOverlayTime()) {
            context.drawTexture(PARRY_OVERLAY, 0, 0, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);
        }
    }
}