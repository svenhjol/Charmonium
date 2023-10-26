package svenhjol.charmonium.mixin;

import svenhjol.charmonium.Charmonium;
import svenhjol.charmony.base.CharmonyMixinConfigPlugin;

public class MixinConfigPlugin extends CharmonyMixinConfigPlugin {
    @Override
    protected String modId() {
        return Charmonium.ID;
    }
}
