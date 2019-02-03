package drsmugleaf.noscraft.client;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.client.gui.GuiExpanded;
import drsmugleaf.noscraft.client.gui.ModGuis;
import drsmugleaf.noscraft.common.CommonProxy;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public @Nullable Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world instanceof WorldClient) {
            switch (ID) {
                case GuiExpanded.ID:
                    return new GuiExpanded(player);
            }
        }

        return null;
    }

    @Override
    public void init() {}

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();

        MinecraftForge.EVENT_BUS.register(ModGuis.class);
    }

    @Override
    public boolean isServer() {
        return false;
    }

}
