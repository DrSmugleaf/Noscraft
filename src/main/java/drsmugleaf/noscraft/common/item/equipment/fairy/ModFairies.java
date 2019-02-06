package drsmugleaf.noscraft.common.item.equipment.fairy;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public class ModFairies {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Fairies eFairy : Fairies.values()) {
            ItemFairy fairy = new ItemFairy(eFairy);
            event.getRegistry().register(fairy);
        }
    }

    @SubscribeEvent
    public static void registerSlot(TextureStitchEvent.Pre event) {
        event.getMap().registerSprite(new ResourceLocation(Noscraft.MOD_ID, "items/fairy"));
    }

}
