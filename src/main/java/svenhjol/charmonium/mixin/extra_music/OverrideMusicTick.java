package svenhjol.charmonium.mixin.extra_music;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charmonium.module.extra_music.ExtraMusic;

@Mixin(MusicManager.class)
public class OverrideMusicTick {
    @Shadow private SoundInstance currentMusic;

    @Inject(
        method = "tick",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookTick(CallbackInfo ci) {
        if (ExtraMusic.isEnabled && ExtraMusic.handleTick(this.currentMusic))
            ci.cancel();
    }

    @Inject(
        method = "stopPlaying",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookStop(CallbackInfo ci) {
        if (ExtraMusic.isEnabled && ExtraMusic.handleStop())
            ci.cancel();
    }

    @Inject(
        method = "isPlayingMusic",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookIsPlayingType(net.minecraft.sounds.Music music, CallbackInfoReturnable<Boolean> cir) {
        if (ExtraMusic.isEnabled && ExtraMusic.handlePlaying(music))
            cir.setReturnValue(true);
    }
}
