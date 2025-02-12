package net.dustley.lemon;

import net.dustley.lemon.modules.camera_effects.freeze_frames.DefaultOverlay;
import net.dustley.lemon.modules.citrus_physics.debug.CitrusDebugRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class LemonLibClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		/*
			Debug Renderer
		 */
		CitrusDebugRenderer.registerEvents();

		/*
			Freeze Frame Overlay HUD Render Callback
		 */
		HudRenderCallback.EVENT.register(new DefaultOverlay());
	}
}