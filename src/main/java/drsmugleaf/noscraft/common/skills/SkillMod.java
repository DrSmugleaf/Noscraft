package drsmugleaf.noscraft.common.skills;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.classes.ITransformation;
import drsmugleaf.noscraft.common.element.Elements;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by DrSmugleaf on 17/02/2019
 */
public class SkillMod extends IForgeRegistryEntry.Impl<ISkill> implements ISkill {

    private boolean onCooldown = false;
    private final int ID;
    private final @Nonnull String NAME;
    private final int LEVEL;
    private final @Nonnull Elements ELEMENT;
    private final @Nonnull String DESCRIPTION;
    private final @Nonnull String SKILL_EFFECTS;
    private final int CP;
    private final double CAST_TIME;
    private final int RANGE;
    private final double COOLDOWN;
    private final @Nonnull Target TARGET;
    private final int MP;
    private final int DAMAGE_BONUS;
    private final int ELEMENT_BONUS;
    private final @Nullable ITransformation TRANSFORMATION;
    private final @Nonnull IEffect EFFECT;

    private final @Nonnull ResourceLocation TEXTURE;

    public SkillMod(@Nonnull SkillBuilder ability) {
        ID = ability.getId();
        NAME = ability.getName();
        LEVEL = ability.getLevel();
        ELEMENT = ability.getElement();
        DESCRIPTION = ability.getDescription();
        SKILL_EFFECTS = ability.getSkillEffects();
        CP = ability.getCp() != null ? ability.getCp() : 0;
        CAST_TIME = ability.getCastTime();
        RANGE = ability.getRange();
        COOLDOWN = ability.getCooldown();
        TARGET = ability.getTarget();
        MP = ability.getMp();
        DAMAGE_BONUS = ability.getDamageBonus();
        ELEMENT_BONUS = ability.getElementBonus();
        TRANSFORMATION = ability.getTransformation();

        registerEntry(this);

        TEXTURE = new ResourceLocation(Noscraft.MOD_ID, getLayer0Path());

        IEffect effect = ModEffects.getEffect(NAME);
        if (effect == null) {
            throw new IllegalArgumentException("No effect name found for " + NAME);
        }
        EFFECT = effect;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Nonnull
    @Override
    public String getName() {
        return NAME;
    }

    @Nonnull
    @Override
    public String getNameToRegister() {
        return NAME;
    }

    @Override
    public int getJobLevel() {
        return LEVEL;
    }

    @Nonnull
    @Override
    public Elements getElement() {
        return ELEMENT;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Nonnull
    @Override
    public String getSkillEffects() {
        return SKILL_EFFECTS;
    }

    @Override
    public int getCP() {
        return CP;
    }

    @Override
    public double getCastTime() {
        return CAST_TIME;
    }

    @Override
    public int getRange() {
        return RANGE;
    }

    @Override
    public double getCooldown() {
        return COOLDOWN;
    }

    @Nonnull
    @Override
    public Target getTarget() {
        return TARGET;
    }

    @Override
    public int getMPCost() {
        return MP;
    }

    @Override
    public int getDamageBonus() {
        return DAMAGE_BONUS;
    }

    @Override
    public int getElementBonus() {
        return ELEMENT_BONUS;
    }

    @Nullable
    @Override
    public ITransformation getTransformation() {
        return TRANSFORMATION;
    }

    @Nonnull
    @Override
    public String getLayer0Path() {
        String path = "skills/";

        if (TRANSFORMATION != null) {
            path +=
                    TRANSFORMATION.getClassFolderName() +
                    "/" +
                    TRANSFORMATION.getFileName() +
                    "/" +
                    getRegistryName().getResourcePath();
        }
        // TODO: 23/02/2019 Class skill paths

        return path;
    }

    @Nonnull
    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    @Override
    public boolean isOnCooldown() {
        return onCooldown;
    }

    @Override
    public void cast() {
        if (onCooldown) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        RayTraceResult result = player.rayTrace(50, mc.getTickLength());
        if (result == null) {
            return;
        }

        World world = player.world;
        BlockPos pos = result.getBlockPos();
        EFFECT.use(world, pos);

        onCooldown = true;
    }

}
