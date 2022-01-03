package svenhjol.charmonium.module.extra_music;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.annotation.ClientModule;
import svenhjol.charmonium.annotation.Config;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.loader.CharmModule;
import svenhjol.charmonium.registry.ClientRegistry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

@SuppressWarnings("unused")
@ClientModule(mod = Charmonium.MOD_ID, description = "Plays creative music tracks in survival mode and enables other mods to add custom music.")
public class ExtraMusic extends CharmModule {
    private static SoundEvent MUSIC_CREATIVE;
    private static final List<MusicCondition> CONDITIONS = new ArrayList<>();

    @Config(name = "Creative music chance", description = "Chance (out of 1.0) of a Creative music track playing in survival mode whenever a new track is ready to play.")
    public static float creativeMusicChance = 0.12F;

    @Override
    public void register() {
        // Charmonium registers all six music tracks under a custom sound registry event
        // so that we can play the creative music in isolation from the other game music.
        MUSIC_CREATIVE = ClientRegistry.sound("music.creative");
    }

    @Override
    public void runWhenEnabled() {
        // Play creative music tracks in survival when the current music is scheduled to be Musics.GAME.
        getConditions().add(new MusicCondition(MUSIC_CREATIVE, 1200, 3600, true, currentMusic -> {
            var mc = Minecraft.getInstance();
            if (currentMusic != Musics.GAME || mc == null || mc.player == null) return false;
            if (new Random().nextFloat() < creativeMusicChance) return false;

            return (!mc.player.isCreative() || !mc.player.isSpectator())
                && DimensionHelper.isOverworld(mc.player.level);
        }));
    }

    @Nullable
    public static Music replaceMusic(Music currentMusic) {
        for (MusicCondition c : CONDITIONS) {
            if (c.handle(currentMusic)) {
                return c.getMusic();
            }
        }

        return null;
    }

    public static List<MusicCondition> getConditions() {
        return CONDITIONS;
    }

    public static void addCondition(MusicCondition condition) {
        CONDITIONS.add(condition);
    }

    public static class MusicCondition {
        private final SoundEvent sound;
        private final int minDelay;
        private final int maxDelay;
        private final boolean replace;
        private Predicate<Music> condition;

        public MusicCondition(SoundEvent sound, int minDelay, int maxDelay, boolean replace, Predicate<Music> condition) {
            this.sound = sound;
            this.minDelay = minDelay;
            this.maxDelay = maxDelay;
            this.condition = condition;
            this.replace = replace;
        }

        public MusicCondition(Music music) {
            this.sound = music.getEvent();
            this.minDelay = music.getMinDelay();
            this.maxDelay = music.getMaxDelay();
            this.replace = false;
        }

        public boolean handle(Music currentMusic) {
            if (condition == null) return false;
            return condition.test(currentMusic);
        }

        public Music getMusic() {
            return new Music(sound, minDelay, maxDelay, replace);
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
