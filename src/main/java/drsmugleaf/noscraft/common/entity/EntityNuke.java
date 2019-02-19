package drsmugleaf.noscraft.common.entity;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class EntityNuke extends EntityMod {

    private static final double Y_OFFSET = 50;
    private static final double Y_MOTION = -5;
    private final double Y_TARGET;

    @Nonnull
    private final EntityNukeCircle CIRCLE;

    public EntityNuke(World world) {
        super(world);
        Y_TARGET = -1;
        CIRCLE = new EntityNukeCircle(world);
        noClip = true;
    }

    public EntityNuke(World world, double xTarget, double yTarget, double zTarget, @Nonnull EntityNukeCircle circle) {
        super(world, xTarget, yTarget + Y_OFFSET, zTarget);
        Y_TARGET = yTarget;
        CIRCLE = circle;
        noClip = true;
    }

    private void explode() {
        CIRCLE.setDead();
        world.newExplosion(this, posX, Y_TARGET, posZ, 24.0F, false, false);
        
        world.playSound(null, posX, Y_TARGET, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (0.8F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
        world.playSound(null, posX, Y_TARGET, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (0.7F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
        world.playSound(null, posX, Y_TARGET, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (0.6F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
        
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, Y_TARGET, posZ, 1.0D, 0.0D, 0.0D);
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, Y_TARGET + 6, posZ, 1.0D, 0.0D, 0.0D);
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, Y_TARGET + 12, posZ, 1.0D, 0.0D, 0.0D);
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, Y_TARGET + 18, posZ, 1.0D, 0.0D, 0.0D);
        radialExplosions(posX, Y_TARGET, posZ);
        radialExplosions(posX, Y_TARGET + 24, posZ);

        setDead();
    }

    private void radialExplosions(double x, double y, double z) {
        x += 4;
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 1.0D, 0.0D, 0.0D);
        z += 4;
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 1.0D, 0.0D, 0.0D);
        z -= 8;
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 1.0D, 0.0D, 0.0D);
        x -= 8;
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 1.0D, 0.0D, 0.0D);
        z += 4;
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 1.0D, 0.0D, 0.0D);        z += 4;
        z += 4;
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 1.0D, 0.0D, 0.0D);
    }

    @Override
    public boolean getIsInvulnerable() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        motionY = Y_MOTION;

        if (posY < Y_TARGET || ticksExisted > 200) {
            explode();
        }
    }

    @Override
    public void spawnTrailParticles() {
        world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, posX, posY - 0.3D, posZ, rand.nextGaussian() * 0.05D, -motionY * 0.5D, rand.nextGaussian() * 0.05D);
    }

}
