package drsmugleaf.noscraft.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 28/02/2019
 */
public abstract class PacketMod<REQ extends IMessage, REPLY extends IMessage> implements IMessage, IMessageHandler<REQ, REPLY> {

    public PacketMod() {}

    @Nullable
    public REPLY handleOnClient(@Nonnull REQ message, @Nonnull MessageContext ctx) {
        return null;
    }

    @Nullable
    public REPLY handleOnServer(@Nonnull REQ message, @Nonnull MessageContext ctx) {
        return null;
    }

    public final void sendTo(EntityPlayerMP player) {
        ModPackets.INSTANCE.sendTo(this, player);
    }

    public final void sendToServer() {
        ModPackets.INSTANCE.sendToServer(this);
    }

    @Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public void toBytes(ByteBuf buf) {}

    @Nullable
    @Override
    public final REPLY onMessage(REQ message, MessageContext ctx) {
        switch (ctx.side) {
            case CLIENT:
                return handleOnClient(message, ctx);
            case SERVER:
                return handleOnServer(message, ctx);
        }

        return null;
    }

}
