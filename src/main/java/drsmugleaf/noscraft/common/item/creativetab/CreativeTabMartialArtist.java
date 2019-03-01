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
public class CreativeTabMartialArtist extends CreativeTabs {

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":martialartist.short_knight_glove")
    @Nonnull
    private static final Item ICON = null;

    @Nonnull
    public static final CreativeTabMartialArtist INSTANCE = new CreativeTabMartialArtist();

    private CreativeTabMartialArtist() {
        super("martialartist");
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
