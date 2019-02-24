package drsmugleaf.noscraft.client.gui;

import drsmugleaf.noscraft.common.skills.ISkill;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 23/02/2019
 */
public class GuiSkillHotbarButton extends GuiSkillButton {

    public GuiSkillHotbarButton(@Nonnull GuiSkillMenu parentGui, @Nullable ISkill skill, int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(parentGui, skill, buttonId, x, y, widthIn, heightIn, buttonText);
    }

    public void setSkill(@Nonnull ISkill skill) {
        SKILL = skill;
    }

}
