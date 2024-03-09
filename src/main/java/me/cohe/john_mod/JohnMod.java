package me.cohe.john_mod;

import me.cohe.john_mod.config.JohnConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

@Environment(EnvType.CLIENT)
public class JohnMod implements ModInitializer {
    @Override
    public void onInitialize() {
        JohnConfig.loadConfig();
        JohnGlobals.LOGGER.info("John");
    }
}
