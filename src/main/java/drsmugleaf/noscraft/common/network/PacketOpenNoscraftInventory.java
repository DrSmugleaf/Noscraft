package drsmugleaf.noscraft.common.network;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.gui.GuiExpanded;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class PacketOpenNoscraftInventory extends PacketMod<PacketOpenNoscraftInventory, IMessage> {

    public PacketOpenNoscraftInventory() {}

    @Nullable
    @Override
    public IMessage handleOnServer(@Nonnull PacketOpenNoscraftInventory message, @Nonnull MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        WorldServer world = (WorldServer) player.world;

        world.addScheduledTask(() -> {
            player.openContainer.onContainerClosed(player);
            player.openGui(Noscraft.getInstance(), GuiExpanded.ID, world, 0, 0, 0);
        });

        return null;
    }

}
