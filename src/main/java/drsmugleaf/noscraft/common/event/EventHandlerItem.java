package drsmugleaf.noscraft.common.event;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.container.noscraft.FairyCapabilities;
import drsmugleaf.noscraft.common.item.equipment.fairy.IFairy;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 05/02/2019
 */
public class EventHandlerItem {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        if (
                stack.isEmpty() ||
                !(stack.getItem() instanceof IFairy) ||
                stack.hasCapability(FairyCapabilities.CAPABILITY_ITEM_FAIRY, null) ||
                event.getCapabilities().values().stream().anyMatch(capabilities -> capabilities.hasCapability(FairyCapabilities.CAPABILITY_ITEM_FAIRY, null))
            ) {
            return;
        }

        event.addCapability(new ResourceLocation(Noscraft.MOD_ID, "fairy"), new ICapabilityProvider() {
            @Override
            public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
                return capability == FairyCapabilities.CAPABILITY_ITEM_FAIRY;
            }

            @Override
            public @Nullable <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
                return capability == FairyCapabilities.CAPABILITY_ITEM_FAIRY ?
                        FairyCapabilities.CAPABILITY_ITEM_FAIRY.cast((IFairy) stack.getItem()) :
                        null;
            }
        });
    }

}
