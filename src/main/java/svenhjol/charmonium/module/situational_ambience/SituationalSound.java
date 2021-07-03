package svenhjol.charmonium.module.situational_ambience;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.helper.LogHelper;
import svenhjol.charmonium.iface.IAmbientSound;
import svenhjol.charmonium.sounds.SingleSound;

import java.util.ConcurrentModificationException;
import java.util.function.Function;
import java.util.function.Predicate;

public class SituationalSound implements IAmbientSound {
    protected Player player;
    protected ClientLevel level;
    protected Function<SituationalSound, SoundEvent> soundCondition;
    protected Predicate<SituationalSound> validCondition;
    protected BlockPos pos;
    protected SingleSound soundInstance;

    protected int soundTicks = 100; // set something high here so it doesn't autoplay when player logs in
    protected boolean isValid;
    protected boolean playUnderWater = false;

    public SituationalSound(Player player, Predicate<SituationalSound> validCondition, Function<SituationalSound, SoundEvent> soundCondition) {
        this.player = player;
        this.level = (ClientLevel) player.level;
        this.soundCondition = soundCondition;
        this.validCondition = validCondition;
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
    public void updatePlayer(Player player) {
        this.player = player;
        this.level = (ClientLevel) player.level;
    }

    @Override
    public SingleSound getSoundInstance() {
        return soundInstance;
    }

    @Override
    public boolean isValid() {
        // initial filters
        if (level == null) return false;
        if (!player.isAlive()) return false;
        if (player.isUnderWater() && !playUnderWater) return false;

        return validCondition.test(this);
    }

    @Override
    public void tick() {
        if (--soundTicks >= 0)
            return;

        soundTicks = getDelay();
        isValid = isValid();

        if (isValid) {
            soundInstance = new SingleSound(getPlayer(), getSound(), getVolume() * getVolumeScaling(), getPitch(), getPos());
            SoundManager manager = getSoundManager();

            try {
                if (!manager.isActive(soundInstance))
                    manager.play(soundInstance);
            } catch (ConcurrentModificationException e) {
                LogHelper.debug(this.getClass(), "Exception in manager.play");
            }
        }
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        return soundCondition.apply(this);
    }

    @Override
    public int getDelay() {
        return level.random.nextInt(200) + 200;
    }

    @Override
    public float getVolumeScaling() {
        return SituationalAmbience.volumeScaling;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    @Nullable
    public BlockPos getPos() {
        return pos;
    }
}
