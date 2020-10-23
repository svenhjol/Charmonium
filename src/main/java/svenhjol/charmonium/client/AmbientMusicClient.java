package svenhjol.charmonium.client;

import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import svenhjol.charm.base.handler.ModuleHandler;
import svenhjol.charm.client.MusicClient;
import svenhjol.charm.client.MusicCondition;
import svenhjol.charmonium.base.CharmoniumSounds;
import svenhjol.charm.base.helper.DimensionHelper;

public class AmbientMusicClient {
    public AmbientMusicClient() {
        if (!ModuleHandler.enabled("charm:music_improvements"))
            return;

        // play Þarna in overworld anywhere
        MusicClient.getMusicConditions().add(new MusicCondition(CharmoniumSounds.MUSIC_THARNA, 1200, 3600, mc -> {
            if (mc.player == null || mc.player.world == null)
                return false;

            return mc.player.world.random.nextFloat() < 0.08F
                && DimensionHelper.isDimension(mc.player.world, new Identifier("overworld"));
        }));

        // play Mús in cold environments
        MusicClient.getMusicConditions().add(new MusicCondition(CharmoniumSounds.MUSIC_MUS, 1200, 3600, mc ->
            mc.player != null
                && mc.player.world.getBiome(mc.player.getBlockPos()).getCategory() == Biome.Category.ICY
                && mc.player.world.random.nextFloat() < 0.28F
        ));

        // play Undir in nether underground
        MusicClient.getMusicConditions().add(new MusicCondition(CharmoniumSounds.MUSIC_UNDIR, 1200, 3600, mc -> {
            if (mc.player == null)
                return false;

            return mc.player.getBlockPos().getY() < 48
                && DimensionHelper.isDimension(mc.player.world, new Identifier("the_nether"))
                && mc.player.world.random.nextFloat() < 0.33F;
        }));
    }
}
