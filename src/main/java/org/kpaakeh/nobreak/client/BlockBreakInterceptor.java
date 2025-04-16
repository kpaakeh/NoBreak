package org.kpaakeh.nobreak.client;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.client.MinecraftClient;
import org.kpaakeh.nobreak.client.config.ConfigManager;

public class BlockBreakInterceptor {
    public static void register() {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if (world.isClient) {
                ItemStack stack = player.getMainHandStack();

                if (!stack.isDamageable() || !stack.isDamaged()) return ActionResult.PASS;

                int max = stack.getMaxDamage();
                int cur = stack.getDamage();
                int threshold = ConfigManager.durabilityThreshold;

                if ((float) cur / max >= (1.0f - (threshold / 100f))) {
                    return ActionResult.FAIL;
                }
            }
            return ActionResult.PASS;
        });
    }
}
