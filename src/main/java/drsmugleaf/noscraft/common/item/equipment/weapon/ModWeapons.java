package drsmugleaf.noscraft.common.item.equipment.weapon;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 09/02/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class ModWeapons {

    private static final @Nonnull String CSV_PATH = Noscraft.ASSETS + "/csv/weapon/";

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {}

}
