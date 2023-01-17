package svenhjol.charmonium.iface;

import svenhjol.charmonium.init.SoundHandler;

/**
 * Implemented by all ambient sounds.
 */
public interface ISoundType<T extends ISoundInstance> {
    void addSounds(SoundHandler<T> handler);
}
