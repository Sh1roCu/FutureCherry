package cn.sh1rocu.futurecherry.registration;

import cn.sh1rocu.futurecherry.FutureCherry;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class FCParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, FutureCherry.MODID);

    public static final RegistryObject<BasicParticleType> CHERRY_LEAVES = register("cherry_leaves_particle", () -> new BasicParticleType(false));

    public static <T extends ParticleType<?>> RegistryObject<T> register(String name, Supplier<T> particleType) {
        return PARTICLE_TYPES.register(name, particleType);
    }
}