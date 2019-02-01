package drsmugleaf.noscraft.common.item.equipment.armor;

import drsmugleaf.noscraft.common.item.equipment.Effects;
import drsmugleaf.noscraft.common.item.equipment.Rarity;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Armors {

    ADVENTURER_1(12, 14, 12, 24, 1, Effects.NONE, ArmorTypes.ADVENTURER_ARMOR, Rarity.NONE);

    private final @Nonnull Integer MELEE_DEF;
    private final @Nonnull Integer RANGE_DEF;
    private final @Nonnull Integer MAGIC_DEF;
    private final @Nonnull Integer DODGE;
    private final @Nonnull Integer LEVEL;
    private final @Nonnull Effects EFFECT;
    private final @Nonnull ArmorTypes TYPE;
    private final @Nonnull Rarity RARITY;

    Armors(
            @Nonnull Integer meleeDef,
            @Nonnull Integer rangeDef,
            @Nonnull Integer magicDef,
            @Nonnull Integer dodge,
            @Nonnull Integer level,
            @Nonnull Effects effect,
            @Nonnull ArmorTypes type,
            @Nonnull Rarity rarity
    ) {
        MELEE_DEF = meleeDef;
        RANGE_DEF = rangeDef;
        MAGIC_DEF = magicDef;
        DODGE = dodge;
        LEVEL = level;
        EFFECT = effect;
        TYPE = type;
        RARITY = rarity;
    }

    public int getMeleeDef() {
        return (int) Math.floor(MELEE_DEF - 0.25 * getStatIncrease());
    }

    public int getRangeDef() {
        return (int) Math.floor(RANGE_DEF - 0.25 * getStatIncrease());
    }

    public int getMagicDef() {
        return (int) Math.floor(MAGIC_DEF - 0.25 * getStatIncrease());
    }

    public int getDodge() {
        return (int) Math.floor(DODGE - 0.25 * getStatIncrease());
    }

    public int getLevel() {
        return LEVEL;
    }

    public @Nonnull Effects getEffect() {
        return EFFECT;
    }

    public @Nonnull ArmorTypes getType() {
        return TYPE;
    }

    public @Nonnull Rarity getRarity() {
        return RARITY;
    }

    public int getStatIncrease() {
        int rarityCapacity = (LEVEL / 5) + 1; // This being an int is intentional; the original game truncates decimals.
        return (rarityCapacity * RARITY.getStrengtheningFactor()) - RARITY.getStatDecrease();
    }

}
