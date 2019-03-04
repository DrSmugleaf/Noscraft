package drsmugleaf.noscraft.common.item;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.IModellable;
import drsmugleaf.noscraft.common.item.creativetab.CreativeTabMod;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public abstract class ItemMod extends Item implements IModellable {

    private final @Nonnull String NAME;

    public ItemMod(@Nonnull String name) {
        super();
        NAME = name;
        setCreativeTab(CreativeTabMod.INSTANCE);
        register(this);

        if (Noscraft.getProxy().isClient()) {
            ModelLoader.setCustomModelResourceLocation(this, 0, getModelResourceLocation());
        }
    }

    public @Nonnull String getName() {
        return NAME;
    }

}
