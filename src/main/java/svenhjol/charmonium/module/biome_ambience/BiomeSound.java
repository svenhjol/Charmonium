package svenhjol.charmonium.module.biome_ambience;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.iface.IAmbientSound;
import svenhjol.charmonium.sounds.LoopingSound;

import java.util.ConcurrentModificationException;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BiomeSound implements IAmbientSound {
    protected boolean isValid = false;
    protected Player player;
    protected ClientLevel level;
    protected LoopingSound soundInstance = null;
    protected Supplier<SoundEvent> soundCondition;
    protected Predicate<Biome> biomeCondition;

    protected BiomeSound(Player player, Predicate<Biome> biomeCondition, Supplier<SoundEvent> soundCondition) {
        this.player = player;
        this.level = (ClientLevel) player.level;
        this.soundCondition = soundCondition;
        this.biomeCondition = biomeCondition;
    }

    @Override
    public void updatePlayer(Player player) {
        this.player = player;
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

        if (isValid && !nowValid)
            isValid = false;

        if (!isValid && nowValid)
            isValid = true;

        if (isValid && !isPlaying()) {
            soundInstance = new LoopingSound(player, getSound(), getVolume(), getPitch(), p -> isValid);
            try {
                getSoundManager().play(this.soundInstance);
            } catch (ConcurrentModificationException e) {
                Charmonium.LOG.debug("Exception in tick");
            }
        }
    }

    @Override
    public boolean isValid() {
        if (level == null)
            return false;

        if (!player.isAlive())
            return false;

        BlockPos pos = player.blockPosition();
        Biome biome = level.getBiome(pos);
        if (biome == null)
            return false;

        return this.biomeCondition.test(biome);
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        return soundCondition.get();
    }
}
