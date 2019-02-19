package drsmugleaf.noscraft.common.item.ability;

import drsmugleaf.noscraft.common.item.ItemMod;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 17/02/2019
 */
public class Ability extends ItemMod {

    public Ability(@Nonnull String name) {
        super(name);
    }

    @Nonnull
    @Override
    public String getLayer0Path() {
        return LAYER0_PREFIX + "ability/" + toRegistryName();
    }

}
