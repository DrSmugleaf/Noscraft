package drsmugleaf.noscraft.common.network;

import drsmugleaf.noscraft.Noscraft;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 05/03/2019
 */
public class PacketOpenInventory extends PacketMod<PacketOpenInventory, IMessage> {

    private int id;

    public PacketOpenInventory() {}

    public PacketOpenInventory(int id) {
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
    }

    @Nullable
    @Override
    public IMessage handleOnServer(@Nonnull PacketOpenInventory message, @Nonnull MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        WorldServer world = (WorldServer) player.world;

        world.addScheduledTask(() -> {
            player.openContainer.onContainerClosed(player);
            player.openGui(Noscraft.getInstance(), message.id, world, 0, 0, 0);
        });

        return null;
    }

}
