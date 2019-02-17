package drsmugleaf.noscraft.common.item.equipment;

/**
 * Created by Josde on 01/02/2019
 **/
public enum Rarity {

    DAMAGED(-2, 0, -20),
    LOW_LEVEL(-1, 0, -10),
    NONE(0, 0, 0),
    USEFUL(1, 1, 0),
    GOOD(2, 2, 0),
    HIGH_QUALITY(3, 3, 0),
    EXCELLENT(4, 4, 0),
    ANCIENT(5, 5, 0),
    MYSTERIOUS(6, 7, 0),
    LEGENDARY(7, 10, 0);

    private final int RARITY_LEVEL;
    private final int STRENGTHENING_FACTOR;
    private final int STAT_MODIFIER;

    Rarity(int rarityLevel, int strengtheningFactor, int statModifier) {
        RARITY_LEVEL = rarityLevel;
        STRENGTHENING_FACTOR = strengtheningFactor;
        STAT_MODIFIER = statModifier;
    }

    public int getRarityLevel() {
        return RARITY_LEVEL;
    }

    public int getStrengtheningFactor() {
        return STRENGTHENING_FACTOR;
    }

    public int getStatModifier() {
        return STAT_MODIFIER;
    }

}
