package cn.sh1rocu.futurecherry.item;


import cn.sh1rocu.futurecherry.entity.boat.CherryBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class CherryBoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntityPredicates.NO_SPECTATORS.and(Entity::isPickable);
    private final CherryBoatEntity.Type type;

    public CherryBoatItem(CherryBoatEntity.Type pType, Properties pProperties) {
        super(pProperties);
        this.type = pType;
    }

    public ActionResult<ItemStack> use(World pLevel, PlayerEntity pPlayer, Hand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        RayTraceResult hitResult = getPlayerPOVHitResult(pLevel, pPlayer, RayTraceContext.FluidMode.ANY);
        if (hitResult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemstack);
        } else {
            Vector3d vec3 = pPlayer.getViewVector(1.0F);
            List<Entity> list = pLevel.getEntities(pPlayer, pPlayer.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vector3d vec31 = pPlayer.getEyePosition(1.0F);
                for (Entity entity : list) {
                    AxisAlignedBB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (aabb.contains(vec31)) {
                        return ActionResult.pass(itemstack);
                    }
                }
            }
            if (hitResult.getType() == RayTraceResult.Type.BLOCK) {
                BoatEntity boat = this.getBoat(pLevel, hitResult);
                if (boat instanceof CherryBoatEntity) {
                    ((CherryBoatEntity) boat).setVariant(this.type);
                }
                boat.yRot = pPlayer.yRot;
                if (!pLevel.noCollision(boat, boat.getBoundingBox())) {
                    return ActionResult.fail(itemstack);
                } else {
                    if (!pLevel.isClientSide) {
                        pLevel.addFreshEntity(boat);
                        if (!pPlayer.abilities.instabuild) {
                            itemstack.shrink(1);
                        }
                    }
                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.sidedSuccess(itemstack, pLevel.isClientSide());
                }
            } else {
                System.out.println("reason: hitResult is not Block");
                return ActionResult.pass(itemstack);
            }
        }
    }

    private BoatEntity getBoat(World p_220017_, RayTraceResult p_220018_) {
        return new CherryBoatEntity(p_220017_, p_220018_.getLocation().x, p_220018_.getLocation().y, p_220018_.getLocation().z);
    }
}