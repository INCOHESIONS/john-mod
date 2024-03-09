package me.cohe.john_mod.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.cohe.john_mod.JohnArmorMaterials;
import me.cohe.john_mod.JohnRenderType;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class JohnConfig {
    public static JohnConfig config = new JohnConfig();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "JohnMod.json");
    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public static void loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (Reader reader = new FileReader(CONFIG_FILE)) {
                JohnConfig.config = GSON.fromJson(reader, JohnConfig.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        config = new JohnConfig();
        saveConfig();
    }

    public static void saveConfig() {
        try (Writer writer = new FileWriter(CONFIG_FILE, false)) {
            if (CONFIG_FILE.createNewFile()) {
                GSON.toJson(config, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String nameTagText = "John {mob_name}";

    public JohnRenderType renderType = JohnRenderType.WhenVisible;

    public JohnArmorMaterials johnMaterial = JohnArmorMaterials.Gold;
}
