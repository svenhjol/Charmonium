 package svenhjol.charmonium.feature.world_ambience.sounds;

 import net.minecraft.core.BlockPos;
 import net.minecraft.sounds.SoundEvent;
 import net.minecraft.world.level.block.AmethystBlock;
 import net.minecraft.world.level.block.Block;
 import net.minecraft.world.level.block.Blocks;
 import org.jetbrains.annotations.Nullable;
 import svenhjol.charm_core.helper.WorldHelper;
 import svenhjol.charmonium.Charmonium;
 import svenhjol.charmonium.feature.world_ambience.WorldAmbience;
 import svenhjol.charmonium.iface.ISoundType;
 import svenhjol.charmonium.init.SoundHandler;
 import svenhjol.charmonium.sounds.RepeatedWorldSound;
 import svenhjol.charmonium.sounds.WorldSound;

 import java.util.Optional;

 public class Geode implements ISoundType<WorldSound> {
     public static SoundEvent SOUND;

     public Geode() {
         SOUND = SoundEvent.createVariableRangeEvent(Charmonium.makeId("world.geode"));
     }

     public void addSounds(SoundHandler<WorldSound> handler) {
         if (!WorldAmbience.geode) return;

         handler.getSounds().add(new RepeatedWorldSound(handler.getPlayer()) {
             @Override
             public boolean isValidSituationCondition() {
                 Optional<BlockPos> optAmethyst = BlockPos.findClosestMatch(player.blockPosition(), 12, 8, pos -> {
                     Block block = level.getBlockState(pos).getBlock();
                     return block instanceof AmethystBlock;
                 });

                 Optional<BlockPos> optSmoothBasalt = BlockPos.findClosestMatch(player.blockPosition(), 12, 8, pos -> {
                     Block block = level.getBlockState(pos).getBlock();
                     return block == Blocks.SMOOTH_BASALT;
                 });

                 if (optAmethyst.isPresent() && optSmoothBasalt.isPresent()) {
                     setPos(optAmethyst.get());
                     return true;
                 }

                 return false;
             }

             @Override
             public boolean isValidPlayerCondition() {
                 return !WorldHelper.isOutside(player)
                     && WorldHelper.isBelowSeaLevel(player);
             }

             @Nullable
             @Override
             public SoundEvent getSound() {
                 return SOUND;
             }

             @Override
             public int getDelay() {
                 return level.random.nextInt(350) + 300;
             }

             @Override
             public float getVolume() {
                 return 0.5F;
             }

             @Override
             public float getPitch() {
                 return 0.8F + (0.4F * level.random.nextFloat());
             }
         });
     }
 }
