package svenhjol.charmonium.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.player.PlayerEntity;
import svenhjol.charmonium.client.ambience.*;
import svenhjol.charmonium.module.Sounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmbientSoundClient {
    public Handler handler;

    public AmbientSoundClient() { }

    public void playerJoined(PlayerEntity player) {
        // we only care about ClientPlayerEntity (the actual player) not RemoteClientPlayerEntity (other players relative to the actual player)
        if (player instanceof ClientPlayerEntity) {
            MinecraftClient mc = MinecraftClient.getInstance();
            Sounds.client.handler = new Handler(player, mc.getSoundManager());
        }
    }

    public static class Handler {
        private final PlayerEntity player;
        private final List<BaseAmbientSounds> ambientSounds = new ArrayList<>();

        public Handler(PlayerEntity player, SoundManager soundHandler) {
            this.player = player;

            ambientSounds.addAll(Arrays.asList(
                new DeepAmbientSounds(player, soundHandler),
                new MineshaftAmbientSounds(player, soundHandler),
                new VillageAmbientSounds(player, soundHandler),

                new CaveAmbientSounds(player, soundHandler),
                new EndAmbientSounds(player, soundHandler),
                new HighAmbientSounds(player, soundHandler),

                new BeachAmbientSounds(player, soundHandler),
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
                new OceanAmbientSounds(player, soundHandler),
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

        public void tick() {
            if (!player.isAlive() || player.world == null) return;
            ambientSounds.forEach(BaseAmbientSounds::tick);
        }
    }
}
