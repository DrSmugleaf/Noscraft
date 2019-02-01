package drsmugleaf.noscraft.common.item;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Weapons {

    ADVENTURER_MAIN_1(24, 20, 4, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_MAIN, Rarity.NONE),
    ADVENTURER_SECONDARY_1(23, 21, 2, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_SECONDARY, Rarity.NONE);

    private final @Nonnull Integer DAMAGE;
    private final @Nonnull Integer HITRATE;
    private final @Nonnull Integer CRITCHANCE;
    private final @Nonnull Integer CRITMULTIPLIER;
    private final @Nonnull Integer LEVEL;
    private final @Nonnull Effects EFFECT;
    private final @Nonnull Integer RANGE;
    private final @Nonnull Rarity RARITY;

    Weapons(@Nonnull Integer damage,
            @Nonnull Integer hitRate,
            @Nonnull Integer critChance,
            @Nonnull Integer critMultiplier,
            @Nonnull Integer level,
            @Nonnull Effects effect,
            @Nonnull WeaponTypes type,
            @Nonnull Rarity rarity) {
        DAMAGE = damage;
        HITRATE = hitRate;
        CRITCHANCE = critChance;
        CRITMULTIPLIER = critMultiplier;
        LEVEL = level;
        EFFECT = effect;
        RARITY = rarity;
        RANGE = type.getRange();
    }
    public int getStatIncrease() {
        int rarityCapacity = (LEVEL / 5) + 1; //This being an int is intentional; the original game truncates decimals.
        return (rarityCapacity * RARITY.getStrenghteningFactor()) - RARITY.getStatDecrease();
    }
    public int getDamage() {
        return (int)Math.floor((DAMAGE - 0.5 * getStatIncrease()));
    }
    public int getHitRate() {
        return (int)Math.floor((HITRATE - 0.5 * getStatIncrease()));
    }
    public int getCritChance() {return CRITCHANCE;}
    public int getCritMultiplier() {return CRITMULTIPLIER;}
    public int getLevel() {return LEVEL;}
    public Effects getEffect() {return EFFECT;}
    public int getRange() {return RANGE;}



}



