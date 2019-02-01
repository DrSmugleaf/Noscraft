package drsmugleaf.noscraft.common.item;
import javax.annotation.Nonnull;
/**
 * Created by Josde on 31/01/2019
 */
public enum WeaponTypes {


    ADVENTURER_MAIN(Range.MELEE_RANGE),
    ADVENTURER_SECONDARY(Range.BOW_RANGE),
    SWORD(Range.MELEE_RANGE),
    BOW(Range.BOW_RANGE),
    STAFF(Range.MAGIC_RANGE),
    CROSSBOW(Range.BOW_RANGE),
    DAGGER(Range.MELEE_RANGE),
    GUN(Range.MAGIC_RANGE);

    private final @Nonnull Integer RANGE;
    WeaponTypes(@Nonnull Integer range) {
        RANGE = range;
    }

    public int getRange() {
        return RANGE;
    }
}
