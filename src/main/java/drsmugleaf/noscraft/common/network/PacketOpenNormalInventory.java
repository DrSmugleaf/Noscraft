package drsmugleaf.noscraft.common.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class PacketOpenNormalInventory extends PacketMod<PacketOpenNormalInventory, IMessage> {

    public PacketOpenNormalInventory() {}

    @Nullable
    @Override
    public IMessage handleOnServer(@Nonnull PacketOpenNormalInventory message, @Nonnull MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        WorldServer world = (WorldServer) player.world;

        world.addScheduledTask(() -> {
            player.openContainer.onContainerClosed(player);
            player.openContainer = player.inventoryContainer;
        });

        return null;
    }

}
