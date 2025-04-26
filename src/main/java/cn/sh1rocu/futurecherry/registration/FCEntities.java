package cn.sh1rocu.futurecherry.registration;

import cn.sh1rocu.futurecherry.FutureCherry;
import cn.sh1rocu.futurecherry.entity.boat.CherryBoatEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FCEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, FutureCherry.MODID);

    public static final RegistryObject<EntityType<CherryBoatEntity>> CHERRY_BOAT =
            ENTITY_TYPES.register("cherry_boat", () -> EntityType.Builder.<CherryBoatEntity>of(CherryBoatEntity::new, EntityClassification.MISC)
                    .sized(1.375F, 0.5625F).build(new ResourceLocation(FutureCherry.MODID, "cherry_boat").toString()));
}