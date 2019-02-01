package drsmugleaf.noscraft.common.network;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.gui.GuiExpanded;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class PacketOpenNoscraftInventory implements IMessage, IMessageHandler<PacketOpenNoscraftInventory, IMessage> {

    public PacketOpenNoscraftInventory() {}

    @Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public void toBytes(ByteBuf buf) {}

    @Override
    public IMessage onMessage(PacketOpenNoscraftInventory message, MessageContext ctx) {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        mainThread.addScheduledTask(() -> {
            ctx.getServerHandler().player.openContainer.onContainerClosed(ctx.getServerHandler().player);
            ctx.getServerHandler().player.openGui(Noscraft.getInstance(), GuiExpanded.ID, ctx.getServerHandler().player.world, 0, 0, 0);
        });

        return null;
    }

}
