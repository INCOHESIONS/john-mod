package me.cohe.john_mod.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.cohe.john_mod.JohnGlobals;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.stream.Collectors;

/*
    Inspired by: https://github.com/Awakened-Redstone/Subathon
*/
public final class JohnConfig {
    private static JohnConfigData configData = new JohnConfigData();
    private static final File CONFIG_FILE = new File(getConfigDirectory(), JohnGlobals.CONFIG_FILENAME);
    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .serializeNulls()
            .create();

    public static JohnConfigData getConfigData() {
        return configData;
    }

    public static File getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir().toFile();
    }

    public static void loadConfig() {
        final var dir = getConfigDirectory();

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
            if (!CONFIG_FILE.exists()) {
                configData = new JohnConfigData();
                saveConfig();
                return;
            }
        }

        if (CONFIG_FILE.exists() && CONFIG_FILE.isFile() && CONFIG_FILE.canRead()) {
            try (final var reader = new FileReader(CONFIG_FILE)) {
                configData = GSON.fromJson(reader, JohnConfigData.class);
            } catch (IOException e) {
                JohnGlobals.LOGGER.error("Failed to load configuration!", e);
            }
        } else {
            JohnGlobals.LOGGER.error("Failed to load configuration file either because it didn't exist, or was unreadable.");
        }
    }

    public static void saveConfig() {
        try (final var writer = new FileWriter(CONFIG_FILE)) {
            writer.write(GSON.toJson(configData));
        } catch (IOException e) {
            JohnGlobals.LOGGER.warn("Failed to write JSON data to the config file.", e);
        }
    }
}
