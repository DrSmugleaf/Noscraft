package drsmugleaf.noscraft.common.item;

import drsmugleaf.noscraft.Noscraft;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 09/02/2019
 */
public interface IModellable {

    @Nonnull String LAYER0_PREFIX = Noscraft.MOD_ID + ":items/";

    @Nonnull String getLayer0Path();

    @Nonnull String getName();

    static @Nonnull String toRegistryName(@Nonnull String name) {
        return name
                .replace(' ', '_')
                .replaceAll(":", "")
                .toLowerCase();
    }

    default @Nonnull String toRegistryName() {
        return toRegistryName(getName());
    }

}
