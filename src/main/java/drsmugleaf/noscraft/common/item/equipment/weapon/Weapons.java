package drsmugleaf.noscraft.common.item.equipment.weapon;

import drsmugleaf.noscraft.common.item.equipment.Effects;
import drsmugleaf.noscraft.common.item.equipment.Rarity;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Weapons {

    ADVENTURER_MAIN_1(24, 20, 4, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_MAIN, Rarity.NONE),
    ADVENTURER_SECONDARY_1(23, 21, 2, 70, 1, Effects.NONE, WeaponTypes.ADVENTURER_SECONDARY, Rarity.NONE);

    private final int DAMAGE;
    private final int HITRATE;
    private final int CRITCHANCE;
    private final int CRITMULTIPLIER;
    private final int LEVEL;
    private final @Nonnull Effects EFFECT;
    private final @Nonnull WeaponTypes TYPE;
    private final @Nonnull Rarity RARITY;

    Weapons(
            int damage,
            int hitRate,
            int critChance,
            int critMultiplier,
            int level,
            @Nonnull Effects effect,
            @Nonnull WeaponTypes type,
            @Nonnull Rarity rarity
    ) {
        DAMAGE = damage;
        HITRATE = hitRate;
        CRITCHANCE = critChance;
        CRITMULTIPLIER = critMultiplier;
        LEVEL = level;
        EFFECT = effect;
        TYPE = type;
        RARITY = rarity;
    }

    public int getDamage() {
        return (int) Math.floor(DAMAGE - 0.5 * getStatIncrease());
    }

    public int getHitRate() {
        return (int) Math.floor(HITRATE - 0.5 * getStatIncrease());
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

    public @Nonnull Effects getEffect() {
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

    public int getStatIncrease() {
        int rarityCapacity = (LEVEL / 5) + 1; //This being an int is intentional; the original game truncates decimals.
        return (rarityCapacity * RARITY.getStrengtheningFactor()) - RARITY.getStatDecrease();
    }

}



