package org.kpaakeh.nobreak.client;

import net.fabricmc.api.ClientModInitializer;
import org.kpaakeh.nobreak.client.config.ConfigManager;

public class NobreakClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfigManager.load();
        BlockBreakInterceptor.register();
    }
}