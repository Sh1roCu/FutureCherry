package cn.sh1rocu.futurecherry.registration;

import cn.sh1rocu.futurecherry.FutureCherry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class FCItemGroups {
    public static final ItemGroup FC_GROUP = new FCItemGroup("cherry", () -> new ItemStack(FCBlocks.CHERRY_SAPLING.get()));

    public static class FCItemGroup extends ItemGroup {
        private final Supplier<ItemStack> displayStack;

        public FCItemGroup(String id, Supplier<ItemStack> icon) {
            super(FutureCherry.MODID + "." + id);
            this.displayStack = icon;
        }

        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return this.displayStack.get();
        }
    }
}
