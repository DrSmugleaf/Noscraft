package drsmugleaf.noscraft.common.item.creativetab;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 28/02/2019
 */
public class CreativeTabMage extends CreativeTabs {

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":mage.magic_stick_for_beginners")
    @Nonnull
    private static final Item ICON = null;

    @Nonnull
    public static final CreativeTabMage INSTANCE = new CreativeTabMage();

    private CreativeTabMage() {
        super("mage");
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ICON);
    }

}
