package drsmugleaf.noscraft;

import com.sun.istack.internal.NotNull;
import drsmugleaf.noscraft.common.CommonProxy;
import drsmugleaf.noscraft.common.network.PacketHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
@Mod(modid = Noscraft.MOD_ID, useMetadata = true)
public class Noscraft {

    public static final @NotNull String MOD_ID = "noscraft";
    public static final int MAJOR_VERSION = 0;
    public static final int MINOR_VERSION = 0;
    public static final int REVISION_VERSION = 0;
    public static final int BUILD_VERSION = 1;

    @SidedProxy(clientSide = "drsmugleaf.noscraft.client.ClientProxy", serverSide = "drsmugleaf.noscraft.common.CommonProxy")
    private static @Nonnull CommonProxy PROXY;

    @Mod.Instance(value = MOD_ID)
    private static @Nonnull Noscraft INSTANCE;

    @Nonnull
    public static Noscraft getInstance() {
        return INSTANCE;
    }

    @Nonnull
    public static CommonProxy getProxy() {
        return PROXY;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PROXY.registerEventHandlers();
        PacketHandler.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, PROXY);
        PROXY.init();
    }

}
