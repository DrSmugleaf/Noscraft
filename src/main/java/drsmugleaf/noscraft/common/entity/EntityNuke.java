package drsmugleaf.noscraft.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class EntityNuke extends EntityArrow {

    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(EntityNuke.class, DataSerializers.BOOLEAN);

    public EntityNuke(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        onUpdate();
    }

    public EntityNuke(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter.posX, shooter.posY + shooter.getEyeHeight() - 0.2D, shooter.posZ);
        shootingEntity = shooter;
        onUpdate();
    }

    public EntityNuke(World worldIn) {
        super(worldIn);
        onUpdate();
    }

    @Override
    protected void entityInit() {
        dataManager.register(INVULNERABLE, Boolean.FALSE);
    }

    @Nonnull
    @Override
    protected ItemStack getArrowStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public boolean getIsCritical() {
        return false;
    }

    @Override
    public void setIsCritical(boolean critical) {}

    @Override
    protected void onHit(@Nonnull RayTraceResult result) {
        world.createExplosion(this, posX, posY, posZ, 6.0F, true);
        setDead();
    }

}
