package svenhjol.charmonium.module.sounds;

import svenhjol.charm.event.AddEntityCallback;
import svenhjol.charm.event.PlayerTickCallback;
import svenhjol.charm.module.CharmClientModule;
import svenhjol.charm.module.CharmModule;
import svenhjol.charmonium.module.sounds.ambience.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class SoundsClient extends CharmClientModule {
    public Handler handler;

    public SoundsClient(CharmModule module) {
        super(module);
    }

    @Override
    public void register() {
        AddEntityCallback.EVENT.register(this::handlePlayerJoined);
        PlayerTickCallback.EVENT.register(this::handlePlayerTick);
    }

    private InteractionResult handlePlayerJoined(Entity entity) {
        if (entity instanceof Player
            && entity.level.isClientSide) {
            trySetupPlayerSoundHandler((Player) entity);
        }
        return InteractionResult.PASS;
    }

    private void handlePlayerTick(Player playerEntity) {
        if (handler != null)
            handler.tick();
    }

    public void trySetupPlayerSoundHandler(Player player) {
        // we only care about ClientPlayerEntity (the actual player) not RemoteClientPlayerEntity (other players relative to the actual player)
        if (player instanceof LocalPlayer) {
            Minecraft mc = Minecraft.getInstance();
            handler = new Handler(player, mc.getSoundManager());
        }
    }

    public static class Handler {
        private final Player player;
        private final List<BaseAmbientSounds> ambientSounds = new ArrayList<>();

        public Handler(Player player, SoundManager soundHandler) {
            this.player = player;

            // vertical
            if (Sounds.caveAmbience) ambientSounds.add(new CaveAmbientSounds(player, soundHandler));
            if (Sounds.deepAmbience) ambientSounds.add(new DeepAmbientSounds(player, soundHandler));
            if (Sounds.highAmbience) ambientSounds.add(new HighAmbientSounds(player, soundHandler));

            // structures/locations
            if (Sounds.mineshaftAmbience) ambientSounds.add(new MineshaftAmbientSounds(player, soundHandler));
            if (Sounds.villageAmbience) ambientSounds.add(new VillageAmbientSounds(player, soundHandler));

            // dimensions
            if (Sounds.endAmbience) ambientSounds.add(new EndAmbientSounds(player, soundHandler));

            // biomes
            if (Sounds.beachAmbience) ambientSounds.add(new BeachAmbientSounds(player, soundHandler));
            if (Sounds.desertAmbience) ambientSounds.addAll(Arrays.asList(
                new DesertAmbientSounds.Day(player, soundHandler),
                new DesertAmbientSounds.Night(player, soundHandler)
            ));
            if (Sounds.badlandsAmbience) ambientSounds.addAll(Arrays.asList(
                new BadlandsAmbientSounds.Day(player, soundHandler),
                new BadlandsAmbientSounds.Night(player, soundHandler)
            ));
            if (Sounds.forestAmbience) ambientSounds.addAll(Arrays.asList(
                new ForestAmbientSounds.Day(player, soundHandler),
                new ForestAmbientSounds.Night(player, soundHandler)
            ));
            if (Sounds.icyAmbience) ambientSounds.addAll(Arrays.asList(
                new IcyAmbientSounds.Day(player, soundHandler),
                new IcyAmbientSounds.Night(player, soundHandler),
                new IcyAmbientSounds.Thunderstorm(player, soundHandler)
            ));
            if (Sounds.jungleAmbience) ambientSounds.addAll(Arrays.asList(
                new JungleAmbientSounds.Day(player, soundHandler),
                new JungleAmbientSounds.Night(player, soundHandler)
            ));
            if (Sounds.mountainsAmbience) ambientSounds.addAll(Arrays.asList(
                new MountainsAmbientSounds.Day(player, soundHandler),
                new MountainsAmbientSounds.Night(player, soundHandler)
            ));
            if (Sounds.oceanAmbience) ambientSounds.add(new OceanAmbientSounds(player, soundHandler));
            if (Sounds.plainsAmbience) ambientSounds.addAll(Arrays.asList(
                new PlainsAmbientSounds.Day(player, soundHandler),
                new PlainsAmbientSounds.Night(player, soundHandler)
            ));
            if (Sounds.savannaAmbience) ambientSounds.addAll(Arrays.asList(
                new SavannaAmbientSounds.Day(player, soundHandler),
                new SavannaAmbientSounds.Night(player, soundHandler)
            ));
            if (Sounds.swampAmbience) ambientSounds.addAll(Arrays.asList(
                new SwampAmbientSounds.Day(player, soundHandler),
                new SwampAmbientSounds.Night(player, soundHandler)
            ));
            if (Sounds.taigaAmbience) ambientSounds.addAll(Arrays.asList(
                new TaigaAmbientSounds.Day(player, soundHandler),
                new TaigaAmbientSounds.Night(player, soundHandler)
            ));
        }

        public void tick() {
            if (!player.isAlive() || player.level == null) return;
            ambientSounds.forEach(BaseAmbientSounds::tick);
        }
    }
}
