package svenhjol.charmonium.charmony.feature;

import svenhjol.charmonium.charmony.Feature;
import svenhjol.charmonium.charmony.Resolve;

public interface ChildFeature<P extends Feature> {
    default P parent() {
        return Resolve.feature(typeForParent());
    }

    Class<P> typeForParent();
}
