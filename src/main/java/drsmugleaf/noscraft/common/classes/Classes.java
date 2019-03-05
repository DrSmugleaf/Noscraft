package drsmugleaf.noscraft.common.classes;

import com.google.common.collect.ImmutableSet;
import drsmugleaf.noscraft.common.item.creativetab.*;
import drsmugleaf.noscraft.common.item.equipment.weapon.WeaponTypes;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * Created by Josde on 31/01/2019
 */
public enum Classes {

    ADVENTURER(CreativeTabAdventurer.INSTANCE),
    SWORDSMAN(CreativeTabSwordsman.INSTANCE),
    ARCHER(CreativeTabArcher.INSTANCE),
    MAGE(CreativeTabMage.INSTANCE),
    MARTIALARTIST(CreativeTabMartialArtist.INSTANCE);

    static {
        ADVENTURER.MAIN = WeaponTypes.ADVENTURER_MAIN;
        ADVENTURER.SECONDARY = WeaponTypes.ADVENTURER_SECONDARY;
        SWORDSMAN.MAIN = WeaponTypes.SWORD;
        SWORDSMAN.SECONDARY = WeaponTypes.CROSSBOW;
        ARCHER.MAIN = WeaponTypes.BOW;
        ARCHER.SECONDARY = WeaponTypes.DAGGER;
        MAGE.MAIN = WeaponTypes.STAFF;
        MAGE.SECONDARY = WeaponTypes.GUN;
        MARTIALARTIST.MAIN = WeaponTypes.GAUNTLET;
        MARTIALARTIST.SECONDARY = WeaponTypes.TOKEN;
    }

    private @Nonnull WeaponTypes MAIN;
    private @Nonnull WeaponTypes SECONDARY;
    private final @Nonnull CreativeTabs CREATIVE_TAB;

    Classes(@Nonnull CreativeTabs tab) {
        CREATIVE_TAB = tab;
    }

    @Nonnull
    public static Classes from(@Nonnull String name) {
        for (Classes clazz : values()) {
            if (clazz.name().equalsIgnoreCase(name)) {
                return clazz;
            }
        }

        throw new IllegalArgumentException("No class found with name " + name);
    }

    @Nonnull
    public static ImmutableSet<Classes> setOf(@Nonnull Classes... classes) {
        return ImmutableSet.copyOf(classes);
    }

    @Nonnull
    public static ImmutableSet<Classes> setOf(@Nonnull String... names) {
        return setOf(Arrays.stream(names).map(Classes::from).toArray(Classes[]::new));
    }

    @Nonnull
    public static ImmutableSet<Classes> setOf(@Nonnull String name) {
        return from(name).setOf();
    }

    @Nonnull
    public static ImmutableSet<Classes> all() {
        return setOf(values());
    }

    @Nonnull
    public ImmutableSet<Classes> setOf() {
        return setOf(this);
    }

    @Nonnull
    public WeaponTypes getMain() {
        return MAIN;
    }

    @Nonnull
    public WeaponTypes getSecondary() {
        return SECONDARY;
    }

    @Nonnull
    public CreativeTabs getCreativeTab() {
        return CREATIVE_TAB;
    }

}
