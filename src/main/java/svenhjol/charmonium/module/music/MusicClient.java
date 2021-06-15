package svenhjol.charmonium.module.music;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import svenhjol.charm.handler.ModuleHandler;
import svenhjol.charm.helper.DimensionHelper;
import svenhjol.charm.module.CharmClientModule;
import svenhjol.charm.module.CharmModule;
import svenhjol.charm.module.music_improvements.MusicImprovementsClient;
import svenhjol.charm.module.music_improvements.MusicImprovementsClient.MusicCondition;
import svenhjol.charm.module.player_state.PlayerStateClient;
import svenhjol.charmonium.init.CharmoniumSounds;

public class MusicClient extends CharmClientModule {
    public MusicClient(CharmModule module) {
        super(module);
    }

    @Override
    public void register() {
        if (!ModuleHandler.enabled("charm:music_improvements"))
            return;

        // play Þarna in overworld anywhere
        MusicImprovementsClient.getMusicConditions().add(new MusicCondition(CharmoniumSounds.MUSIC_THARNA, 1200, 3600, mc -> {
            if (mc.player == null || mc.player.level == null)
                return false;

            return mc.player.level.random.nextFloat() < 0.08F
                && DimensionHelper.isDimension(mc.player.level, new ResourceLocation("overworld"));
        }));

        // play Mús in cold environments
        MusicImprovementsClient.getMusicConditions().add(new MusicCondition(CharmoniumSounds.MUSIC_MUS, 1200, 3600, mc ->
            mc.player != null
                && mc.player.level.getBiome(mc.player.blockPosition()).getBiomeCategory() == Biome.BiomeCategory.ICY
                && mc.player.level.random.nextFloat() < 0.28F
        ));

        // play Undir in nether underground
        MusicImprovementsClient.getMusicConditions().add(new MusicCondition(CharmoniumSounds.MUSIC_UNDIR, 1200, 3600, mc -> {
            if (mc.player == null)
                return false;

            return mc.player.blockPosition().getY() < 48
                && DimensionHelper.isDimension(mc.player.level, new ResourceLocation("the_nether"))
                && mc.player.level.random.nextFloat() < 0.33F;
        }));

        // play Steinn and Draugur in ruins
        MusicImprovementsClient.getMusicConditions().add(new MusicCondition(CharmoniumSounds.MUSIC_RUIN, 2400, 3600, mc
            -> mc.player != null && mc.player.level.random.nextFloat() < 0.8F && PlayerStateClient.INSTANCE.ruin));
    }
}
