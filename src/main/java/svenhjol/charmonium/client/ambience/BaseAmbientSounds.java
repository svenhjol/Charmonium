package svenhjol.charmonium.client.ambience;

import net.minecraft.block.*;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import svenhjol.charm.Charm;
import svenhjol.charm.base.helper.DimensionHelper;
import svenhjol.charmonium.client.LongSound;
import svenhjol.charmonium.client.ShortSound;
import svenhjol.charmonium.iface.IAmbientSounds;
import svenhjol.charmonium.module.Sounds;

import java.util.ConcurrentModificationException;

public abstract class BaseAmbientSounds implements IAmbientSounds {
    protected int shortTicks = 0;
    protected boolean isValid = false;
    protected MovingSoundInstance longSound = null;

    protected final PlayerEntity player;
    protected final ClientWorld world;
    protected final SoundManager soundHandler;

    public BaseAmbientSounds(PlayerEntity player, SoundManager soundHandler) {
        this.player = player;
        this.soundHandler = soundHandler;
        this.world = (ClientWorld) player.world;
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
        if (!DimensionHelper.isOverworld(player.world)) {
            if (!Sounds.outdoorDimensions.contains(DimensionHelper.getDimension(player.world)))
                return false;
        }

        if (player.isSubmergedInWater()) return false;

        int blocks = 16;
        int start = 1;

        BlockPos playerPos = player.getBlockPos();

        for (int i = start; i < start + blocks; i++) {
            BlockPos check = new BlockPos(playerPos.getX(), playerPos.getY() + i, playerPos.getZ());
            BlockState state = world.getBlockState(check);
            Block block = state.getBlock();

            if (world.isSkyVisibleAllowingSea(check)) return true;

            if (world.isAir(check)) continue;
            if (state.getMaterial() == Material.GLASS
                || (block instanceof PillarBlock && state.getMaterial() == Material.WOOD) // no more LogBlock, wtf?
                || block instanceof MushroomBlock
                || block instanceof StemBlock
            ) continue;

            if (state.isOpaque()) return false;
        }

        return player.getBlockPos().getY() >= 48;
    }

    protected void setShortSound() {
        ShortSound shortSound = new ShortSound((ClientPlayerEntity) player, getShortSound(), getShortSoundVolume() * (float) Sounds.volumeMultiplier);

        try {
            if (!soundHandler.isPlaying(shortSound))
                soundHandler.play(shortSound);
        } catch (ConcurrentModificationException e) {
            Charm.LOG.debug("Exception in setShortSound");
        }
    }

    protected void setLongSound() {
        this.longSound = new LongSound(player, getLongSound(), getLongSoundVolume() * (float) Sounds.volumeMultiplier, p -> isValid());
    }

    public boolean isPlayingLongSound() {
        return this.longSound != null && !this.longSound.isDone();
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
    public ClientWorld getWorld() {
        return world;
    }

    @Override
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public SoundManager getSoundManager() {
        return soundHandler;
    }
}
