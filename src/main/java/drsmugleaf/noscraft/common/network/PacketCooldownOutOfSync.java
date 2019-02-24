package drsmugleaf.noscraft.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by DrSmugleaf on 20/02/2019
 */
public class PacketCooldownOutOfSync implements IMessage, IMessageHandler<PacketCooldownOutOfSync, IMessage> {

    public PacketCooldownOutOfSync() {}

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public IMessage onMessage(PacketCooldownOutOfSync message, MessageContext ctx) {
        return null;
    }

}
