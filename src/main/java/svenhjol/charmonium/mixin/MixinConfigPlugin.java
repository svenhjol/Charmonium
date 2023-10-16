package svenhjol.charmonium.mixin;

import svenhjol.charmonium.CharmoniumClient;
import svenhjol.charmony.base.CharmonyMixinConfigPlugin;

public class MixinConfigPlugin extends CharmonyMixinConfigPlugin {
    @Override
    protected String getModId() {
        return CharmoniumClient.MOD_ID;
    }
}
