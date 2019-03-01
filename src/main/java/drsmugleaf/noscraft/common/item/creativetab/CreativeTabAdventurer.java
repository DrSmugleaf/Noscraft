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
public class CreativeTabAdventurer extends CreativeTabs {

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":adventurer.piece_of_wood")
    @Nonnull
    private static final Item ICON = null;

    @Nonnull
    public static final CreativeTabAdventurer INSTANCE = new CreativeTabAdventurer();

    private CreativeTabAdventurer() {
        super("adventurer");
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
