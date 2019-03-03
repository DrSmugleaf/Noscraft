package drsmugleaf.noscraft.util.parser.nostaleclub.parsers;

import drsmugleaf.noscraft.util.parser.nostaleclub.sheet.CSVColumn;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Created by DrSmugleaf on 02/03/2019
 */
public class EffectsParser extends ColumnParser {

    public EffectsParser() {
        super(getEffectsColumns());
    }

    @Nonnull
    private static Set<CSVColumn> getEffectsColumns() {
        String[] columns = new String[]{
                "(?<booleanhighBadEffectPrevention>Self >> High probability of preventing a bad effect)",
                "Self >> HP recovery is increased by (?<hpRecoveryIncrease>\\d+)",
                "Self >> (?<elementIncreased>\\w+) element is increased by (?<elementIncreasedAmount>\\d+)",
                "Self >> All defence powers are increased by (?<allDefenceIncrease>\\d+)",
                "Self >> Hit rate of ranged attacks is increased by (?<rangedHitrateIncrease>\\d+)",
                "Self >> Increases damage with a probability of (?<damageIncreaseProbability>\\d+)% by (?<damageIncreaseAmountPercentage>\\d+)%",
                "Self >> All element energies are increased by (?<allElementsIncrease>\\d+)",
                "Self >> Maximum MP is increased by (?<maxMPIncrease>\\d+)",
                "Self >> Ranged attack is increased by (?<rangedAttackIncrease>\\d+)",
                "Self >> Melee attack is increased by (?<meleeAttackIncrease>\\d+)",
                "Self >> Dodging of melee attacks is increased by (?<meleeAttacksDodge>\\d+)",
                "Self >> All attacks are increased by (?<allAttackIncrease>\\d+)",
                "Self >> All attacks are increased by (?<allAttackIncreasePercentage>\\d+)%",
                "Self >> Magic attack power is increased by (?<magicAttackIncrease>\\d+)",
                "Self >> Mana for using skills is decreased by (?<skillManaDecrease>\\d+)%. \\(Includes magic.\\)" + ColumnParser.NO_END_APPEND_MARKER,
                "Self >> MP recovery is increased by (?<MPRecoveryIncrease>\\d+)",
                "Self >> (?<booleannoImpactOnMorale>No impact on morale stat)",
                "Self >> Maximum HP is increased by (?<maximumHPIncrease>\\d+)",
                "Self >> Morale stat is increased by (?<moraleIncrease>\\d+)",
                "Self >> Dodging of ranged attacks is increased by (?<rangedAttackDodge>\\d+)",
                "Self >> (?<resistanceType>\\w+) resistance is increased by (?<resistanceAmount>\\d+)",
                "Self >> Magic defence is increased by (?<magicDefenceIncrease>\\d+)",
                "Self >> Chance of inflicting critical hits is increased by (?<criticalChanceIncrease>\\d+)%",
                "Self >> There is a (?<randomDefenceChance>\\d+)% chance that damage from (?<randomDefenceType>\\w+) attacks is reduced by (?<randomDefencePercentage>\\d+)%",
                "Self >> (?<attacksBelowType>\\w+) attacks below level \\(\\+(?<attacksBelowLevel>\\d+)\\) cause (?<attacksBelowPercentage>\\d+)% less damage",
                "(?<effectWhen>.+) >> There is a (?<effectChance>\\d+)% chance of causing \\[ (?<effectName>.+)]", // TODO: 02/03/2019 Take into account multiple effects
                "Self >> All elemental resistance is increased by (?<resistanceAll>\\d+)",
                "Self >> (?<elementDecreased>\\w+) element is decreased by (?<elementDecreasedAmount>\\d+)",
                "Self >> Dodge is increased by (?<dodgeIncrease>\\d+)",
                "Self >> Attack level is increased by (?<attackLevelIncrease>\\d+)",
                "Self >> Defence level is increased by (?<defenceLevelIncrease>\\d+)",
                "Self >> Damage is decreased by (?<damageDecreasePercentage>\\d+)%",
                "Self >> All defences are increased by (?<allDefencesIncreasePercentage>\\d+)%",
                "(?<booleanGeneratesResistanceToCertainEffect>Self >> Generates resistance to a certain effect)",
                "(?<booleanCertainMonsterGroupsMistakeYouAsAFriend>Self >> Certain monster groups mistake you as a friend)",
                "Self >> Probability to receive critical hits is decreased by (?<criticalHitsChanceDecreasePercentage>\\d+)%",
                "Self >> Increases damage against \\[ (?<selfIncreasedDamageAgainst>.+)] by (?<selfIncreasedDamageAgainstAmount>\\d+)",
                "Self >> Increases damage from critical hits by (?<criticalHitDamageIncreasePercentage>\\d+)%",
                "Self >> Reduces the enemy's elemental resistances by (?<enemyElementalResistanceDecrease>\\d+)",
                "Self >> Damage from critical hits is reduced by (?<criticalHitDamageDecreasePercentage>\\d+)%",
                "Attack >> There's a (?<attackLeechHPChance>\\d+)% chance of leeching (?<attackLeechHPAmount>\\d+) HP from the enemy",
                "Attack >> There's a (?<attackLeechMPChance>\\d+)% chance of leeching (?<attackLeechMPAmount>\\d+) MP from the enemy",
                "Attack >> There's a (?<attackLeechMPChance2>\\d+)% chance of leeching (?<attackLeechMPAmount2>\\d+) MP from your enemy",
                "Self >> Melee damage is decreased by (?<meleeDamageDecreasePercentage>\\d+)%",
                "Attack >> MP is reduced by (?<attackDecreaseMP>\\d+)",
                "Self >> Reduces the enemy's (?<enemyResistanceDecreaseType>\\w+) resistance by (?<enemyResistanceReducedAmount>\\d+)",
                "Attack >> Increases damage against \\[ (?<attackIncreasedDamageAgainst>.+)] by (?<attackIncreasedDamageAgainstAmount>\\d+)",
                "Self >> Concentration is increased by (?<concentrationIncreaseAmount>\\d+) during the magic attack",
                "Self >> (?<defenceIncreaseType>\\w+) defence is increased by (?<defenceIncreaseAmount>\\d+)",
                "Self >> Below level (?<belowLevelNeverBadGeneralEffectLevel>\\d+) there is a (?<belowLevelNeverBadGeneralEffectPercentage>\\d+)% chance of never getting a bad general effect",
                "Self >> Magic attack power is decreased by (?<magicAttackPowerDecrease>\\d+)",
                "Self >> Ranged damage is decreased by (?<rangeDamageDecreasePercentage>\\d+)%",
                "Self >> Magic damage is decreased by (?<magicDamageDecreasePercentage>\\d+)%",
                "Self >> Movement speed is increased by (?<movementSpeedIncreasePercentage>\\d+)%",
                "Self >> Movement speed is decreased by (?<movementSpeedDecrease>\\d+)",
                "Self >> Movement speed is increased by (?<movementSpeedIncrease>\\d+)",
                "Self >> Hit rate of all attacks is increased by (?<allAttacksHitrateIncreas>\\d+)",
                "Self >> Gather \\(Player Level\\*(?<nextAttackPointsLevelMultiplier>\\d+)\\) points for the next attack",
                "Self >> Restores (?<HPRestore>\\d+) HP",
                "Self >> Restores (?<MPRestore>\\d+) MP",
                "Self >> (?<HPRestorePercentage>\\d+)% HP is recovered",
                "Self >> (?<MPRestorePercentage>\\d+)% MP is recovered",
                "Self >> HP is reduced by (?<HPDecrease>\\d+)",
                "Self >> MP is reduced by (?<MPDecrease>\\d+)",
                "(?<booleanScrollSafe>Self >> The power of scroll is safe)",
                "(?<booleanScrollLowerSPProtection>Self >> Use Lower SP Protection Scroll)",
                "(?<booleanScrollHigherSPProtection>Self >> Use Higher SP Protection Scroll)",
                "Self >> Summons (?<summonAmount>\\d+) of \\[ (?<summonName>.+)] monsters. \\(Training dummy\\)" + ColumnParser.NO_END_APPEND_MARKER,
                "(?<booleanCancelEffectsForACertainGroup>Self >> Cancel effects for a certain group)",
                "Self >> You receive an additional (?<additionalMPReceived>\\d+) MP\\. \\(Cannot exceed (?<additionalMPReceivedMaximumPercentage>\\d+)% of your maximum MP.\\)" + ColumnParser.NO_END_APPEND_MARKER,
                "Self >> You receive an additional (?<additionalHPReceived>\\d+) HP\\. \\(Cannot exceed (?<additionalHPReceivedMaximumPercentage>\\d+)% of your maximum HP.\\)" + ColumnParser.NO_END_APPEND_MARKER,
                "Self >> (?<effectResistancePercentage>\\d+)% resistance to the effect: \\[ (?<effectResistanceName>.+)] and lower",
                "Self >> Increases Gold earned by (?<goldEarnedIncreasePercentage>\\d+)%",
                "Self >> Experience gain is increased by (?<experienceEarnedIncreasePercentage>\\d+)%",
                "Defense >> There is a (?<defenceAllyEffectChance>\\d+)% chance to apply \\[ (?<defenceAllyEffectName>.+)] to the alliance within (?<defenceAllyEffectRadius>\\d+) fields",
                "Self >> Increases PvP attack power by (?<pvpAttackPowerIncrease>\\d+)%",
                "Self >> The effectiveness of recovery items is increased by (?<recoveryItemsEffectivenessIncrease>\\d+)%",
                "Defense >> There is a (?<defenceBadEffectRemovalPercentage>\\d+)% probability to remove bad effects of level (?<defenceBadEffectRemovalLevel>\\d+) or lower",
                "Self >> Below level (?<badEffectRemovalLevel>\\d+) there is a (?<badEffectRemovalPercentage>\\d+)% chance of never getting a bad effect",
                "Self >> Provides a (?<damageIncreaseForMonstersAboveLevelPercentage>\\d+)% chance of increasing damage by (?<damageIncreaseForMonstersAboveLevelAmount>\\d+)% if the monster has a higher level than the character",
                "Self >> Your movement speed is increased by (?<movementSpeedIncreaseWhenHidden>\\d+) while you are hidden",
                "Self >> Defence power in PvP is increased by (?<pvpDefencePowerIncrease>\\d+)%",
                "Self >> There is a (?<skillUsedResetChance>\\d+)% chance to reset the cooldown of the skill used",
                "Self >> Skill cooldown is decreased by (?<skillCooldownDecreasePercentage>\\d+)%",
                "Self >> Concentration is increased by \\(Player Level\\*(?<concentrationIncreasePlayerLevelMultiplier>\\d+)\\) during the magic attack",
                "Self >> Hit rate of all attacks is increased by \\(Player Level\\*(?<hitrateIncreasePlayerLevelMultiplier>\\d+)\\)",
                "Self >> Increases fame received by (?<fameReceivedIncreasePercentage>\\d+)%",
                "Self >> Resists forced movement with a probability of (?<resistsForcedMovementChance>\\d+)%",
                "Self >> (?<defenceIncreasePlayerLevelMultiplierType>\\w+) defence is increased by \\(Player Level\\*(?<defenceIncreasePlayerLevelMultiplier>\\d+)\\)",
                "Self >> When you die, the amount of reputation you lose is reduced by (?<deathReputationLostDecreasePercentage>\\d+)%",
                "Self >> Increases champion experience received by (?<championExperienceIncreasePercentage>\\d+)%",
                "Self >> When attacking demons, your max\\. damage increases to (?<demonMaxDamageIncrease>\\d+)%",
                "Self >> When attacking angels, your max\\. damage increases to (?<angelMaxDamageIncrease>\\d+)%",
                "Self >> There's a (?<criticalDamageReceivedDecreaseChance>\\d+)% chance of the critical damage received being reduced by (?<criticalDamageReceivedDecreasePercentage>\\d+)%",
                "Self >> There's a (?<targetDefenceIgnoreChance>\\d+)% chance to ignore (?<targetDefenceIgnorePercentage>\\d+)% of the target's defence",
                "Self >> When you die, the amount of champion level experience you lose is reduced by (?<championExperienceLostOnDeathReducePercentage>\\d+)%",
                "Self >> There is a (?<effectRemoveChance>\\d+)% chance that \\[ (?<effectRemoveName>.+)] will be removed"
        };

        columns = appendLineEnd(columns);
        return CSVColumn.from(columns);
    }

}
