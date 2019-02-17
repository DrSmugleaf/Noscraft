package drsmugleaf.noscraft.common.item.equipment.weapon;
import javax.annotation.Nonnull;
/**
 * Created by Josde on 31/01/2019
 */
public enum WeaponTypes {

    ADVENTURER_MAIN(Range.MELEE, WeaponSlot.MAIN, WeaponClass.ADVENTURER),
    ADVENTURER_SECONDARY(Range.RANGED, WeaponSlot.SECONDARY, WeaponClass.ADVENTURER),
    SWORD(Range.MELEE, WeaponSlot.MAIN, WeaponClass.SWORDSMAN),
    BOW(Range.RANGED, WeaponSlot.MAIN, WeaponClass.ARCHER),
    STAFF(Range.MAGIC, WeaponSlot.MAIN, WeaponClass.MAGE),
    GAUNTLET(Range.MELEE, WeaponSlot.MAIN, WeaponClass.MARTIALARTIST),
    CROSSBOW(Range.RANGED, WeaponSlot.SECONDARY, WeaponClass.SWORDSMAN),
    DAGGER(Range.MELEE, WeaponSlot.SECONDARY, WeaponClass.ARCHER),
    GUN(Range.MAGIC, WeaponSlot.SECONDARY, WeaponClass.MAGE),
    TOKEN(Range.RANGED, WeaponSlot.SECONDARY, WeaponClass.MARTIALARTIST);

    private final @Nonnull Range RANGE;
    private final @Nonnull WeaponSlot SLOT;
    private final @Nonnull WeaponClass CLASS;

    WeaponTypes(@Nonnull Range range, @Nonnull WeaponSlot slot, @Nonnull WeaponClass wClass) {
        RANGE = range;
        SLOT = slot;
        CLASS = wClass;
    }

    public int getRange() {
        return RANGE.getRange();
    }

    public @Nonnull WeaponSlot getSlot() {
        return SLOT;
    }

    public @Nonnull WeaponClass getWeaponClass() {
        return CLASS;
    }

}
