package svenhjol.charmonium.module.underground_ambience;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import svenhjol.charmonium.helper.LogHelper;
import svenhjol.charmonium.iface.IAmbientSound;
import svenhjol.charmonium.sounds.LoopingSound;

import java.util.ConcurrentModificationException;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class UndergroundSound implements IAmbientSound {
    protected boolean isValid = false;
    protected Player player;
    protected ClientLevel level;
    protected LoopingSound soundInstance = null;
    protected Supplier<SoundEvent> soundCondition;
    protected Predicate<UndergroundSound> validCondition;

    protected UndergroundSound(Player player, Predicate<UndergroundSound> validCondition, Supplier<SoundEvent> soundCondition) {
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
                getSoundManager().play(soundInstance);
            } catch (ConcurrentModificationException e) {
                LogHelper.debug(this.getClass(), "Exception in tick");
            }
        }
    }

    @Override
    public boolean isValid() {
        if (level == null)
            return false;

        if (!player.isAlive())
            return false;

        return validCondition.test(this);
    }

    @Nullable
    @Override
    public SoundEvent getSound() {
        return soundCondition.get();
    }
}
