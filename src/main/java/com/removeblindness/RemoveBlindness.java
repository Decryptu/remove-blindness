// src/main/java/com/removeblindness/RemoveBlindness.java
package com.removeblindness;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@Mod(RemoveBlindness.MODID)
public class RemoveBlindness {
    public static final String MODID = "removeblindness";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RemoveBlindness(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(this);
        LOGGER.info("RemoveBlindness mod initialized - will prevent BasicLogin blindness effect");
    }

    @SubscribeEvent
    public void onEffectAdded(MobEffectEvent.Added event) {
        if (event.getEntity() instanceof Player) {
            if (event.getEffectInstance().getEffect() == MobEffects.BLINDNESS
                && event.getEffectInstance().getDuration() == Integer.MAX_VALUE
                && event.getEffectInstance().getAmplifier() == 255) {
                event.setCanceled(true);
                LOGGER.debug("Prevented BasicLogin blindness effect on player {}", 
                    ((Player)event.getEntity()).getName().getString());
            }
        }
    }
}