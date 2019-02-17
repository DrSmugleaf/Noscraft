package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;
import drsmugleaf.noscraft.common.element.Elements;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class GasShell extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 10;
    }

    @Nonnull
    @Override
    public Elements getElement() {
        return Elements.NONE;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "";
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return "100% chance to cast gas poison: [Chance of receiving critical hit 50%, defense level -2 for 20 seconds, 50% chance to cast 2nd gas poison: (Disables attacks for 5 seconds)]";
    }

    @Override
    public double getCastTime() {
        return 0.5;
    }

    @Override
    public double getCooldown() {
        return 60;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.ALL_IN_1_CELL;
    }

    @Override
    public int getMPCost() {
        return 90;
    }

    @Override
    public int getDamageBonus() {
        return 0;
    }

    @Override
    public int getElementBonus() {
        return 0;
    }

}
