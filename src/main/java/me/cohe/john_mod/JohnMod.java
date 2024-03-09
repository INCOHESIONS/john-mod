package me.cohe.john_mod;

import net.fabricmc.api.ModInitializer;

public class JohnMod implements ModInitializer {
    @Override
    public void onInitialize() {
        JohnGlobals.LOGGER.info("John");
    }
}
