package svenhjol.charmonium.iface;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charmonium.helper.BiomeHelper;
import svenhjol.charmonium.module.biome_ambience.BiomeAmbience;

import javax.annotation.Nullable;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public interface IAmbientSound {
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
        return BiomeHelper.getBiome(getLevel(), pos);
    }

    default ResourceKey<Biome> getBiomeKey(BlockPos pos) {
        return BiomeHelper.getBiomeKey(getLevel(), pos);
    }

    default Holder<Biome> getBiomeHolder(BlockPos pos) {
        return BiomeHelper.getBiomeHolder(getLevel(), pos);
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
        return 1.0F;
    }

    default float getPitch() {
        return 1.0F;
    }

    default float getVolumeScaling() {
        return BiomeAmbience.volumeScaling;
    }
}
