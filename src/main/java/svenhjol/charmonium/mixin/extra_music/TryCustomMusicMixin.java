package svenhjol.charmonium.mixin.extra_music;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charmonium.Charmonium;
import svenhjol.charmonium.module.extra_music.ExtraMusic;

@Mixin(Minecraft.class)
public class TryCustomMusicMixin {
    @Inject(
        method = "getSituationalMusic",
        at = @At(value = "RETURN"),
        cancellable = true
    )
    private void hookSituationalMusic(CallbackInfoReturnable<Music> cir) {
        if (Charmonium.LOADER.isEnabled(ExtraMusic.class)) {
            Music music = ExtraMusic.replaceMusic(cir.getReturnValue());
            if (music != null) {
                cir.setReturnValue(music);
            }
        }
    }
}
