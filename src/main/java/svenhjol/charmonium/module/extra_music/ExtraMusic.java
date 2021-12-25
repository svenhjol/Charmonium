package svenhjol.charmonium.module.extra_music;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.annotation.ClientModule;
import svenhjol.charmonium.annotation.Config;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.loader.CharmModule;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

@SuppressWarnings("unused")
@ClientModule(mod = Charmonium.MOD_ID, description = "Plays custom music tracks according to the location of the player.")
public class ExtraMusic extends CharmModule {
    private static final List<MusicCondition> musicConditions = new ArrayList<>();

    @Config(name = "Play Creative music", description = "If true, the six Creative music tracks may play in survival mode.")
    public static boolean playCreativeMusic = true;

    @Override
    public void runWhenEnabled() {
        // creative tracks in survival mode
        if (playCreativeMusic) {
            getMusicConditions().add(new MusicCondition(SoundEvents.MUSIC_CREATIVE, 1200, 3600, mc ->
                mc.player != null
                    && (!mc.player.isCreative() || !mc.player.isSpectator())
                    && DimensionHelper.isOverworld(mc.player.level)
                    && new Random().nextFloat() < 0.25F
            ));
        }
    }

    @Nullable
    public static Music getMusic() {
        for (MusicCondition c : musicConditions) {
            if (c.handle())
                return c.getMusic();
        }

        return null;
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

        public MusicCondition(Music music) {
            this.sound = music.getEvent();
            this.minDelay = music.getMinDelay();
            this.maxDelay = music.getMaxDelay();
        }

        public boolean handle() {
            if (condition == null) return false;
            return condition.test(Minecraft.getInstance());
        }

        public Music getMusic() {
            return new Music(sound, minDelay, maxDelay, false);
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
