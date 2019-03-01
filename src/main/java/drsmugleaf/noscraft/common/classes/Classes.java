package drsmugleaf.noscraft.common.classes;

import com.google.common.collect.ImmutableSet;
import drsmugleaf.noscraft.common.item.creativetab.*;
import drsmugleaf.noscraft.common.item.equipment.weapon.WeaponTypes;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;

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
    public static ImmutableSet<Classes> setOf(@Nonnull Classes... classes) {
        return ImmutableSet.copyOf(classes);
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
