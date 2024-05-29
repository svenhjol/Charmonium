package svenhjol.charmonium.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public interface SoundInstance {
    boolean isValid();

    boolean isValidPlayerCondition();

    void tick();

    void updatePlayer(Player player);

    Player getPlayer();

    ClientLevel getLevel();

    @Nullable
    SoundEvent getSound();

    AbstractTickableSoundInstance getSoundInstance();

    default Biome getBiome(BlockPos pos) {
        return getBiomeHolder(pos).value();
    }

    default ResourceKey<Biome> getBiomeKey(BlockPos pos) {
        var biome = getBiome(pos);
        return getLevel().registryAccess()
            .registryOrThrow(Registries.BIOME)
            .getResourceKey(biome)
            .orElse(null);
    }

    default Holder<Biome> getBiomeHolder(BlockPos pos) {
        return getPlayer().level().getBiome(pos);
    }

    default Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }

    default SoundManager getSoundManager() {
        return getMinecraft().getSoundManager();
    }

    default boolean isPlaying() {
        return getSoundInstance() != null
            && getSoundManager().isActive(getSoundInstance());
    }

    default void stop() {
        getSoundManager().stop(getSoundInstance());
    }

    default int getDelay() {
        return 0;
    }

    default float getVolume() {
        return 1.0f;
    }

    default float getPitch() {
        return 1.0f;
    }

    default double getVolumeScaling() {
        return 1.0d;
    }
}
