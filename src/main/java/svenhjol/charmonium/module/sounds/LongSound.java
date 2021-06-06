package svenhjol.charmonium.module.sounds;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import svenhjol.charm.mixin.accessor.AbstractTickableSoundInstanceAccessor;

import java.util.function.Predicate;

public class LongSound extends AbstractTickableSoundInstance {
    private final Player player;
    private int longTicks;
    private final Predicate<Player> predicate;
    private final float maxVolume;

    public LongSound(Player player, SoundEvent sound, float volume, Predicate<Player> predicate) {
        super(sound, SoundSource.AMBIENT);
        this.maxVolume = volume;
        this.player = player;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.01F;
        this.relative = true;
        this.predicate = predicate;
        this.longTicks = -50;
    }

    @Override
    public void tick() {
        if (this.player.isAlive()) {

            if (predicate.test(this.player)) {
                ++this.longTicks;
            } else {
                this.longTicks -= 1;
            }

            this.longTicks = Math.min(this.longTicks, 140);
            this.volume = Math.max(0.0F, Math.min((float) this.longTicks / 140, 1.0F)) * maxVolume;

            boolean donePlaying = ((AbstractTickableSoundInstanceAccessor) this).getStopped();

            if (!donePlaying && this.volume == 0.0F && this.longTicks < -100)
                ((AbstractTickableSoundInstanceAccessor) this).setStopped(true);

        } else {
            ((AbstractTickableSoundInstanceAccessor) this).setStopped(true);
        }
    }
}
