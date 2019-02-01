package drsmugleaf.noscraft.common.item.equipment;

/**
 * Created by Josde on 01/02/2019
 **/
public enum Rarity {

    DAMAGED(-2),
    LOW_LEVEL(-1),
    NONE(0),
    USEFUL(1),
    GOOD(2),
    HIGH_QUALITY(3),
    EXCELLENT(4),
    ANCIENT(5),
    MYSTERIOUS(6),
    LEGENDARY(7);

    private final int STAT_DECREASE;
    private final int STRENGHTENING_FACTOR;
    private final int RARITY_LEVEL;

    Rarity(int rarityLevel) {
        RARITY_LEVEL = rarityLevel;

        if (rarityLevel > 7) {
            STAT_DECREASE = 0;
            STRENGHTENING_FACTOR = 10;
        } else if (rarityLevel > 6) {
            STAT_DECREASE = 0;
            STRENGHTENING_FACTOR = 7;
        } else if (rarityLevel > 0) {
            STAT_DECREASE = 0;
            STRENGHTENING_FACTOR = rarityLevel;
        } else {
            STAT_DECREASE = 10 * rarityLevel;
            STRENGHTENING_FACTOR = 0;
        }
    }

    public int getStatDecrease() {
        return STAT_DECREASE;
    }

    public int getRarityLevel() {
        return RARITY_LEVEL;
    }

    public int getStrengtheningFactor() {
        return STRENGHTENING_FACTOR;
    }

}
