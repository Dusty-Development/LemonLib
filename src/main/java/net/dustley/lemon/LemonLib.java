package net.dustley.lemon;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LemonLib implements ModInitializer {
	public static final String MOD_ID = "lemon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}