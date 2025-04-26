package cn.sh1rocu.futurecherry.world.biome;

import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class CherryGroveBaseBiome {
    public static Biome generate() {
        Biome.Builder biomeBuilder = new Biome.Builder()
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.EXTREME_HILLS)
                .depth(0.5F)
                .downfall(0.8F)
                .scale(0.1F)
                .temperature(0.5F)
                .specialEffects(new BiomeAmbience.Builder().
                        fogColor(12638463).
                        foliageColorOverride(11983713).
                        grassColorOverride(11983713).
                        ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_CAVE, 6000, 8, 2.0)).
                        skyColor(8103167).
                        waterColor(6141935).
                        waterFogColor(6141935).
                        build());
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder();
        MobSpawnInfo.Builder spawnsBuilder = new MobSpawnInfo.Builder();
        return biomeBuilder.generationSettings(builder.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS).build()).mobSpawnSettings(spawnsBuilder.build()).build();
    }
}
