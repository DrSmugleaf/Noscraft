package drsmugleaf.noscraft.common.item;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 06/02/2019
 */
public class NoscraftFairyTab extends CreativeTabs {

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":fire_fairy")
    private static final @Nonnull Item FIRE_FAIRY = null;

    public static final @Nonnull NoscraftFairyTab INSTANCE = new NoscraftFairyTab();

    private NoscraftFairyTab() {
        super("Fairies");
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(FIRE_FAIRY);
    }

}
