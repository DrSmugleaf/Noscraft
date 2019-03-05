package drsmugleaf.noscraft.common.classes;

import com.google.common.collect.ImmutableSet;
import drsmugleaf.noscraft.common.IRegistrable;
import drsmugleaf.noscraft.common.element.Elements;
import drsmugleaf.noscraft.common.item.equipment.weapon.WeaponSlot;
import drsmugleaf.noscraft.common.skills.ISkill;
import drsmugleaf.noscraft.common.skills.ModSkills;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by DrSmugleaf on 31/01/2019
 */
public enum SpecialistCards implements ITransformation {

    WARRIOR("The Warrior", 36, 20, Elements.FIRE, WeaponSlot.MAIN, Classes.SWORDSMAN),
    NINJA("The Ninja", 46, 35, Elements.WATER, WeaponSlot.MAIN, Classes.SWORDSMAN),
    CRUSADER("Crusader", 55, 50, Elements.LIGHT, WeaponSlot.SECONDARY, Classes.SWORDSMAN),
    BERSERKER("The Berserker", 65, 55, Elements.DARKNESS, WeaponSlot.MAIN, Classes.SWORDSMAN),
    GLADIATOR("Gladiator", 75, 60, Elements.FIRE, WeaponSlot.MAIN, Classes.SWORDSMAN),
    BATTLE_MONK("Battle Monk", 80, 70, Elements.WATER, WeaponSlot.MAIN, Classes.SWORDSMAN),
    DEATH_REAPER("Death Reaper", 80, 70, Elements.DARKNESS, WeaponSlot.MAIN, Classes.SWORDSMAN),
    RENEGADE("Renegade", 70, 70, Elements.LIGHT, WeaponSlot.MAIN, Classes.SWORDSMAN), // TODO: Switch to darkness

    RANGER("The Ranger", 36, 20, Elements.WATER, WeaponSlot.MAIN, Classes.ARCHER),
    ASSASSIN("The Assassin", 46, 35, Elements.DARKNESS, WeaponSlot.SECONDARY, Classes.ARCHER),
    DESTROYER("The Destroyer", 55, 50, Elements.FIRE, WeaponSlot.MAIN, Classes.ARCHER),
    WILDKEEPER("The Wild Keeper", 65, 55, Elements.LIGHT, WeaponSlot.MAIN, Classes.ARCHER),
    CANNONNER("Fire Cannoneer", 75, 60, Elements.FIRE, WeaponSlot.MAIN, Classes.ARCHER),
    SCOUT("Scout", 80, 70, Elements.WATER, WeaponSlot.MAIN, Classes.ARCHER),
    DEMON_HUNTER("Demon Hunter", 80, 70, Elements.DARKNESS, WeaponSlot.MAIN, Classes.ARCHER),
    AVENGING_ANGEL("Avenging Angel", 70, 70, Elements.LIGHT, WeaponSlot.SECONDARY, Classes.ARCHER),

    RED_MAGICIAN("The Red Magician", 36, 20, Elements.FIRE, WeaponSlot.MAIN, Classes.MAGE),
    HOLY_MAGE("The Holy Mage", 46, 35, Elements.LIGHT, WeaponSlot.MAIN, Classes.MAGE),
    BLUE_MAGICIAN("Blue Magician", 55, 50, Elements.WATER, WeaponSlot.MAIN, Classes.MAGE),
    DARK_GUNNER("The Dark Gunner", 65, 55, Elements.DARKNESS, WeaponSlot.SECONDARY, Classes.MAGE),
    VOLCANO("Volcano", 75, 60, Elements.FIRE, WeaponSlot.MAIN, Classes.MAGE),
    TIDE_LORD("Tide Lord", 80, 70, Elements.WATER, WeaponSlot.MAIN, Classes.MAGE),
    SEER("Seer", 80, 70, Elements.DARKNESS, WeaponSlot.MAIN, Classes.MAGE),
    ARCHMAGE("Archmage", 70, 70, Elements.LIGHT, WeaponSlot.MAIN, Classes.MAGE),

    DRACONIC_FIST("Draconic Fist", 81, 20, Elements.FIRE, WeaponSlot.MAIN, Classes.MARTIALARTIST),
//    MYSTIC_ARTIST("Mystic Artist", 81, 20, Elements.WATER, WeaponSlot.MAIN, Classes.MARTIALARTIST), // TODO: 21/02/2019 Research this

    PYJAMA("The Pyjama", 27, 10, Elements.NONE, WeaponSlot.MAIN, Classes.values()),
    JAJAMARU("The Jajamaru", 45, 38, Elements.FIRE, WeaponSlot.MAIN, Classes.values()),
    CHICKEN("The Chicken", 0, 1, Elements.NONE, WeaponSlot.MAIN, Classes.values()),
    PIRATE("The Pirate", 0, 10, Elements.NONE, WeaponSlot.MAIN, Classes.values());

    private final @Nonnull String NAME;
    private final int LVL_OBTAINED;
    private final int JOB_LEVEL_REQUIRED;
    private final @Nonnull Elements ELEMENT;
    private final @Nonnull WeaponSlot WEAPON;
    private final @Nonnull Set<ISkill> SKILLS;
    private final @Nonnull ImmutableSet<Classes> CLASSES;

    SpecialistCards(
            @Nonnull String name,
            int lvlObtained,
            int jobLevelRequired,
            @Nonnull Elements element,
            @Nonnull WeaponSlot weapon,
            @Nonnull Classes... classes) {
        NAME = name;
        LVL_OBTAINED = lvlObtained;
        JOB_LEVEL_REQUIRED = jobLevelRequired;
        ELEMENT = element;
        WEAPON = weapon;
        SKILLS = ModSkills.getSPSkills(this);
        CLASSES = Classes.setOf(classes);
    }

    @Nonnull
    @Override
    public String getName() {
        return NAME;
    }

    @Nonnull
    @Override
    public String getFileName() {
        return IRegistrable.toRegistryName(name());
    }

    public int getLvlObtained() {
        return LVL_OBTAINED;
    }

    public int getJobLevelRequired() {
        return JOB_LEVEL_REQUIRED;
    }

    public @Nonnull Elements getElement() {
        return ELEMENT;
    }

    public @Nonnull WeaponSlot getWeaponSlot() {
        return WEAPON;
    }

    @Override
    public @Nonnull Set<ISkill> getSkills() {
        return new HashSet<>(SKILLS);
    }

    @Nonnull
    @Override
    public ImmutableSet<Classes> getClasses() {
        return CLASSES;
    }

}
