package drsmugleaf.noscraft.common.item.equipment.fairy;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class ModFairies {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {}

}
