package svenhjol.charmonium.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import svenhjol.charmonium.feature.ambience_settings.AmbienceSettings;

import java.util.function.Predicate;

public class LoopingSound extends AbstractTickableSoundInstance {
    public static int FADE_TIME = 140;

    private final Player player;
    private int longTicks;
    private final Predicate<Player> predicate;
    public float maxVolume;

    public LoopingSound(Player player, SoundEvent sound, float volume, float pitch, Predicate<Player> predicate) {
        super(sound, AmbienceSettings.getAudioChannel(), RandomSource.create());

        this.maxVolume = volume;
        this.player = player;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.01F;
        this.pitch = pitch;
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

            this.longTicks = Math.min(this.longTicks, FADE_TIME);
            this.volume = Math.max(0.0F, Math.min((float) this.longTicks / FADE_TIME, 1.0F)) * maxVolume;

            boolean donePlaying = this.stopped;

            if (!donePlaying && this.volume == 0.0F && this.longTicks < -100)
                this.stopped = true;

        } else {
            this.stopped = true;
        }
    }
}
