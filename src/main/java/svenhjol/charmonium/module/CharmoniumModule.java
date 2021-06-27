package svenhjol.charmonium.module;

public abstract class CharmoniumModule {
    public boolean enabled = true;
    public boolean enabledByDefault = true;
    public boolean alwaysEnabled = false;
    public String description = "";
    public String mod = "";
    public int priority = 0;

    public boolean depends() {
        return true;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public void register() {
        // always runs
    }

    public void init() {
        // only runs if module is enabled
    }
}
