package svenhjol.charmonium.client;

import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import svenhjol.charm.mixin.accessor.MovingSoundInstanceAccessor;

import java.util.function.Predicate;

public class LongSound extends MovingSoundInstance {
    private final PlayerEntity player;
    private int longTicks;
    private final Predicate<PlayerEntity> predicate;
    private final float maxVolume;

    public LongSound(PlayerEntity player, SoundEvent sound, float volume, Predicate<PlayerEntity> predicate) {
        super(sound, SoundCategory.AMBIENT);
        this.maxVolume = volume;
        this.player = player;
        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 0.01F;
        this.looping = true;
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

            boolean donePlaying = ((MovingSoundInstanceAccessor) this).getDone();

            if (!donePlaying && this.volume == 0.0F && this.longTicks < -100)
                ((MovingSoundInstanceAccessor) this).setDone(true);

        } else {
            ((MovingSoundInstanceAccessor) this).setDone(true);
        }
    }
}
