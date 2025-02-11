package net.dustley.lemon;

import net.dustley.lemon.content.registry.ModItems;
import net.dustley.lemon.modules.citrus_physics.debug.CitrusDebugRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LemonLibClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		CitrusDebugRenderer.registerEvents();
	}

}