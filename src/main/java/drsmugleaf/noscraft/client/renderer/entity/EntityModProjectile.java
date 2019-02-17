package drsmugleaf.noscraft.client.renderer.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 17/02/2019
 */
public abstract class EntityModProjectile extends EntityArrow {

    public EntityModProjectile(World worldIn) {
        super(worldIn);
    }

    public EntityModProjectile(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityModProjectile(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public void spawnTrailParticles() {}

    @Nonnull
    @Override
    protected final ItemStack getArrowStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public final boolean getIsCritical() {
        return false;
    }

    @Override
    public final void setIsCritical(boolean critical) {}

    @Override
    public boolean getIsInvulnerable() {
        return true;
    }

    @Override
    public void onUpdate() {
        if (world.isRemote) {
            spawnTrailParticles();
        }

        super.onUpdate();
    }

    @Override
    protected void onHit(@Nonnull RayTraceResult result) {
        setDead();
    }

}
