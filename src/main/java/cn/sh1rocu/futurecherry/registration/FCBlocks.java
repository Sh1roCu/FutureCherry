package cn.sh1rocu.futurecherry.registration;

import cn.sh1rocu.futurecherry.FutureCherry;
import cn.sh1rocu.futurecherry.block.*;
import cn.sh1rocu.futurecherry.block.blockentity.CherrySignTypes;
import cn.sh1rocu.futurecherry.world.gen.tree.CherryTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class FCBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FutureCherry.MODID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FutureCherry.MODID);

    public static final RegistryObject<Block> STRIPPED_CHERRY_LOG = registerBlock("stripped_cherry_log", () -> new BaseCherryBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG), null), FCItemGroups.FC_GROUP);
    public static final RegistryObject<Block> CHERRY_LOG = registerBlock("cherry_log", () -> new BaseCherryBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG), STRIPPED_CHERRY_LOG), FCItemGroups.FC_GROUP);
    public static final RegistryObject<Block> STRIPPED_CHERRY_WOOD = registerBlock("stripped_cherry_wood", () -> new BaseCherryBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD), null), FCItemGroups.FC_GROUP);
    public static final RegistryObject<Block> CHERRY_WOOD = registerBlock("cherry_wood", () -> new BaseCherryBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD), STRIPPED_CHERRY_WOOD), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_LEAVES = registerBlock("cherry_leaves",
            () -> new CherryLeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
                    return 60;
                }
            }, FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_SAPLING = registerBlock("cherry_sapling",
            () -> new SaplingBlock(new CherryTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_PLANKS = registerBlock("cherry_planks",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
                    return 20;
                }
            }, FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> POTTED_CHERRY_SAPLING = registerBlock("potted_cherry_sapling",
            () -> new FlowerPotBlock(CHERRY_SAPLING.get(), AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()), null, false);

    public static final RegistryObject<Block> CHERRY_BUTTON = registerBlock("cherry_button",
            () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> PINK_PETALS = registerBlock("pink_petals",
            () -> new CherryBushBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_PINK).noCollission().sound(SoundType.GRASS)), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_STAIRS = registerBlock("cherry_stairs",
            () -> new StairsBlock(CHERRY_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(CHERRY_PLANKS.get())), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_SLAB = registerBlock("cherry_slab",
            () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_FENCE = registerBlock("cherry_fence",
            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), FCItemGroups.FC_GROUP);
    public static final RegistryObject<Block> CHERRY_FENCE_GATE = registerBlock("cherry_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F)), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_DOOR = registerBlock("cherry_door",
            () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD).strength(3.0f).sound(SoundType.WOOD).noOcclusion()), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_TRAPDOOR = registerBlock("cherry_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(3.0F).noOcclusion()), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_PRESSURE_PLATE = registerBlock("cherry_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F)), FCItemGroups.FC_GROUP);

    public static final RegistryObject<Block> CHERRY_SIGN = registerBlock("cherry_sign",
            () -> new CherryStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, FCBlocks.CHERRY_PLANKS.get().defaultMaterialColor()).noCollission().strength(1.0F), CherrySignTypes.CHERRY), null, false);
    public static final RegistryObject<Block> CHERRY_WALL_SIGN = registerBlock("cherry_wall_sign",
            () -> new CherryWallSignBlock(AbstractBlock.Properties.of(Material.WOOD, FCBlocks.CHERRY_LOG.get().defaultMaterialColor()).noCollission().strength(1.0F).dropsLike(CHERRY_SIGN.get()), CherrySignTypes.CHERRY), null, false);

    private static <T extends Block> RegistryObject<T> registerBlock(String registryName, Supplier<T> supplier, ItemGroup itemGroup) {
        return registerBlock(registryName, supplier, itemGroup, true);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String registryName, Supplier<T> supplier, ItemGroup itemGroup, boolean hasItem) {
        RegistryObject<T> block = BLOCKS.register(registryName, supplier);
        if (hasItem)
            BLOCK_ITEMS.register(registryName, () -> new BlockItem(block.get(), new Item.Properties().tab(itemGroup)));
        return block;
    }
}
