package drsmugleaf.noscraft.common.item.equipment.weapon;

/**
 * Created by Josde on 01/02/2019
 **/
public enum Range {

    MELEE(1),
    RANGED(5),
    MAGIC(4);

    private final int RANGE;

    Range(int range) {
        RANGE = range;
    }

    public int getRange() {
        return RANGE;
    }

}
