package drsmugleaf.noscraft.common.classes;

import drsmugleaf.noscraft.common.IRegistrable;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Created by DrSmugleaf on 28/02/2019
 */
public interface IClassSpecific {

    @Nonnull
    Set<Classes> getClasses();

    @Nonnull
    default String getClassFolderName() {
        Set<Classes> classes = getClasses();
        if (classes.size() == 1) {
            return IRegistrable.toRegistryName(classes.iterator().next().name().toLowerCase());
        } else {
            return "all";
        }
    }

    default boolean canEquip(@Nonnull Classes clazz) {
        return getClasses().contains(clazz);
    }

    @Nonnull
    default CreativeTabs getClassCreativeTab() {
        Set<Classes> classes = getClasses();
        if (classes.isEmpty()) {
            return CreativeTabs.MISC;
        } else if (classes.size() == 1) {
            return classes.iterator().next().getCreativeTab();
        } else {
            return CreativeTabs.MISC;
        }
    }

}
