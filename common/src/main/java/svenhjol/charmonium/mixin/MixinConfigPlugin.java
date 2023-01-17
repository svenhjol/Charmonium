package svenhjol.charmonium.mixin;

import svenhjol.charm_core.base.BaseMixinConfigPlugin;
import svenhjol.charmonium.Charmonium;

public class MixinConfigPlugin extends BaseMixinConfigPlugin {
    @Override
    protected String getModId() {
        return Charmonium.MOD_ID;
    }
}
