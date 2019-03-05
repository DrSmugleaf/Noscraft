package drsmugleaf.noscraft.common;

import drsmugleaf.noscraft.Noscraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Created by DrSmugleaf on 17/02/2019
 */
public interface IRegistrable {

    static @Nonnull String toRegistryName(@Nonnull String name) {
        return name
                .replace(' ', '_')
                .replaceAll("[\\\\/]", "-")
                .replaceAll("[^A-Za-z0-9_\\-.]", "")
                .replaceAll("_{2,}", "_")
                .replaceAll("\\.$", "")
                .toLowerCase();
    }

    @Nullable
    ResourceLocation getRegistryName();

    default @Nonnull ResourceLocation toResourceLocation(@Nonnull String name) {
        return new ResourceLocation(Noscraft.MOD_ID, toRegistryName(name));
    }

    @SuppressWarnings("unchecked")
    default @Nonnull <T extends IForgeRegistryEntry.Impl & IRegistrable> String registerEntry(T entry, @Nonnull String name) {
        ResourceLocation location = toResourceLocation(name);
        entry.setRegistryName(location);
        return Objects.requireNonNull(entry.getRegistryName()).getResourcePath();
    }

    default @Nonnull <T extends Block & IRegistrable> String register(@Nonnull T block, @Nonnull String name) {
        name = registerEntry(block, name);
        block.setUnlocalizedName(name);
        return name;
    }

    default @Nonnull <T extends Item & IRegistrable> String register(@Nonnull T item, @Nonnull String name) {
        name = registerEntry(item, name);
        item.setUnlocalizedName(name);
        return name;
    }

}
