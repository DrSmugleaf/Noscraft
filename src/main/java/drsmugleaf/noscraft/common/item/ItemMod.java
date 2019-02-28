package drsmugleaf.noscraft.common.item;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.IModellable;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public abstract class ItemMod extends Item implements IModellable {

    private final @Nonnull String NAME;
    private @Nonnull String REGISTRY_NAME;

    public ItemMod(@Nonnull String name, @Nonnull String registryName) {
        super();
        NAME = name;
        REGISTRY_NAME = registryName;
        REGISTRY_NAME = register(this);

        if (Noscraft.getProxy().isClient()) {
            ModelLoader.setCustomModelResourceLocation(this, 0, getModelResourceLocation());
        }
    }

    public @Nonnull String getName() {
        return NAME;
    }

    @Nonnull
    @Override
    public String getNameToRegister() {
        return REGISTRY_NAME;
    }

}
