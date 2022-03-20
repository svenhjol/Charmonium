package svenhjol.charmonium.module.biome_ambience;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.helper.BiomeHelper;
import svenhjol.charmonium.helper.LogHelper;
import svenhjol.charmonium.iface.IAmbientSound;
import svenhjol.charmonium.sounds.LoopingSound;

import java.util.ConcurrentModificationException;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BiomeSound implements IAmbientSound {
    protected Minecraft client;
    protected boolean isValid = false;
    protected Player player;
    protected ClientLevel level;
    protected LoopingSound soundInstance = null;
    protected Supplier<SoundEvent> soundCondition;
    protected Predicate<ResourceKey<Biome>> biomeCondition;

    protected BiomeSound(Player player, Predicate<ResourceKey<Biome>> biomeCondition, Supplier<SoundEvent> soundCondition) {
        this.client = Minecraft.getInstance();
        this.player = player;
        this.level = (ClientLevel) player.level;
        this.soundCondition = soundCondition;
        this.biomeCondition = biomeCondition;
    }

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

        if (isValid && !nowValid) {
            isValid = false;
        }

        if (!isValid && nowValid) {
            isValid = true;
        }

        if (isValid && !isPlaying()) {
            soundInstance = new LoopingSound(player, getSound(), getVolume() * getVolumeScaling(), getPitch(), p -> isValid);
            try {
                getSoundManager().play(this.soundInstance);
            } catch (ConcurrentModificationException e) {
                LogHelper.debug(this.getClass(), "Exception in tick");
            }
        }
    }

    @Override
    public boolean isValid() {
        if (client.level == null || level == null) {
            return false;
        }

        if (!player.isAlive()) {
            return false;
        }

        if (!BiomeAmbience.VALID_DIMENSIONS.contains(level.dimension().location())) {
            return false;
        }

        BlockPos pos = player.blockPosition();
        ResourceKey<Biome> biomeKey = BiomeHelper.getBiomeKey(level, pos);
        if (biomeKey == null) {
            return false;
        }

        return this.biomeCondition.test(biomeKey);
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        return soundCondition.get();
    }

    @Override
    public float getVolumeScaling() {
        return BiomeAmbience.volumeScaling;
    }
}
