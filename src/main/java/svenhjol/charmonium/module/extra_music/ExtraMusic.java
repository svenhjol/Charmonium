package svenhjol.charmonium.module.extra_music;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.annotation.Module;
import svenhjol.charmonium.helper.DimensionHelper;
import svenhjol.charmonium.helper.RegistryHelper;
import svenhjol.charmonium.module.CharmoniumModule;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@SuppressWarnings("unused")
@Module(description = "Adds custom music tracks that play in certain situations.")
public class ExtraMusic extends CharmoniumModule {
    private static final List<MusicCondition> musicConditions = new ArrayList<>();
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

        // overworld music
        getMusicConditions().add(new MusicCondition(MUSIC_OVERWORLD, 1200, 3600, mc -> {
            if (mc.player == null || mc.player.level == null) return false;

            return mc.player.level.random.nextFloat() < 0.08F
                && DimensionHelper.isOverworld(mc.player.level);
        }));

        // cold music, look for player pos in icy biome
        getMusicConditions().add(new MusicCondition(MUSIC_COLD, 1200, 3600, mc ->
            mc.player != null
                && mc.player.level.getBiome(mc.player.blockPosition()).getBiomeCategory() == Biome.BiomeCategory.ICY
                && mc.player.level.random.nextFloat() < 0.28F
        ));

        // nether music, look for player at low position in the nether
        getMusicConditions().add(new MusicCondition(MUSIC_NETHER, 1200, 3600, mc -> {
            if (mc.player == null)
                return false;

            return mc.player.blockPosition().getY() < 48
                && DimensionHelper.isNether(mc.player.level)
                && mc.player.level.random.nextFloat() < 0.33F;
        }));

        // ruin music, look around player for stone bricks and cobblestone
        getMusicConditions().add(new MusicCondition(MUSIC_RUIN, 1200, 3600, mc -> {
            if (mc.player == null)
                return false;

            Level level = mc.player.level;

            Optional<BlockPos> optMatch1 = BlockPos.findClosestMatch(mc.player.blockPosition(), 16, 16, pos -> {
                Block block = level.getBlockState(pos).getBlock();
                return block == Blocks.STONE_BRICKS
                    || block == Blocks.CRACKED_STONE_BRICKS
                    || block == Blocks.MOSSY_STONE_BRICKS
                    || block == Blocks.DEEPSLATE_BRICKS;
            });

            Optional<BlockPos> optMatch2 = BlockPos.findClosestMatch(mc.player.blockPosition(), 16, 16, pos -> {
                Block block = level.getBlockState(pos).getBlock();
                return block == Blocks.COBBLESTONE
                    || block == Blocks.MOSSY_COBBLESTONE
                    || block == Blocks.COBBLED_DEEPSLATE
                    || block == Blocks.IRON_DOOR
                    || block == Blocks.IRON_BARS;
            });

            return mc.player.blockPosition().getY() < 64
                && DimensionHelper.isOverworld(level)
                && optMatch1.isPresent()
                && optMatch2.isPresent()
                && level.random.nextFloat() < 0.33F;
        }));
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

        public MusicCondition(net.minecraft.sounds.Music music) {
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
