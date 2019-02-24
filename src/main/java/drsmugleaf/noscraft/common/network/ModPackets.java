package drsmugleaf.noscraft.common.network;

import drsmugleaf.noscraft.Noscraft;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ModPackets {

    public static final @Nonnull SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Noscraft.MOD_ID);

    public static void init() {
        INSTANCE.registerMessage(PacketOpenNormalInventory.class, PacketOpenNormalInventory.class, 0, Side.SERVER);
        INSTANCE.registerMessage(PacketOpenNoscraftInventory.class, PacketOpenNoscraftInventory.class, 1, Side.SERVER);
        INSTANCE.registerMessage(PacketSync.Handler.class, PacketSync.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(PacketHellDrop.class, PacketHellDrop.class, 3, Side.SERVER);
        INSTANCE.registerMessage(PacketCooldownOutOfSync.class, PacketCooldownOutOfSync.class, 4, Side.CLIENT);
        INSTANCE.registerMessage(PacketOpenSkillMenu.class, PacketOpenSkillMenu.class, 5, Side.SERVER);
    }

}
