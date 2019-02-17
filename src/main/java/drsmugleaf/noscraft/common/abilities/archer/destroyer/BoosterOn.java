package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;
import drsmugleaf.noscraft.common.element.Elements;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 15/02/2019
 */
public class BoosterOn extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 6;
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
        return "100% chance to cast booster on: (movement speed +3 for 42 seconds)";
    }

    @Override
    public double getCastTime() {
        return 0.2;
    }

    @Override
    public int getDistance() {
        return 0;
    }

    @Override
    public double getCooldown() {
        return 55;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.SELF;
    }

    @Override
    public int getMPCost() {
        return 180;
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
