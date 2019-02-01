package drsmugleaf.noscraft.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class PacketOpenNormalInventory implements IMessage, IMessageHandler<PacketOpenNormalInventory, IMessage> {

    public PacketOpenNormalInventory() {}

    @Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public void toBytes(ByteBuf buf) {}

    @Override
    public IMessage onMessage(PacketOpenNormalInventory message, MessageContext ctx) {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        mainThread.addScheduledTask(() -> {
            ctx.getServerHandler().player.openContainer.onContainerClosed(ctx.getServerHandler().player);
            ctx.getServerHandler().player.openContainer = ctx.getServerHandler().player.inventoryContainer;
        });

        return null;
    }

}
