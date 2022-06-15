package svenhjol.charmonium.module.biome_ambience;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charmonium.helper.LogHelper;
import svenhjol.charmonium.iface.IAmbientSound;
import svenhjol.charmonium.sounds.LoopingSound;

import java.util.ConcurrentModificationException;
import java.util.List;

public abstract class BiomeSound implements IAmbientSound {
    protected Minecraft client;
    protected boolean isValid = false;
    protected Player player;
    protected ClientLevel level;
    protected LoopingSound soundInstance = null;
    protected float blendScaling = 1.0F;
    protected float volumeScaleFade = 0.005F;

    protected BiomeSound(Player player) {
        this.client = Minecraft.getInstance();
        this.player = player;
        this.level = (ClientLevel) player.level;
    }

    public abstract boolean isValidBiomeCondition(ResourceKey<Biome> biomeKey, Biome biome);

    @Override
    public void updatePlayer(Player player) {
        this.player = player;
        this.level = (ClientLevel) player.level;
    }

    @Override
    public ClientLevel getLevel() {
        return level;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public AbstractTickableSoundInstance getSoundInstance() {
        return soundInstance;
    }

    @Override
    public void tick() {
        boolean nowValid = isValid();

        if (isValid && !nowValid) isValid = false;
        if (!isValid && nowValid) isValid = true;

        if (isValid) {
            var volume = getVolume() * getVolumeScaling() * blendScaling;

            if (!isPlaying()) {
                soundInstance = new LoopingSound(player, getSound(), volume, getPitch(), p -> isValid);
                try {
                    getSoundManager().play(this.soundInstance);
                } catch (ConcurrentModificationException e) {
                    LogHelper.debug(this.getClass(), "Exception in tick");
                }
            } else {

                // Adjust sound volume with a fade.
                if (soundInstance.maxVolume != volume) {
                    if (soundInstance.maxVolume < volume) {
                        soundInstance.maxVolume += volumeScaleFade;
                    } else {
                        soundInstance.maxVolume -= volumeScaleFade;
                    }
                }
            }
        }
    }

    @Override
    public boolean isValid() {
        if (client.level == null || level == null) return false;
        if (!player.isAlive()) return false;
        if (!isValidPlayerCondition()) return false;

        if (!BiomeAmbience.VALID_DIMENSIONS.contains(level.dimension().location())) {
            return false;
        }

        var pos = player.blockPosition();
        var blend = (float)BiomeAmbience.biomeBlend;

        if (blend > 0) {

            // Sample points.
            var directions = List.of(
                Direction.EAST,
                Direction.WEST,
                Direction.NORTH,
                Direction.SOUTH
            );

            for (var direction : directions) {
                for (int i = 0; i < blend; i += 2) {
                    var relativePos = pos.relative(direction, i);

                    // Get the biome and key for condition check.
                    var biome = getBiome(relativePos);
                    var biomeKey = getBiomeKey(relativePos);

                    if (isValidBiomeCondition(biomeKey, biome)) {
                        this.blendScaling = 1.0F - ((float) i / blend);
                        return true;
                    }
                }
            }
        } else {

            // Get the biome and key for condition check.
            var biome = getBiome(pos);
            var biomeKey = getBiomeKey(pos);

            if (isValidBiomeCondition(biomeKey, biome)) {
                this.blendScaling = 1.0F;
                return true;
            }
        }

        return false;
    }
}
