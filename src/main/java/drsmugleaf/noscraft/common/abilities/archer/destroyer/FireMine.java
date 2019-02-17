package drsmugleaf.noscraft.common.abilities.archer.destroyer;

import drsmugleaf.noscraft.common.abilities.Target;
import drsmugleaf.noscraft.common.element.Elements;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 16/02/2019
 */
public class FireMine extends AbilityDestroyer {

    @Override
    public int getJobLevel() {
        return 18;
    }

    @Nonnull
    @Override
    public Elements getElement() {
        return Elements.NONE;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "Release 3 Fire Mines at your feet.The mines will find and chase its target on its own, though the target is selected at random within a certain area";
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return "";
    }

    @Override
    public double getCastTime() {
        return 1.0;
    }

    @Override
    public int getDistance() {
        return 0;
    }

    @Override
    public double getCooldown() {
        return 60;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return Target.SELECTED;
    }

    @Override
    public int getMPCost() {
        return 160;
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
