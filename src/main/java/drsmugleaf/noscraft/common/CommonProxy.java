package drsmugleaf.noscraft.common;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.gui.GuiExpanded;
import drsmugleaf.noscraft.common.container.ContainerExpanded;
import drsmugleaf.noscraft.common.item.equipment.armor.ModArmors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class CommonProxy implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiExpanded.ID:
                return new ContainerExpanded(player);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public void init() {}

    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(ModArmors.class);
    }

    public boolean isServer() {
        return true;
    }

}
