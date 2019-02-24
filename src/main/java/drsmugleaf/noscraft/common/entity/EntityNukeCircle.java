package drsmugleaf.noscraft.common.entity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 18/02/2019
 */
public class EntityNukeCircle extends EntityMod {

    private boolean spawnedNuke = false;

    public EntityNukeCircle(World worldIn) {
        super(worldIn);
    }

    public EntityNukeCircle(World worldIn, double x, double y, double z) {
        super(worldIn, x, y + 1, z);
    }

    public EntityNukeCircle(World worldIn, @Nonnull BlockPos pos) {
        super(worldIn, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean getIsInvulnerable() {
        return true;
    }

    @Override
    public void onUpdate() {
        if (ticksExisted > 80 && !spawnedNuke) {
            world.spawnEntity(new EntityNuke(world, posX, posY, posZ, this));
            spawnedNuke = true;
        }

        if (ticksExisted > 300) {
            setDead();
        }
    }

}
