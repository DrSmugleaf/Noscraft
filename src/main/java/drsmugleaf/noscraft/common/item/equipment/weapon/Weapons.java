package drsmugleaf.noscraft.common.item.equipment.weapon;

import drsmugleaf.noscraft.common.item.equipment.Effects;
import drsmugleaf.noscraft.common.item.equipment.Optimization;
import drsmugleaf.noscraft.common.item.equipment.Rarity;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Weapons {

    ADVENTURER_MAIN_1(24, 20, 4, 70, 1, WeaponTypes.ADVENTURER_MAIN, Effects.NONE),
    ADVENTURER_SECONDARY_1(23, 21, 2, 70, 1, WeaponTypes.ADVENTURER_SECONDARY, Effects.NONE),
    SWORDSMAN_MAIN_15(95, 100, 6, 50, 15, WeaponTypes.SWORD, Effects.NONE),
    SWORDSMAN_SECONDARY_15(93, 97, 2, 70, 15, WeaponTypes.CROSSBOW, Effects.NONE),
    ARCHER_MAIN_15(95, 100, 2, 70, 15, WeaponTypes.BOW, Effects.NONE),
    ARCHER_SECONDARY_15(78, 104, 5, 150, 15, WeaponTypes.DAGGER, Effects.NONE),
    MAGE_MAIN_15(96, 70, 0, 0, 15, WeaponTypes.STAFF, Effects.NONE),
    MAGE_SECONDARY_15(91, 93, 0, 0, 15, WeaponTypes.GUN, Effects.NONE);


    private final int DAMAGE;
    private final int HITRATE;
    private final int CRITCHANCE;
    private final int CRITMULTIPLIER;
    private final int LEVEL;
    private final @Nonnull WeaponTypes TYPE;
    private final @Nonnull Rarity RARITY;
    private final @Nonnull Optimization OPTIMIZATION;
    private final @Nonnull Effects[] EFFECT;

    Weapons(
            int damage,
            int hitRate,
            int critChance,
            int critMultiplier,
            int level,
            @Nonnull WeaponTypes type,
            @Nonnull Effects... effect
    ) {
        DAMAGE = damage;
        HITRATE = hitRate;
        CRITCHANCE = critChance;
        CRITMULTIPLIER = critMultiplier;
        LEVEL = level;
        EFFECT = effect;
        TYPE = type;
        RARITY = Rarity.NONE;
        OPTIMIZATION = Optimization.OPTIMIZATION_0;
    }

    Weapons(int damage,
            int hitRate,
            int critChance,
            int critMultiplier,
            int level,
            @Nonnull WeaponTypes type,
            @Nonnull Rarity rarity,
            @Nonnull Optimization optimization,
            @Nonnull Effects... effect
    ) {
        DAMAGE = damage;
        HITRATE = hitRate;
        CRITCHANCE = critChance;
        CRITMULTIPLIER = critMultiplier;
        LEVEL = level;
        EFFECT = effect;
        TYPE = type;
        RARITY = rarity;
        OPTIMIZATION = optimization;
    }

    public int getDamage() {
        return (int) Math.floor((DAMAGE + 0.5 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
    }

    public int getHitRate() {
        return (int) Math.floor((HITRATE + 0.5 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
    }

    public int getCritChance() {
        return CRITCHANCE;
    }

    public int getCritMultiplier() {
        return CRITMULTIPLIER;
    }

    public int getLevel() {
        return LEVEL;
    }

    public @Nonnull Effects[] getEffects() {
        return EFFECT;
    }

    public @Nonnull WeaponTypes getType() {
        return TYPE;
    }

    public @Nonnull Rarity getRarity() {
        return RARITY;
    }

    public int getRange() {
        return TYPE.getRange();
    }

    public @Nonnull Optimization getOptimization() { return OPTIMIZATION;}

    public int getStatIncrease() {
        int rarityCapacity = (LEVEL / 5) + 1; //This being an int is intentional; the original game truncates decimals.
        return (rarityCapacity * RARITY.getStrengtheningFactor()) - RARITY.getStatDecrease();
    }

}



