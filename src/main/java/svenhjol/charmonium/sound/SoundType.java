package svenhjol.charmonium.sound;

/**
 * Implemented by all ambient sounds.
 */
public interface SoundType<T extends SoundInstance> {
    void addSounds(SoundHandler<T> handler);
}
