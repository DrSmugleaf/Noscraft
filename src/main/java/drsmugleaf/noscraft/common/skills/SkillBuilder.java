package drsmugleaf.noscraft.common.skills;

import drsmugleaf.noscraft.common.classes.ITransformation;
import drsmugleaf.noscraft.common.element.Elements;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by DrSmugleaf on 20/02/2019
 */
public class SkillBuilder {

    @Nullable
    private Integer id;

    @Nullable
    private String name = null;

    @Nullable
    private Integer level = null;

    @Nullable
    private Elements element = null;

    @Nullable
    private String description = null;

    @Nullable
    private String skillEffects = null;

    @Nullable
    private Integer cp = null;

    @Nullable
    private Double castTime = null;

    @Nullable
    private Integer range = null;

    @Nullable
    private Double cooldown = null;

    @Nullable
    private Target target = null;

    @Nullable
    private Integer mp = null;

    @Nullable
    private Integer damageBonus = null;

    @Nullable
    private Integer elementBonus = null;

    @Nullable
    private String texturePath = null;

    @Nullable
    private ITransformation transformation = null;
    
    public SkillBuilder() {}

    @Nonnull
    public static SkillBuilder fromCSVLine(int id, @Nonnull Map<String, String> line, @Nonnull ITransformation transformation) {
        return new SkillBuilder()
                .setId(id)
                .setName(line.get("name"))
                .setLevel(Integer.valueOf(line.get("lvl")))
                .setRange(Integer.valueOf(line.get("range")))
                .setTarget(Target.from(line.get("target")))
                .setCastTime(Double.valueOf(line.get("cast_time")))
                .setCooldown(Double.valueOf(line.get("cooldown")))
                .setMp(Integer.valueOf(line.get("mp")))
                .setDamageBonus(Integer.valueOf(line.get("damage_bonus")))
                .setElementBonus(Integer.valueOf(line.get("element_bonus")))
                .setElement(Elements.from(line.get("element")))
                .setTransformation(transformation);
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    @Nonnull
    public SkillBuilder setId(@Nullable Integer id) {
        this.id = id;
        return this;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nonnull
    public SkillBuilder setName(@Nonnull String name) {
        this.name = name;
        return this;
    }

    @Nullable
    public Integer getLevel() {
        return level;
    }

    @Nonnull
    public SkillBuilder setLevel(@Nonnull Integer level) {
        this.level = level;
        return this;
    }

    @Nullable
    public Elements getElement() {
        return element;
    }

    @Nonnull
    public SkillBuilder setElement(@Nonnull Elements element) {
        this.element = element;
        return this;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nonnull
    public SkillBuilder setDescription(@Nonnull String description) {
        this.description = description;
        return this;
    }

    @Nullable
    public String getSkillEffects() {
        return skillEffects;
    }

    @Nonnull
    public SkillBuilder setSkillEffects(@Nonnull String skillEffects) {
        this.skillEffects = skillEffects;
        return this;
    }

    @Nullable
    public Integer getCp() {
        return cp;
    }

    @Nonnull
    public SkillBuilder setCp(@Nonnull Integer cp) {
        this.cp = cp;
        return this;
    }

    @Nullable
    public Double getCastTime() {
        return castTime;
    }

    @Nonnull
    public SkillBuilder setCastTime(@Nonnull Double castTime) {
        this.castTime = castTime;
        return this;
    }

    @Nullable
    public Integer getRange() {
        return range;
    }

    @Nonnull
    public SkillBuilder setRange(@Nonnull Integer range) {
        this.range = range;
        return this;
    }

    @Nullable
    public Double getCooldown() {
        return cooldown;
    }

    @Nonnull
    public SkillBuilder setCooldown(@Nonnull Double cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    @Nullable
    public Target getTarget() {
        return target;
    }

    @Nonnull
    public SkillBuilder setTarget(@Nonnull Target target) {
        this.target = target;
        return this;
    }

    @Nullable
    public Integer getMp() {
        return mp;
    }

    @Nonnull
    public SkillBuilder setMp(@Nonnull Integer mp) {
        this.mp = mp;
        return this;
    }

    @Nullable
    public Integer getDamageBonus() {
        return damageBonus;
    }

    @Nonnull
    public SkillBuilder setDamageBonus(@Nonnull Integer damageBonus) {
        this.damageBonus = damageBonus;
        return this;
    }

    @Nullable
    public Integer getElementBonus() {
        return elementBonus;
    }

    @Nonnull
    public SkillBuilder setElementBonus(@Nonnull Integer elementBonus) {
        this.elementBonus = elementBonus;
        return this;
    }

    @Nullable
    public String getTexturePath() {
        return texturePath;
    }

    @Nonnull
    public SkillBuilder setTexturePath(@Nonnull String texturePath) {
        this.texturePath = texturePath;
        return this;
    }

    @Nullable
    public ITransformation getTransformation() {
        return transformation;
    }

    @Nonnull
    public SkillBuilder setTransformation(@Nullable ITransformation transformation) {
        this.transformation = transformation;
        return this;
    }

}
