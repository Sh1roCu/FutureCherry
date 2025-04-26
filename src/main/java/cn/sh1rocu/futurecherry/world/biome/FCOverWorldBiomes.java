package cn.sh1rocu.futurecherry.world.biome;

import cn.sh1rocu.futurecherry.FutureCherry;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class FCOverWorldBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, FutureCherry.MODID);

    public static final RegistryKey<Biome> CHERRY_GROVE = register("cherry_grove", CherryGroveBaseBiome::generate);

    private static <T extends Biome> RegistryKey<Biome> register(String name, Supplier<T> biome) {
        BIOMES.register(name, biome);
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(FutureCherry.MODID, name));
        BiomeDictionary.addTypes(key, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.OVERWORLD);
        return key;
    }
}