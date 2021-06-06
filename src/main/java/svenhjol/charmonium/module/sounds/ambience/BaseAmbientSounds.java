package svenhjol.charmonium.module.sounds.ambience;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import svenhjol.charm.Charm;
import svenhjol.charm.helper.DimensionHelper;
import svenhjol.charmonium.module.sounds.LongSound;
import svenhjol.charmonium.module.sounds.ShortSound;
import svenhjol.charmonium.module.sounds.IAmbientSounds;
import svenhjol.charmonium.module.sounds.Sounds;

import java.util.ConcurrentModificationException;

public abstract class BaseAmbientSounds implements IAmbientSounds {
    protected int shortTicks = 0;
    protected boolean isValid = false;
    protected AbstractTickableSoundInstance longSound = null;

    protected final Player player;
    protected final ClientLevel world;
    protected final SoundManager soundHandler;

    public BaseAmbientSounds(Player player, SoundManager soundHandler) {
        this.player = player;
        this.soundHandler = soundHandler;
        this.world = (ClientLevel) player.level;
    }

    public void tick() {
        boolean nowValid = isValid();

        if (isValid && !nowValid)
            isValid = false;

        if (!isValid && nowValid) {
            isValid = true;
            shortTicks = getShortSoundDelay();
        }

        if (nowValid && hasShortSound() && --shortTicks <= 0) {
            setShortSound();
            shortTicks = getShortSoundDelay();
        }

        if (isValid && hasLongSound() && !isPlayingLongSound()) {
            setLongSound();
            try {
                soundHandler.play(this.longSound);
            } catch (ConcurrentModificationException e) {
                Charm.LOG.debug("Exception in tick");
            }
        }
    }

    public boolean isOutside() {
        if (!DimensionHelper.isOverworld(player.level)) {
            if (!Sounds.outdoorDimensions.contains(DimensionHelper.getDimension(player.level)))
                return false;
        }

        if (player.isUnderWater()) return false;

        int blocks = 16;
        int start = 1;

        BlockPos playerPos = player.blockPosition();

        for (int i = start; i < start + blocks; i++) {
            BlockPos check = new BlockPos(playerPos.getX(), playerPos.getY() + i, playerPos.getZ());
            BlockState state = world.getBlockState(check);
            Block block = state.getBlock();

            if (world.canSeeSkyFromBelowWater(check)) return true;

            if (world.isEmptyBlock(check)) continue;
            if (state.getMaterial() == Material.GLASS
                || (block instanceof RotatedPillarBlock && state.getMaterial() == Material.WOOD) // no more LogBlock, wtf?
                || block instanceof HugeMushroomBlock
                || block instanceof StemBlock
            ) continue;

            if (state.canOcclude()) return false;
        }

        return player.blockPosition().getY() >= 48;
    }

    protected void setShortSound() {
        ShortSound shortSound = new ShortSound((LocalPlayer) player, getShortSound(), getShortSoundVolume() * (float) Sounds.volumeMultiplier);

        try {
            if (!soundHandler.isActive(shortSound))
                soundHandler.play(shortSound);
        } catch (ConcurrentModificationException e) {
            Charm.LOG.debug("Exception in setShortSound");
        }
    }

    protected void setLongSound() {
        this.longSound = new LongSound(player, getLongSound(), getLongSoundVolume() * (float) Sounds.volumeMultiplier, p -> isValid());
    }

    public boolean isPlayingLongSound() {
        return this.longSound != null && !this.longSound.isStopped();
    }

    public float getShortSoundVolume() {
        return 0.4F;
    }

    public float getLongSoundVolume() {
        return 0.3F;
    }

    public int getShortSoundDelay() {
        return world.random.nextInt(400) + 400;
    }

    public boolean hasLongSound() {
        return getLongSound() != null;
    }

    public boolean hasShortSound() {
        return getShortSound() != null;
    }

    @Override
    public ClientLevel getWorld() {
        return world;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public SoundManager getSoundManager() {
        return soundHandler;
    }
}
