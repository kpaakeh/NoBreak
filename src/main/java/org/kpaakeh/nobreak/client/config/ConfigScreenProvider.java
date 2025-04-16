package org.kpaakeh.nobreak.client.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreenProvider {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("NoBreak Config"));

        ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder
                .startIntField(Text.literal("Durability Threshold (%)"), ConfigManager.durabilityThreshold)
                .setDefaultValue(10)
                .setMin(1)
                .setMax(100)
                .setSaveConsumer(newValue -> ConfigManager.durabilityThreshold = newValue)
                .build());

        builder.setSavingRunnable(ConfigManager::save);

        return builder.build();
    }
}
