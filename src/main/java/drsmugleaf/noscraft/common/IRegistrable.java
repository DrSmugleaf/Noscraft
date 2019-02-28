package drsmugleaf.noscraft.common;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Created by DrSmugleaf on 17/02/2019
 */
public interface IRegistrable {

    @Nonnull String getNameToRegister();

    static @Nonnull String toRegistryName(@Nonnull String name) {
        return name
                .replace(' ', '_')
                .replaceAll(":", "")
                .toLowerCase();
    }

    default @Nonnull String toRegistryName() {
        return toRegistryName(getNameToRegister());
    }

    default @Nonnull ResourceLocation toResourceLocation(@Nonnull String name) {
        return new ResourceLocation(Noscraft.MOD_ID, toRegistryName(name));
    }

    default @Nonnull ResourceLocation toResourceLocation() {
        return new ResourceLocation(Noscraft.MOD_ID, toRegistryName());
    }

    @SuppressWarnings("unchecked")
    default @Nonnull <T extends IForgeRegistryEntry.Impl & IRegistrable> String registerEntry(T entry) {
        ResourceLocation location = toResourceLocation();
        entry.setRegistryName(location);
        return Objects.requireNonNull(entry.getRegistryName()).getResourcePath();
    }

    default @Nonnull <T extends Block & IRegistrable> String register(@Nonnull T block) {
        String name = registerEntry(block);
        block.setUnlocalizedName(name);
        return name;
    }

    default @Nonnull <T extends Item & IRegistrable> String register(@Nonnull T item) {
        String name = registerEntry(item);
        item.setUnlocalizedName(name);
        return name;
    }

}
