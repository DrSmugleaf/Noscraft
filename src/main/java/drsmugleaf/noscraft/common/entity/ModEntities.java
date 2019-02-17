package drsmugleaf.noscraft.common.entity;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class ModEntities {

    public static final EntityEntry NUKE = EntityEntryBuilder
            .create()
            .entity(EntityNuke.class)
            .id(new ResourceLocation(Noscraft.MOD_ID, "nuke"), 0)
            .name("nuke")
            .tracker(64, 5, true)
            .build();

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(NUKE);
//        EntityRegistry.registerModEntity(new ResourceLocation(Noscraft.MOD_ID, "nuke"), EntityNuke.class, Noscraft.MOD_ID + ":nuke", 0, Noscraft.getInstance(), 64, 3, true);
    }

}
