package svenhjol.charmonium.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import svenhjol.charmonium.Charmonium;

@Mod(Charmonium.MOD_ID)
public class Initializer {
    public Initializer() {
        // There is no common code so just execute the client.
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientInitializer::new);
    }
}
