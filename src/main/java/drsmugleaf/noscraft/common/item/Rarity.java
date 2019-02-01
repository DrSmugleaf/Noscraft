package drsmugleaf.noscraft.common.item;

import javax.annotation.Nonnull;

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

    private  @Nonnull Integer STRENGHTENING_FACTOR = 0;
    private  @Nonnull Integer STAT_DECREASE = 0;
    private final @Nonnull Integer RARITY_LEVEL;

    Rarity(Integer rarityLevel) {
        RARITY_LEVEL = rarityLevel;
        if (rarityLevel <= 0) {
            STAT_DECREASE = 10 * rarityLevel;
            STRENGHTENING_FACTOR = 0;
        } else if (rarityLevel > 0 && rarityLevel <= 5) {
            STRENGHTENING_FACTOR = rarityLevel;
        } else if (rarityLevel == 6) {
            STRENGHTENING_FACTOR = 7;
        } else if (rarityLevel == 7) {
            STRENGHTENING_FACTOR = 10;
        }
    }
    public int getRarityLevel() {return RARITY_LEVEL;}
    public int getStatDecrease() {return STAT_DECREASE;}
    public int getStrenghteningFactor() {return STRENGHTENING_FACTOR;}
}
