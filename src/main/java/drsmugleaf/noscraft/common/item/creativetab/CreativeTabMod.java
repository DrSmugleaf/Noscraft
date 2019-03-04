package drsmugleaf.noscraft.common.item.creativetab;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 03/03/2019
 */
public class CreativeTabMod extends CreativeTabs {

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":logo_icon")
    @Nonnull
    private static final Item LOGO_ICON = null;

    @Nonnull
    public static final CreativeTabMod INSTANCE = new CreativeTabMod();

    private CreativeTabMod() {
        super("noscraft");
    }

    @Nonnull
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(LOGO_ICON);
    }

}
