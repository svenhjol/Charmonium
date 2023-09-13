package svenhjol.charmonium.sound;

/**
 * Implemented by all ambient sounds.
 */
public interface ISoundType<T extends ISoundInstance> {
    void addSounds(SoundHandler<T> handler);
}
