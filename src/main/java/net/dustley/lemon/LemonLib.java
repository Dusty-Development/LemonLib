package net.dustley.lemon;

import net.dustley.lemon.content.registry.ModItems;
import net.dustley.lemon.modules.camera_effects.freeze_frames.FreezeFrameManager;
import net.dustley.lemon.modules.citrus_physics.debug.CitrusDebugRenderer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LemonLib implements ModInitializer {
	public static final String MOD_ID = "lemon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Identifier id(String id) { return Identifier.of(MOD_ID, id); }

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

//		ModItems.registerModItems();
		CitrusDebugRenderer.registerEvents();

		/*
			Freeze Frame Tick Caller
		 */
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			FreezeFrameManager.tick();
		});
	}
}