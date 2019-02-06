package drsmugleaf.noscraft.common.network;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.container.FairyCapabilities;
import drsmugleaf.noscraft.common.container.FairyContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 05/02/2019
 */
public class PacketSync implements IMessage {

    private int playerID;
    private byte slot;
    private @Nonnull ItemStack stack;

    public PacketSync() {}

    public PacketSync(@Nonnull EntityPlayer player, int slot, ItemStack stack) {
        playerID = player.getEntityId();
        this.slot = (byte) slot;
        this.stack = stack;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerID = buf.readInt();
        slot = buf.readByte();
        stack = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerID);
        buf.writeByte(slot);
        ByteBufUtils.writeItemStack(buf, stack);
    }

    public static class Handler implements IMessageHandler<PacketSync, IMessage> {
        @Override
        public IMessage onMessage(PacketSync message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                World world = Noscraft.getProxy().getClientWorld();

                if (world == null) {
                    return;
                }

                Entity player = world.getEntityByID(message.playerID);
                if (player instanceof EntityPlayer) {
                    FairyContainer container = player.getCapability(FairyCapabilities.CAPABILITY_FAIRIES, null);
                    container.setStackInSlot(message.slot, message.stack);
                }
            });

            return null;
        }
    }

}
