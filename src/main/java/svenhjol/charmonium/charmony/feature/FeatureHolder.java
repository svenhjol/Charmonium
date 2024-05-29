package svenhjol.charmonium.charmony.feature;

import svenhjol.charmonium.charmony.Feature;
import svenhjol.charmonium.charmony.Log;

public abstract class FeatureHolder<F extends Feature> {
    private final F feature;

    public FeatureHolder(F feature) {
        this.feature = feature;
    }

    public F feature() {
        return this.feature;
    }

    public Log log() {
        return this.feature.log();
    }
}
