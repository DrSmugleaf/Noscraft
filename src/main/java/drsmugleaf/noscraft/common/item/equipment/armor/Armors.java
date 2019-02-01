package drsmugleaf.noscraft.common.item.equipment.armor;

import drsmugleaf.noscraft.common.item.equipment.Effects;
import drsmugleaf.noscraft.common.item.equipment.Optimization;
import drsmugleaf.noscraft.common.item.equipment.Rarity;
import drsmugleaf.noscraft.common.item.equipment.Optimization;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Armors {

    ADVENTURER_1(12, 14, 12, 24, 1, ArmorTypes.ADVENTURER_ARMOR, Effects.NONE);

    private final @Nonnull Integer MELEE_DEF;
    private final @Nonnull Integer RANGE_DEF;
    private final @Nonnull Integer MAGIC_DEF;
    private final @Nonnull Integer DODGE;
    private final @Nonnull Integer LEVEL;
    private final @Nonnull Effects[] EFFECT;
    private final @Nonnull ArmorTypes TYPE;
    private final @Nonnull Rarity RARITY;
    private final @Nonnull Optimization OPTIMIZATION;

    Armors(
            @Nonnull Integer meleeDef,
            @Nonnull Integer rangeDef,
            @Nonnull Integer magicDef,
            @Nonnull Integer dodge,
            @Nonnull Integer level,
            @Nonnull ArmorTypes type,
            @Nonnull Effects... effect
    ) {
        MELEE_DEF = meleeDef;
        RANGE_DEF = rangeDef;
        MAGIC_DEF = magicDef;
        DODGE = dodge;
        LEVEL = level;
        EFFECT = effect;
        TYPE = type;
        RARITY = Rarity.NONE;
        OPTIMIZATION = Optimization.OPTIMIZATION_0;
    }

    Armors(
            @Nonnull Integer meleeDef,
            @Nonnull Integer rangeDef,
            @Nonnull Integer magicDef,
            @Nonnull Integer dodge,
            @Nonnull Integer level,
            @Nonnull ArmorTypes type,
            @Nonnull Rarity rarity,
            @Nonnull Optimization optimization,
            @Nonnull Effects... effect
    ) {
        MELEE_DEF = meleeDef;
        RANGE_DEF = rangeDef;
        MAGIC_DEF = magicDef;
        DODGE = dodge;
        LEVEL = level;
        EFFECT = effect;
        TYPE = type;
        RARITY = rarity;
        OPTIMIZATION = optimization;
    }

    public int getMeleeDef() {
        return (int) Math.floor((MELEE_DEF + 0.25 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
    }

    public int getRangeDef() {
        return (int) Math.floor((RANGE_DEF + 0.25 * getStatIncrease())* OPTIMIZATION.getExtraStats());
    }

    public int getMagicDef() {
        return (int) Math.floor((MAGIC_DEF + 0.25 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
    }

    public int getDodge() {
        return (int) Math.floor((DODGE + 0.25 * getStatIncrease()) * OPTIMIZATION.getExtraStats());
    }

    public int getLevel() {
        return LEVEL;
    }

    public @Nonnull Effects[] getEffects() {
        return EFFECT;
    }

    public @Nonnull ArmorTypes getType() {
        return TYPE;
    }

    public @Nonnull Rarity getRarity() {
        return RARITY;
    }

    public @Nonnull Optimization getOptimization() { return OPTIMIZATION;}

    public int getStatIncrease() {
        int rarityCapacity = (LEVEL / 5) + 1; // This being an int is intentional; the original game truncates decimals.
        return (rarityCapacity * RARITY.getStrengtheningFactor()) - RARITY.getStatDecrease();
    }

}
