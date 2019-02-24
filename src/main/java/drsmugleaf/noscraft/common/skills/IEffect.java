package drsmugleaf.noscraft.common.skills;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 21/02/2019
 */
public interface IEffect {

    void use(@Nonnull World world, @Nonnull BlockPos pos);

}
