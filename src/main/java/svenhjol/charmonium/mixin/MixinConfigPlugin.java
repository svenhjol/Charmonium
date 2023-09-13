package svenhjol.charmonium.mixin;

import svenhjol.charmonium.Charmonium;
import svenhjol.charmony.base.CharmMixinConfigPlugin;

public class MixinConfigPlugin extends CharmMixinConfigPlugin {
    @Override
    protected String getModId() {
        return Charmonium.MOD_ID;
    }
}
