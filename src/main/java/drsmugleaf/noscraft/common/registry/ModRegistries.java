package drsmugleaf.noscraft.common.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 21/02/2019
 */
public class ModRegistries {

    @Nonnull
    public static <T extends IForgeRegistryEntry<T>> RegistryBuilder<T> makeRegistry(@Nonnull ResourceLocation name, @Nonnull Class<T> type) {
        return new RegistryBuilder<T>().setName(name).setType(type);
    }

}
