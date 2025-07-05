package cn.sh1rocu.futurecherry;

import cn.sh1rocu.futurecherry.block.blockentity.CherrySignTypes;
import cn.sh1rocu.futurecherry.entity.render.BoatEntityRenderer;
import cn.sh1rocu.futurecherry.particle.CherryLeavesParticle;
import cn.sh1rocu.futurecherry.registration.*;
import cn.sh1rocu.futurecherry.world.biome.FCOverWorldBiomes;
import net.minecraft.block.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(FutureCherry.MODID)
public class FutureCherry {

    public static final String MODID = "futurecherry";

    public FutureCherry() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FCBlocks.BLOCKS.register(modEventBus);
        FCBlocks.BLOCK_ITEMS.register(modEventBus);
        FCItems.ITEMS.register(modEventBus);
        FCBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        FCEntities.ENTITY_TYPES.register(modEventBus);
        FCParticleTypes.PARTICLE_TYPES.register(modEventBus);
        FCOverWorldBiomes.BIOMES.register(modEventBus);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WoodType.register(CherrySignTypes.CHERRY);
        });
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(FutureCherry::initBlockEntityRenderer);
        RenderingRegistry.registerEntityRenderingHandler(FCEntities.CHERRY_BOAT.get(), BoatEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent<Biome> event) {
        FCFeatures.init();
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(FCOverWorldBiomes.CHERRY_GROVE, 20));
    }

    private static void initBlockEntityRenderer() {
        RenderTypeLookup.setRenderLayer(FCBlocks.CHERRY_LEAVES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.CHERRY_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.POTTED_CHERRY_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.CHERRY_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.CHERRY_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(FCBlocks.PINK_PETALS.get(), RenderType.cutout());
        Atlases.addWoodType(CherrySignTypes.CHERRY);
        ClientRegistry.bindTileEntityRenderer(FCBlockEntities.CHERRY_SIGNS.get(), SignTileEntityRenderer::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(FCParticleTypes.CHERRY_LEAVES.get(), sprite ->
                (basicParticleType, clientWorld, x, y, z, vx, vy, vz) ->
                        new CherryLeavesParticle(clientWorld, x, y, z, sprite));
    }
}
