package drsmugleaf.noscraft.common.network;

import drsmugleaf.noscraft.common.entity.EntityNukeCircle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 19/02/2019
 */
public class PacketHellDrop implements IMessage, IMessageHandler<PacketHellDrop, PacketCooldownOutOfSync> {

    public int entity;
    private BlockPos pos;

    public PacketHellDrop() {}

    public PacketHellDrop(int entity, @Nonnull BlockPos pos) {
        this.entity = entity;
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entity = buf.readInt();
        pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entity);
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    @Override
    public PacketCooldownOutOfSync onMessage(PacketHellDrop message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        WorldServer mainThread = (WorldServer) player.world;
        BlockPos pos = message.pos;

        mainThread.addScheduledTask(() -> mainThread.spawnEntity(new EntityNukeCircle(mainThread, pos.getX(), pos.getY(), pos.getZ())));

        return null;
    }

}
