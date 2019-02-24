package drsmugleaf.noscraft.common.skills.archer.destroyer;

import drsmugleaf.noscraft.common.skills.IEffect;
import drsmugleaf.noscraft.common.network.ModPackets;
import drsmugleaf.noscraft.common.network.PacketHellDrop;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class HellDrop implements IEffect {

    @Override
    public void use(@Nonnull World world, @Nonnull BlockPos pos) {
        ModPackets.INSTANCE.sendToServer(new PacketHellDrop(1, pos));
    }

}
