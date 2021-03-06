package drsmugleaf.noscraft;

import drsmugleaf.noscraft.common.CommonProxy;
import drsmugleaf.noscraft.common.container.noscraft.Equipment;
import drsmugleaf.noscraft.common.container.noscraft.EquipmentCapabilities;
import drsmugleaf.noscraft.common.container.skill.SkillCapabilities;
import drsmugleaf.noscraft.common.container.skill.SkillsLearned;
import drsmugleaf.noscraft.common.network.ModPackets;
import drsmugleaf.noscraft.common.skills.ModSkills;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
@Mod(
        modid = Noscraft.MOD_ID,
        name = Noscraft.MOD_NAME,
        version = Noscraft.VERSION,
        dependencies = "required-after:forge@[14.23.5.2768,);",
        useMetadata = true
)
public class Noscraft {

    public static final @Nonnull String MOD_ID = "noscraft";
    public static final @Nonnull String MOD_NAME = "Noscraft";
    public static final int MAJOR_VERSION = 0;
    public static final int MINOR_VERSION = 0;
    public static final int REVISION_VERSION = 0;
    public static final int BUILD_VERSION = 1;
    public static final @Nonnull String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    @SidedProxy(clientSide = "drsmugleaf.noscraft.client.ClientProxy", serverSide = "drsmugleaf.noscraft.common.CommonProxy")
    private static @Nonnull CommonProxy PROXY;

    @Mod.Instance(value = MOD_ID)
    private static @Nonnull Noscraft INSTANCE;

    public static final @Nonnull Logger LOG = LogManager.getLogger(MOD_ID.toUpperCase());

    public static final @Nonnull String ASSETS = Objects.requireNonNull(
            Noscraft.class.getClassLoader().getResource("assets/noscraft")
    ).getFile();

    public static @Nonnull Noscraft getInstance() {
        return INSTANCE;
    }

    public static @Nonnull CommonProxy getProxy() {
        return PROXY;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(
                SkillsLearned.class,
                new SkillCapabilities.CapabilitySkills(),
                SkillsLearned::new
        );

        CapabilityManager.INSTANCE.register(
                Equipment.class,
                new EquipmentCapabilities.CapabilityEquipment(),
                Equipment::new
        );

        PROXY.registerEventHandlers();
        ModPackets.init();
        PROXY.preInit(event);
        MinecraftForge.EVENT_BUS.post(new RegistryEvent.Register<>(ModSkills.SKILLS_REGISTRY_NAME, ModSkills.SKILLS));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, PROXY);
        PROXY.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }

}
