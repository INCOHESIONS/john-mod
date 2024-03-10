package me.cohe.john_mod.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.cohe.john_mod.JohnArmorMaterials;
import me.cohe.john_mod.JohnRenderType;
import me.cohe.john_mod.JohnUtils;
import me.shedaniel.clothconfig2.api.ConfigBuilder;

public class JohnModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            final var config = JohnConfig.config;

            final var configBuilder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(JohnUtils.translate("config.john_mod.title"))
                    .setSavingRunnable(JohnConfig::saveConfig);

            final var settings = configBuilder.getOrCreateCategory(JohnUtils.translate("config.john_mod.category"));

            final var entryBuilder = configBuilder.entryBuilder();

            settings.addEntry(entryBuilder.startStrField(JohnUtils.translate("config.john_mod.name_tag"), config.nameTagText)
                    .setDefaultValue("John {mob_name}")
                    .setTooltip(JohnUtils.translate("config.john_mod.name_tag.tooltip"))
                    .setSaveConsumer(value -> config.nameTagText = value)
                    .build());

            settings.addEntry(entryBuilder.startEnumSelector(JohnUtils.translate("config.john_mod.armor_type"), JohnArmorMaterials.class, config.johnMaterial)
                    .setDefaultValue(JohnArmorMaterials.Gold)
                    .setTooltip(JohnUtils.translate("config.john_mod.armor_type.tooltip"))
                    .setSaveConsumer(value -> config.johnMaterial = value)
                    .build());

            settings.addEntry(entryBuilder.startEnumSelector(JohnUtils.translate("config.john_mod.rendering_type"), JohnRenderType.class, config.renderType)
                    .setDefaultValue(JohnRenderType.WhenVisible)
                    .setTooltip(JohnUtils.translate("config.john_mod.rendering_type.tooltip"))
                    .setSaveConsumer(value -> config.renderType = value)
                    .build());

            settings.addEntry(entryBuilder.startBooleanToggle(JohnUtils.translate("config.john_mod.override_custom_names"), config.overrideCustomNames)
                    .setDefaultValue(false)
                    .setTooltip(JohnUtils.translate("config.john_mod.override_custom_names.tooltip"))
                    .setSaveConsumer(value -> config.overrideCustomNames = value)
                    .build());

            return configBuilder.build();
        };
    }
}
