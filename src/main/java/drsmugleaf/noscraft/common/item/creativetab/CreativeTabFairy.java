package drsmugleaf.noscraft.common.item.creativetab;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 06/02/2019
 */
public class CreativeTabFairy extends CreativeTabs {

    @GameRegistry.ObjectHolder(Noscraft.MOD_ID + ":fire_fairy")
    @Nonnull
    private static final Item FIRE_FAIRY = null;

    @Nonnull
    public static final CreativeTabFairy INSTANCE = new CreativeTabFairy();

    private CreativeTabFairy() {
        super("fairies");
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
