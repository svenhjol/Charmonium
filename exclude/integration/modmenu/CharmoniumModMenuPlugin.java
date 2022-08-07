package svenhjol.charmonium.integration.modmenu;

import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.loader.CharmModule;

import java.util.List;

public class CharmoniumModMenuPlugin extends BaseModMenuPlugin<CharmModule> {
    @Override
    public String getModId() {
        return Charmonium.MOD_ID;
    }

    @Override
    public List<CharmModule> getModules() {
        return Charmonium.LOADER.getModules();
    }
}
