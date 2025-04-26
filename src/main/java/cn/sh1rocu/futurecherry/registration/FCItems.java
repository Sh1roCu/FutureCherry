package cn.sh1rocu.futurecherry.registration;

import cn.sh1rocu.futurecherry.FutureCherry;
import cn.sh1rocu.futurecherry.entity.boat.CherryBoatType;
import cn.sh1rocu.futurecherry.item.CherryBoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class FCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FutureCherry.MODID);

    public static final RegistryObject<Item> CHERRY_SIGN = registerItem("cherry_sign",
            () -> new SignItem(new Item.Properties().tab(FCItemGroups.FC_GROUP).stacksTo(16),
                    FCBlocks.CHERRY_SIGN.get(), FCBlocks.CHERRY_WALL_SIGN.get()));

    public static final RegistryObject<Item> CHERRY_BOAT = registerItem("cherry_boat",
            () -> new CherryBoatItem(CherryBoatType.CHERRY, new Item.Properties().tab(FCItemGroups.FC_GROUP).stacksTo(1)));

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }
}
