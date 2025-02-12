package net.dustley.lemon.modules.camera_effects.freeze_frames;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class FreezeFrameManager {
    private static int freezeTicks = 0;
    private static int overlayTime = 0;

    // Freeze Frame Overlay
    private static HudRenderCallback overlay;

    public static void triggerFreeze(int duration) {
        FreezeFrameManager.freezeTicks = duration;
    }

    public static void triggerFreeze(int duration, HudRenderCallback overlay) {
        FreezeFrameManager.freezeTicks = duration;
        FreezeFrameManager.overlayTime = 2;
        FreezeFrameManager.overlay = overlay;
    }

    public static boolean isFrozen() {
        return freezeTicks > 0;
    }

    public static boolean getOverlayTime() {
        return overlayTime > 0;
    }

    public static void tick() {
        if (freezeTicks > 0) {
            freezeTicks--;
        }
        if (overlayTime > 0) {
            overlayTime--;
        }
    }

    public static void renderFreeze() {
        // Pause the game's frame progression
        MinecraftClient client = MinecraftClient.getInstance();
        client.getFramebuffer().beginWrite(false);
    }

    public static HudRenderCallback getOverlay() {
        return overlay;
    }
}
