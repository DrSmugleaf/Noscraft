package drsmugleaf.noscraft.common.item.equipment.weapon;

import com.google.common.collect.ImmutableSet;
import drsmugleaf.noscraft.common.classes.Classes;
import drsmugleaf.noscraft.common.classes.IClassSpecific;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum WeaponTypes implements IClassSpecific {

    ADVENTURER_MAIN(Range.CLOSE, WeaponSlot.MAIN),
    ADVENTURER_SECONDARY(Range.RANGED, WeaponSlot.SECONDARY),
    SWORD(Range.CLOSE, WeaponSlot.MAIN),
    BOW(Range.RANGED, WeaponSlot.MAIN),
    STAFF(Range.MAGIC, WeaponSlot.MAIN),
    GAUNTLET(Range.CLOSE, WeaponSlot.MAIN),
    CROSSBOW(Range.RANGED, WeaponSlot.SECONDARY),
    DAGGER(Range.CLOSE, WeaponSlot.SECONDARY),
    GUN(Range.MAGIC, WeaponSlot.SECONDARY),
    TOKEN(Range.RANGED, WeaponSlot.SECONDARY);

    static {
        ADVENTURER_MAIN.CLASS = Classes.ADVENTURER.setOf();
        ADVENTURER_SECONDARY.CLASS = Classes.ADVENTURER.setOf();
        SWORD.CLASS = Classes.SWORDSMAN.setOf();
        BOW.CLASS = Classes.ARCHER.setOf();
        STAFF.CLASS = Classes.MAGE.setOf();
        GAUNTLET.CLASS = Classes.MARTIALARTIST.setOf();
        CROSSBOW.CLASS = Classes.SWORDSMAN.setOf();
        DAGGER.CLASS = Classes.ARCHER.setOf();
        GUN.CLASS = Classes.MAGE.setOf();
        TOKEN.CLASS = Classes.MARTIALARTIST.setOf();
    }

    private final @Nonnull Range RANGE;
    private final @Nonnull WeaponSlot SLOT;
    private @Nonnull ImmutableSet<Classes> CLASS;

    WeaponTypes(@Nonnull Range range, @Nonnull WeaponSlot slot) {
        RANGE = range;
        SLOT = slot;
    }

    public int getRange() {
        return RANGE.getRange();
    }

    @Nonnull
    public WeaponSlot getSlot() {
        return SLOT;
    }

    @Nonnull
    @Override
    public ImmutableSet<Classes> getClasses() {
        return CLASS;
    }

}
