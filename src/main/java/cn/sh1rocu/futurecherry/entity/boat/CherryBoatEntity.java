package cn.sh1rocu.futurecherry.entity.boat;

import cn.sh1rocu.futurecherry.registration.FCBlocks;
import cn.sh1rocu.futurecherry.registration.FCEntities;
import cn.sh1rocu.futurecherry.registration.FCItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class CherryBoatEntity extends BoatEntity {
    private static final DataParameter<Integer> DATA_ID_TYPE = EntityDataManager.defineId(CherryBoatEntity.class, DataSerializers.INT);

    public CherryBoatEntity(EntityType<? extends BoatEntity> pEntityType, World pLevel) {
        super(pEntityType, pLevel);
    }

    public CherryBoatEntity(World level, double pX, double pY, double pZ) {
        this(FCEntities.CHERRY_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        switch (this.getVariant()) {
            case CHERRY:
                return FCItems.CHERRY_BOAT.get();
            default:
                return FCItems.CHERRY_BOAT.get();
        }
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FCItems.CHERRY_BOAT.get());
    }

    @Nonnull
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.CHERRY.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT pCompound) {
        pCompound.putString("Type", this.getVariant().getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public enum Type {
        CHERRY(FCBlocks.CHERRY_PLANKS.get(), "cherry");
        private final String name;
        private final Block planks;

        Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        public static Type byId(int pId) {
            Type[] aboatentity$type = values();
            if (pId < 0 || pId >= aboatentity$type.length) {
                pId = 0;
            }

            return aboatentity$type[pId];
        }

        public static Type byName(String pName) {
            Type[] aboatentity$type = values();

            for (Type type : aboatentity$type) {
                if (type.getName().equals(pName)) {
                    return type;
                }
            }

            return aboatentity$type[0];
        }
    }
}