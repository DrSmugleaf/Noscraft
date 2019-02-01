package drsmugleaf.noscraft.common.item;

import javax.annotation.Nonnull;

/**
 * Created by Josde on 31/01/2019
 */
public enum Armors {

    ADVENTURER_1(12, 14, 12, 24, 1, Effects.NONE, ArmorTypes.ADVENTURER_ARMOR, Rarity.NONE);

    private final @Nonnull Integer MELEEDEF;
    private final @Nonnull Integer RANGEDEF;
    private final @Nonnull Integer MAGICDEF;
    private final @Nonnull Integer DODGE;
    private final @Nonnull Integer LEVEL;
    private final @Nonnull Effects EFFECT;
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
        MELEEDEF = meleeDef;
        RANGEDEF = rangeDef;
        MAGICDEF = magicDef;
        DODGE = dodge;
        LEVEL = level;
        EFFECT = effect;
        RARITY = rarity;
    }
    public int getLevel() {return LEVEL;}
    public Effects getEffect() {return EFFECT;}
    public Rarity getRarity() {return RARITY;}
    public int getStatIncrease() {
        int rarityCapacity = (LEVEL / 5) + 1; //This being an int is intentional; the original game truncates decimals.
        return (rarityCapacity * RARITY.getStrenghteningFactor()) - RARITY.getStatDecrease();
    }
    public int getMeleeDef() {
        return (int)Math.floor((MELEEDEF - 0.25 * getStatIncrease()));
    }
    public int getRangeDef() {
        return (int)Math.floor((RANGEDEF - 0.25 * getStatIncrease()));
    }
    public int getMagicDef() {
       return (int)Math.floor((MAGICDEF - 0.25 * getStatIncrease()));
    }
    public int getDodge() {
        return (int)Math.floor((MELEEDEF - 0.25 * getStatIncrease()));
    }


}
