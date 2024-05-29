package svenhjol.charmonium.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.charmony.Resolve;
import svenhjol.charmonium.feature.ambience_settings.AmbienceSettings;

public class SingleSound extends AbstractTickableSoundInstance {
    private final Player player;

    public SingleSound(Player player, SoundEvent sound, float volume) {
        this(player, sound, volume, 1.0F, null);
    }

    public SingleSound(Player player, SoundEvent sound, float volume, float pitch, @Nullable BlockPos pos) {
        super(sound, Resolve.feature(AmbienceSettings.class).source(), RandomSource.create());

        this.player = player;
        this.looping = false;
        this.delay = 0;
        this.pitch = pitch;
        this.volume = volume;

        if (pos != null) {
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
        } else {
            this.relative = true;
        }
    }

    @Override
    public void tick() {
        if (player == null || !player.isAlive()) {
            this.stopped = true;
        }
    }
}
