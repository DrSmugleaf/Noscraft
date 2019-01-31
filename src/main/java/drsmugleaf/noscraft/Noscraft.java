package drsmugleaf.noscraft;

import com.sun.istack.internal.NotNull;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

}
