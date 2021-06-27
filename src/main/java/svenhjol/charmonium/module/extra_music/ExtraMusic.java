package svenhjol.charmonium.module.extra_music;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.annotation.Module;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.module.CharmoniumModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

@SuppressWarnings("unused")
@Module(description = "Adds custom music tracks that play in certain situations.")
public class ExtraMusic extends CharmoniumModule {
    private static SoundInstance currentMusic;
    private static ResourceLocation currentDim = null;
    private static MusicCondition lastCondition;
    private static final List<MusicCondition> musicConditions = new ArrayList<>();
    private static int timeUntilNextMusic = 100;

    public static boolean isEnabled;

    public static SoundEvent MUSIC_OVERWORLD;
    public static SoundEvent MUSIC_COLD;
    public static SoundEvent MUSIC_NETHER;
    public static SoundEvent MUSIC_RUIN;

    @Override
    public void register() {
        MUSIC_OVERWORLD = RegistryHelper.sound("music.overworld");
        MUSIC_COLD = RegistryHelper.sound("music.cold");
        MUSIC_NETHER = RegistryHelper.sound("music.nether");
        MUSIC_RUIN = RegistryHelper.sound("music.ruin");
    }

    @Override
    public void init() {
        // static boolean for mixins to check
        isEnabled = Charmonium.isEnabled("extra_music");

        getMusicConditions().add(new MusicCondition(MUSIC_OVERWORLD, 1200, 3600, mc -> {
            if (mc.player == null || mc.player.level == null)
                return false;

            return mc.player.level.random.nextFloat() < 1F
                && DimensionHelper.isOverworld(mc.player.level);
        }));
    }

    public static boolean handleTick(SoundInstance current) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null) return false;
        if (lastCondition == null)
            lastCondition = getMusicCondition();

        if (currentMusic != null) {
            if (!DimensionHelper.isDimension(mc.level, currentDim)) {
                Charmonium.LOG.debug("[Music Improvements] Stopping music as the dimension is no longer correct");
                forceStop();
                currentMusic = null;
            }

            if (currentMusic != null && !mc.getSoundManager().isActive(currentMusic)) {
                Charmonium.LOG.debug("[Music Improvements] Music has finished, setting currentMusic to null");
                timeUntilNextMusic = Math.min(Mth.nextInt(new Random(), lastCondition.getMinDelay(), 3600), timeUntilNextMusic);
                currentMusic = null;
            }
        }

        timeUntilNextMusic = Math.min(timeUntilNextMusic, lastCondition.getMaxDelay());

        if (currentMusic == null && timeUntilNextMusic-- <= 0) {
            MusicCondition condition = getMusicCondition();
            Charmonium.LOG.debug("[Music Improvements] Selected music condition with sound: " + condition.getSound().getLocation());
            forceStop();

            currentDim = DimensionHelper.getDimension(mc.level);
            currentMusic = SimpleSoundInstance.forMusic(condition.getSound());

            if (currentMusic.getSound() != SoundManager.EMPTY_SOUND) {
                mc.getSoundManager().play(currentMusic);
                lastCondition = condition;
                timeUntilNextMusic = Integer.MAX_VALUE;
            }
        }

        mc.getSoundManager().tick(true);
        return true;
    }

    public static boolean handleStop() {
        if (currentMusic != null) {
            Minecraft.getInstance().getSoundManager().stop(currentMusic);
            currentMusic = null;
            timeUntilNextMusic = lastCondition != null ? new Random().nextInt(Math.min(lastCondition.getMinDelay(), 3600) + 100) + 1000 : timeUntilNextMusic + 100;
            Charmonium.LOG.debug("[Music Improvements] Stop was called, setting timeout to " + timeUntilNextMusic);
        }
        return true;
    }

    public static boolean handlePlaying(net.minecraft.sounds.Music music) {
        return currentMusic != null && music.getEvent().getLocation().equals(currentMusic.getLocation());
    }

    public static void forceStop() {
        Minecraft.getInstance().getSoundManager().stop(currentMusic);
        currentMusic = null;
        timeUntilNextMusic = 3600;
    }

    public static MusicCondition getMusicCondition() {
        MusicCondition condition = null;

        // select an available condition from the pool of conditions
        for (MusicCondition c : musicConditions) {
            if (c.handle()) {
                condition = c;
                break;
            }
        }

        // if none available, default to vanilla music selection
        if (condition == null)
            condition = new MusicCondition(Minecraft.getInstance().getSituationalMusic());

        return condition;
    }

    public static List<MusicCondition> getMusicConditions() {
        return musicConditions;
    }

    public static class MusicCondition {
        private final SoundEvent sound;
        private final int minDelay;
        private final int maxDelay;
        private Predicate<Minecraft> condition;

        public MusicCondition(SoundEvent sound, int minDelay, int maxDelay, Predicate<Minecraft> condition) {
            this.sound = sound;
            this.minDelay = minDelay;
            this.maxDelay = maxDelay;
            this.condition = condition;
        }

        public MusicCondition(net.minecraft.sounds.Music music) {
            this.sound = music.getEvent();
            this.minDelay = music.getMinDelay();
            this.maxDelay = music.getMaxDelay();
        }

        public boolean handle() {
            if (condition == null) return false;
            return condition.test(Minecraft.getInstance());
        }

        public SoundEvent getSound() {
            return sound;
        }

        public int getMaxDelay() {
            return maxDelay;
        }

        public int getMinDelay() {
            return minDelay;
        }
    }
}
