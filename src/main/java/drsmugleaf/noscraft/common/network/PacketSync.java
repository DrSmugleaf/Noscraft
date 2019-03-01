package drsmugleaf.noscraft.common.network;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.container.noscraft.FairyCapabilities;
import drsmugleaf.noscraft.common.container.noscraft.FairyContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 05/02/2019
 */
public class PacketSync extends PacketMod<PacketSync, IMessage> {

    private int playerID;
    private byte slot;
    private @Nonnull ItemStack stack;

    public PacketSync() {}

    public PacketSync(@Nonnull EntityPlayer player, int slot, @Nonnull ItemStack stack) {
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

    @Nullable
    @Override
    public IMessage handleOnServer(@Nonnull PacketSync message, @Nonnull MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            World world = Noscraft.getProxy().getClientWorld();

            if (world == null) {
                return;
            }

            Entity player = world.getEntityByID(message.playerID);
            if (player instanceof EntityPlayer) {
                FairyContainer container = player.getCapability(FairyCapabilities.CAPABILITY_FAIRIES, null);
                if (container == null) {
                    return;
                }

                container.setStackInSlot(message.slot, message.stack);
            }
        });

        return null;
    }

}
