package drsmugleaf.noscraft.common.network;

import drsmugleaf.noscraft.Noscraft;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ModPackets {

    private static int nextID = 0;
    public static final @Nonnull SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Noscraft.MOD_ID);

    private static <REQ extends PacketMod<REQ, REPLY>, REPLY extends IMessage> void register(@Nonnull Class<REQ> packet) {
        INSTANCE.registerMessage(packet, packet, nextID++, Side.CLIENT);
        INSTANCE.registerMessage(packet, packet, nextID++, Side.SERVER);
    }

    public static void init() {
        register(PacketOpenNormalInventory.class);
        register(PacketOpenNoscraftInventory.class);
        register(PacketSync.class);
        register(PacketHellDrop.class);
        register(PacketCooldownOutOfSync.class);
        register(PacketOpenNormalInventory.class);
        register(PacketOpenSkillMenu.class);
    }

}
