package svenhjol.charmonium.mixin;

import svenhjol.charmonium.CharmoniumClient;
import svenhjol.charmony.base.CharmMixinConfigPlugin;

public class MixinConfigPlugin extends CharmMixinConfigPlugin {
    @Override
    protected String getModId() {
        return CharmoniumClient.MOD_ID;
    }
}
