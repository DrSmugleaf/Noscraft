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
import java.util.Map;

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

    private SkillMod(
            int id,
            @Nonnull String name,
            int level,
            @Nonnull Elements element,
            @Nonnull String description,
            @Nonnull String skillEffect,
            @Nonnull Integer cp,
            double castTime,
            int range,
            double cooldown,
            @Nonnull Target target,
            int mp,
            int damageBonus,
            int elementBonus,
            @Nullable ITransformation transformation
    ) {
        ID = id;
        NAME = name;
        LEVEL = level;
        ELEMENT = element;
        DESCRIPTION = description;
        SKILL_EFFECTS = skillEffect;
        CP = cp;
        CAST_TIME = castTime;
        RANGE = range;
        COOLDOWN = cooldown;
        TARGET = target;
        MP = mp;
        DAMAGE_BONUS = damageBonus;
        ELEMENT_BONUS = elementBonus;
        TRANSFORMATION = transformation;

        registerEntry(this, name);

        TEXTURE = new ResourceLocation(Noscraft.MOD_ID, getLayer0Path());

        IEffect effect = ModEffects.getEffect(NAME);
        if (effect == null) {
            throw new IllegalArgumentException("No effect name found for " + NAME);
        }
        EFFECT = effect;
    }

    @Nonnull
    public static SkillMod fromCSV(int id, @Nullable ITransformation transformation, @Nonnull Map<String, String> line) {
        String description = line.get("description");
        String cp = line.get("cp");

        return new SkillMod(
                id,
                line.get("name"),
                Integer.valueOf(line.get("lvl")),
                Elements.from(line.get("element")),
                description != null ? description : "",
                line.get("skill_effects"),
                cp != null ? Integer.valueOf(cp) : 0,
                Double.valueOf(line.get("cast_time")),
                Integer.valueOf(line.get("range")),
                Double.valueOf(line.get("cooldown")),
                Target.from(line.get("target")),
                Integer.valueOf(line.get("mp")),
                Integer.valueOf(line.get("damage_bonus")),
                Integer.valueOf(line.get("element_bonus")),
                transformation
        );
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
