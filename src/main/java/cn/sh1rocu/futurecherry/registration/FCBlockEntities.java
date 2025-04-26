package cn.sh1rocu.futurecherry.registration;

import cn.sh1rocu.futurecherry.FutureCherry;
import cn.sh1rocu.futurecherry.block.blockentity.CherrySignBlockEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FCBlockEntities {
    public static final DeferredRegister<TileEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, FutureCherry.MODID);

    public static final RegistryObject<TileEntityType<CherrySignBlockEntity>> CHERRY_SIGNS = BLOCK_ENTITIES.register("sign_block_entity", () ->
            TileEntityType.Builder.of(CherrySignBlockEntity::new,
                            FCBlocks.CHERRY_SIGN.get(),
                            FCBlocks.CHERRY_WALL_SIGN.get()).
                    build(null));
}