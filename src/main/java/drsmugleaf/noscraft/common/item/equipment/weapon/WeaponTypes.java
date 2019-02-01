package drsmugleaf.noscraft.common.item.equipment.weapon;
import javax.annotation.Nonnull;
/**
 * Created by Josde on 31/01/2019
 */
public enum WeaponTypes {

    ADVENTURER_MAIN(Range.MELEE),
    ADVENTURER_SECONDARY(Range.RANGED),
    SWORD(Range.MELEE),
    BOW(Range.RANGED),
    STAFF(Range.MAGIC),
    CROSSBOW(Range.RANGED),
    DAGGER(Range.MELEE),
    GUN(Range.MAGIC);

    private final @Nonnull Range RANGE;

    WeaponTypes(@Nonnull Range range) {
        RANGE = range;
    }

    public int getRange() {
        return RANGE.getRange();
    }

}
