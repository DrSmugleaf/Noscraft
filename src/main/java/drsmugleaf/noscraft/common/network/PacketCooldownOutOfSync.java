package drsmugleaf.noscraft.common.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 20/02/2019
 */
public class PacketCooldownOutOfSync extends PacketMod<PacketCooldownOutOfSync, IMessage> {

    public PacketCooldownOutOfSync() {}

    @Nullable
    @Override
    public IMessage handleOnClient(@Nonnull PacketCooldownOutOfSync message, @Nonnull MessageContext ctx) {
        return super.handleOnClient(message, ctx);
    }

}
