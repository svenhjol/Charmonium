package svenhjol.charmonium.mixin.extra_music;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charmonium.module.extra_music.ExtraMusic;

@Mixin(Minecraft.class)
public class TryCustomMusicMixin {
    @Inject(
        method = "getSituationalMusic",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;getBiome(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/biome/Biome;"
        ),
        cancellable = true
    )
    private void hookSituationalMusic(CallbackInfoReturnable<Music> cir) {
        if (ExtraMusic.isEnabled) {
            Music music = ExtraMusic.getMusic();
            if (music != null)
                cir.setReturnValue(music);
        }
    }
}
