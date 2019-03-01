package drsmugleaf.noscraft.common.skills.archer.destroyer;

import drsmugleaf.noscraft.common.network.PacketHellDrop;
import drsmugleaf.noscraft.common.skills.IEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class HellDrop implements IEffect {

    @Override
    public void use(@Nonnull World world, @Nonnull BlockPos pos) {
        new PacketHellDrop(1, pos).sendToServer();
    }

}
