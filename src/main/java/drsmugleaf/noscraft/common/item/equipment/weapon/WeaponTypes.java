package drsmugleaf.noscraft.common.item.equipment.weapon;
import javax.annotation.Nonnull;
/**
 * Created by Josde on 31/01/2019
 */
public enum WeaponTypes {

    ADVENTURER_MAIN(Range.MELEE, WeaponSlot.MAIN),
    ADVENTURER_SECONDARY(Range.RANGED, WeaponSlot.SECONDARY),
    SWORD(Range.MELEE, WeaponSlot.MAIN),
    BOW(Range.RANGED, WeaponSlot.MAIN),
    STAFF(Range.MAGIC, WeaponSlot.MAIN),
    GAUNTLET(Range.MELEE, WeaponSlot.MAIN),
    CROSSBOW(Range.RANGED, WeaponSlot.SECONDARY),
    DAGGER(Range.MELEE, WeaponSlot.SECONDARY),
    GUN(Range.MAGIC, WeaponSlot.SECONDARY),
    TOKEN(Range.RANGED, WeaponSlot.SECONDARY);

    private final @Nonnull Range RANGE;
    private final @Nonnull WeaponSlot SLOT;

    WeaponTypes(@Nonnull Range range, @Nonnull WeaponSlot slot) {
        RANGE = range;
        SLOT = slot;
    }

    public int getRange() {
        return RANGE.getRange();
    }

    public @Nonnull WeaponSlot getSlot() {
        return SLOT;
    }

}
