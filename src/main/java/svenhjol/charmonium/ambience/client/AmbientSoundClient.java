package svenhjol.charmonium.ambience.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import svenhjol.charmonium.ambience.client.ambience.*;
import svenhjol.charmonium.ambience.module.Sounds;
import svenhjol.meson.helper.ForgeHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static svenhjol.charmonium.ambience.module.Sounds.ambientSoundClient;

public class AmbientSoundClient {
    public Handler handler;

    public AmbientSoundClient() {
    }

    public void playerJoined(PlayerEntity player) {
        // we only care about ClientPlayerEntity (the actual player)
        // not RemoteClientPlayerEntity (other players relative to the actual player)
        if (player instanceof ClientPlayerEntity) {
            Minecraft mc = Minecraft.getInstance();
            ambientSoundClient.handler = new AmbientSoundClient.Handler(player, mc.getSoundHandler());
        }
    }

    public static class Handler {
        private final PlayerEntity player;
        private final List<BaseAmbientSounds> ambientSounds = new ArrayList<>();

        public Handler(PlayerEntity player, SoundHandler soundHandler) {
            final boolean hasEnvirons = ForgeHelper.isModLoaded("environs");
            this.player = player;

            if (!hasEnvirons || Sounds.featureWithEnvirons) {
                ambientSounds.addAll(Arrays.asList(
                    new DeepAmbientSounds(player, soundHandler),
                    new MineshaftAmbientSounds(player, soundHandler),
                    new CaveAmbientSounds.CrystalCaves(player, soundHandler),
                    new VillageAmbientSounds(player, soundHandler)
                ));
            }

            if (!hasEnvirons || Sounds.biomesWithEnvirons) {
                ambientSounds.addAll(Arrays.asList(
                    new BeachAmbientSounds(player, soundHandler),
                    new CaveAmbientSounds(player, soundHandler),
                    new EndAmbientSounds(player, soundHandler),
                    new HighAmbientSounds(player, soundHandler),
                    new OceanAmbientSounds(player, soundHandler),
                    new NetherAmbientSounds(player, soundHandler),

                    new DesertAmbientSounds.Day(player, soundHandler),
                    new DesertAmbientSounds.Night(player, soundHandler),
                    new ExtremeHillsAmbientSounds.Day(player, soundHandler),
                    new ExtremeHillsAmbientSounds.Night(player, soundHandler),
                    new ForestAmbientSounds.Day(player, soundHandler),
                    new ForestAmbientSounds.Night(player, soundHandler),
                    new IcyAmbientSounds.Day(player, soundHandler),
                    new IcyAmbientSounds.Night(player, soundHandler),
                    new JungleAmbientSounds.Day(player, soundHandler),
                    new JungleAmbientSounds.Night(player, soundHandler),
                    new PlainsAmbientSounds.Day(player, soundHandler),
                    new PlainsAmbientSounds.Night(player, soundHandler),
                    new SavannaAmbientSounds.Day(player, soundHandler),
                    new SavannaAmbientSounds.Night(player, soundHandler),
                    new SwampAmbientSounds.Day(player, soundHandler),
                    new SwampAmbientSounds.Night(player, soundHandler),
                    new TaigaAmbientSounds.Day(player, soundHandler),
                    new TaigaAmbientSounds.Night(player, soundHandler)
                ));
            }
        }

        public void tick() {
            if (!player.isAlive() || player.world == null) return;
            ambientSounds.forEach(BaseAmbientSounds::tick);
        }
    }
}
