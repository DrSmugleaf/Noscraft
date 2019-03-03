package drsmugleaf.noscraft.util.parser.nostaleclub.parsers;

import drsmugleaf.noscraft.util.parser.nostaleclub.parsers.regex.ParsingMode;
import drsmugleaf.noscraft.util.parser.nostaleclub.sheet.CSVColumn;
import drsmugleaf.noscraft.util.parser.nostaleclub.sheet.CSVSheet;
import org.jsoup.nodes.Element;

import javax.annotation.Nonnull;
import java.util.Map;
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
                "(?m)(?<booleanhighBadEffectPrevention>Self >> High probability of preventing a bad effect)",
                "(?m)Self >> HP recovery is increased by (?<hpRecoveryIncrease>\\d+)",
                "(?m)Self >> (?<elementIncreased>\\w+) element is increased by (?<elementIncreasedAmount>\\d+)",
                "(?m)Self >> All defence powers are increased by (?<allDefenceIncrease>\\d+)",
                "(?m)Self >> Hit rate of ranged attacks is increased by (?<rangedHitrateIncrease>\\d+)",
                "(?m)Self >> Increases damage with a probability of (?<damageIncreaseProbability>\\d+)% by (?<damageIncreaseAmountPercentage>\\d+)%",
                "(?m)Self >> All element energies are increased by (?<allElementsIncrease>\\d+)",
                "(?m)Self >> Maximum MP is increased by (?<maxMPIncrease>\\d+)",
                "(?m)Self >> Ranged attack is increased by (?<rangedAttackIncrease>\\d+)",
                "(?m)Self >> Melee attack is increased by (?<meleeAttackIncrease>\\d+)",
                "(?m)Self >> Dodging of melee attacks is increased by (?<meleeAttacksDodge>\\d+)",
                "(?m)Self >> All attacks are increased by (?<allAttackIncrease>\\d+)",
                "(?m)Self >> All attacks are increased by (?<allAttackIncreasePercentage>\\d+)%",
                "(?m)Self >> Magic attack power is increased by (?<magicAttackIncrease>\\d+)",
                "(?m)Self >> Mana for using skills is decreased by (?<skillManaDecrease>\\d+)%. \\(Includes magic.\\)" + ColumnParser.NO_END_APPEND_MARKER,
                "(?m)Self >> MP recovery is increased by (?<MPRecoveryIncrease>\\d+)",
                "(?m)Self >> (?<booleannoImpactOnMorale>No impact on morale stat)",
                "(?m)Self >> Maximum HP is increased by (?<maximumHPIncrease>\\d+)",
                "(?m)Self >> Morale stat is increased by (?<moraleIncrease>\\d+)",
                "(?m)Self >> Dodging of ranged attacks is increased by (?<rangedAttackDodge>\\d+)",
                "(?m)Self >> (?<resistanceType>\\w+) resistance is increased by (?<resistanceAmount>\\d+)",
                "(?m)Self >> Magic defence is increased by (?<magicDefenceIncrease>\\d+)",
                "(?m)Self >> Chance of inflicting critical hits is increased by (?<criticalChanceIncrease>\\d+)%",
                "(?m)Self >> There is a (?<randomDefenceChance>\\d+)% chance that damage from (?<randomDefenceType>\\w+) attacks is reduced by (?<randomDefencePercentage>\\d+)%",
                "(?m)Self >> (?<attacksBelowType>\\w+) attacks below level \\(\\+(?<attacksBelowLevel>\\d+)\\) cause (?<attacksBelowPercentage>\\d+)% less damage",
                "(?m)(?<effectWhen>.+) >> There is a (?<effectChance>\\d+)% chance of causing \\[ (?<effectName>.+)]", // TODO: 02/03/2019 Take into account multiple effects
                "(?m)Self >> All elemental resistance is increased by (?<resistanceAll>\\d+)",
                "(?m)Self >> (?<elementDecreased>\\w+) element is decreased by (?<elementDecreasedAmount>\\d+)",
                "(?m)Self >> Dodge is increased by (?<dodgeIncrease>\\d+)",
                "(?m)Self >> Attack level is increased by (?<attackLevelIncrease>\\d+)",
                "(?m)Self >> Defence level is increased by (?<defenceLevelIncrease>\\d+)",
                "(?m)Self >> Damage is decreased by (?<damageDecreasePercentage>\\d+)%",
                "(?m)Self >> All defences are increased by (?<allDefencesIncreasePercentage>\\d+)%",
                "(?m)(?<booleanGeneratesResistanceToCertainEffect>Self >> Generates resistance to a certain effect)",
                "(?m)(?<booleanCertainMonsterGroupsMistakeYouAsAFriend>Self >> Certain monster groups mistake you as a friend)",
                "(?m)Self >> Probability to receive critical hits is decreased by (?<criticalHitsChanceDecreasePercentage>\\d+)%",
                "(?m)Self >> Increases damage against \\[ (?<selfIncreasedDamageAgainst>.+)] by (?<selfIncreasedDamageAgainstAmount>\\d+)",
                "(?m)Self >> Increases damage from critical hits by (?<criticalHitDamageIncreasePercentage>\\d+)%",
                "(?m)Self >> Reduces the enemy's elemental resistances by (?<enemyElementalResistanceDecrease>\\d+)",
                "(?m)Self >> Damage from critical hits is reduced by (?<criticalHitDamageDecreasePercentage>\\d+)%",
                "(?m)Attack >> There's a (?<attackLeechHPChance>\\d+)% chance of leeching (?<attackLeechHPAmount>\\d+) HP from the enemy",
                "(?m)Attack >> There's a (?<attackLeechMPChance>\\d+)% chance of leeching (?<attackLeechMPAmount>\\d+) MP from the enemy",
                "(?m)Attack >> There's a (?<attackLeechMPChance2>\\d+)% chance of leeching (?<attackLeechMPAmount2>\\d+) MP from your enemy",
                "(?m)Self >> Melee damage is decreased by (?<meleeDamageDecreasePercentage>\\d+)%",
                "(?m)Attack >> MP is reduced by (?<attackDecreaseMP>\\d+)",
                "(?m)Self >> Reduces the enemy's (?<enemyResistanceDecreaseType>\\w+) resistance by (?<enemyResistanceReducedAmount>\\d+)",
                "(?m)Attack >> Increases damage against \\[ (?<attackIncreasedDamageAgainst>.+)] by (?<attackIncreasedDamageAgainstAmount>\\d+)",
                "(?m)Self >> Concentration is increased by (?<concentrationIncreaseAmount>\\d+) during the magic attack",
                "(?m)Self >> (?<defenceIncreaseType>\\w+) defence is increased by (?<defenceIncreaseAmount>\\d+)",
                "(?m)Self >> Below level (?<belowLevelNeverBadGeneralEffectLevel>\\d+) there is a (?<belowLevelNeverBadGeneralEffectPercentage>\\d+)% chance of never getting a bad general effect",
                "(?m)Self >> Magic attack power is decreased by (?<magicAttackPowerDecrease>\\d+)",
                "(?m)Self >> Ranged damage is decreased by (?<rangeDamageDecreasePercentage>\\d+)%",
                "(?m)Self >> Magic damage is decreased by (?<magicDamageDecreasePercentage>\\d+)%",
                "(?m)Self >> Movement speed is increased by (?<movementSpeedIncreasePercentage>\\d+)%",
                "(?m)Self >> Movement speed is decreased by (?<movementSpeedDecrease>\\d+)",
                "(?m)Self >> Movement speed is increased by (?<movementSpeedIncrease>\\d+)",
                "(?m)Self >> Hit rate of all attacks is increased by (?<allAttacksHitrateIncreas>\\d+)",
                "(?m)Self >> Gather \\(Player Level\\*(?<nextAttackPointsLevelMultiplier>\\d+)\\) points for the next attack",
                "(?m)Self >> Restores (?<HPRestore>\\d+) HP",
                "(?m)Self >> Restores (?<MPRestore>\\d+) MP",
                "(?m)Self >> (?<HPRestorePercentage>\\d+)% HP is recovered",
                "(?m)Self >> (?<MPRestorePercentage>\\d+)% MP is recovered",
                "(?m)Self >> HP is reduced by (?<HPDecrease>\\d+)",
                "(?m)Self >> MP is reduced by (?<MPDecrease>\\d+)",
                "(?m)(?<booleanScrollSafe>Self >> The power of scroll is safe)",
                "(?m)(?<booleanScrollLowerSPProtection>Self >> Use Lower SP Protection Scroll)",
                "(?m)(?<booleanScrollHigherSPProtection>Self >> Use Higher SP Protection Scroll)",
                "(?m)Self >> Summons (?<summonAmount>\\d+) of \\[ (?<summonName>.+)] monsters. \\(Training dummy\\)" + ColumnParser.NO_END_APPEND_MARKER,
                "(?m)(?<booleanCancelEffectsForACertainGroup>Self >> Cancel effects for a certain group)",
                "(?m)Self >> You receive an additional (?<additionalMPReceived>\\d+) MP\\. \\(Cannot exceed (?<additionalMPReceivedMaximumPercentage>\\d+)% of your maximum MP.\\)" + ColumnParser.NO_END_APPEND_MARKER,
                "(?m)Self >> You receive an additional (?<additionalHPReceived>\\d+) HP\\. \\(Cannot exceed (?<additionalHPReceivedMaximumPercentage>\\d+)% of your maximum HP.\\)" + ColumnParser.NO_END_APPEND_MARKER,
                "(?m)Self >> (?<effectResistancePercentage>\\d+)% resistance to the effect: \\[ (?<effectResistanceName>.+)] and lower",
                "(?m)Self >> Increases Gold earned by (?<goldEarnedIncreasePercentage>\\d+)%",
                "(?m)Self >> Experience gain is increased by (?<experienceEarnedIncreasePercentage>\\d+)%",
                "(?m)Defense >> There is a (?<defenceAllyEffectChance>\\d+)% chance to apply \\[ (?<defenceAllyEffectName>.+)] to the alliance within (?<defenceAllyEffectRadius>\\d+) fields",
                "(?m)Self >> Increases PvP attack power by (?<pvpAttackPowerIncrease>\\d+)%",
                "(?m)Self >> The effectiveness of recovery items is increased by (?<recoveryItemsEffectivenessIncrease>\\d+)%",
                "(?m)Defense >> There is a (?<defenceBadEffectRemovalPercentage>\\d+)% probability to remove bad effects of level (?<defenceBadEffectRemovalLevel>\\d+) or lower",
                "(?m)Self >> Below level (?<badEffectRemovalLevel>\\d+) there is a (?<badEffectRemovalPercentage>\\d+)% chance of never getting a bad effect",
                "(?m)Self >> Provides a (?<damageIncreaseForMonstersAboveLevelPercentage>\\d+)% chance of increasing damage by (?<damageIncreaseForMonstersAboveLevelAmount>\\d+)% if the monster has a higher level than the character",
                "(?m)Self >> Your movement speed is increased by (?<movementSpeedIncreaseWhenHidden>\\d+) while you are hidden",
                "(?m)Self >> Defence power in PvP is increased by (?<pvpDefencePowerIncrease>\\d+)%",
                "(?m)Self >> There is a (?<skillUsedResetChance>\\d+)% chance to reset the cooldown of the skill used",
                "(?m)Self >> Skill cooldown is decreased by (?<skillCooldownDecreasePercentage>\\d+)%",
                "(?m)Self >> Concentration is increased by \\(Player Level\\*(?<concentrationIncreasePlayerLevelMultiplier>\\d+)\\) during the magic attack",
                "(?m)Self >> Hit rate of all attacks is increased by \\(Player Level\\*(?<hitrateIncreasePlayerLevelMultiplier>\\d+)\\)",
                "(?m)Self >> Increases fame received by (?<fameReceivedIncreasePercentage>\\d+)%",
                "(?m)Self >> Resists forced movement with a probability of (?<resistsForcedMovementChance>\\d+)%",
                "(?m)Self >> (?<defenceIncreasePlayerLevelMultiplierType>\\w+) defence is increased by \\(Player Level\\*(?<defenceIncreasePlayerLevelMultiplier>\\d+)\\)",
                "(?m)Self >> When you die, the amount of reputation you lose is reduced by (?<deathReputationLostDecreasePercentage>\\d+)%",
                "(?m)Self >> Increases champion experience received by (?<championExperienceIncreasePercentage>\\d+)%",
                "(?m)Self >> When attacking demons, your max\\. damage increases to (?<demonMaxDamageIncrease>\\d+)%",
                "(?m)Self >> When attacking angels, your max\\. damage increases to (?<angelMaxDamageIncrease>\\d+)%",
                "(?m)Self >> There's a (?<criticalDamageReceivedDecreaseChance>\\d+)% chance of the critical damage received being reduced by (?<criticalDamageReceivedDecreasePercentage>\\d+)%",
                "(?m)Self >> There's a (?<targetDefenceIgnoreChance>\\d+)% chance to ignore (?<targetDefenceIgnorePercentage>\\d+)% of the target's defence",
                "(?m)Self >> When you die, the amount of champion level experience you lose is reduced by (?<championExperienceLostOnDeathReducePercentage>\\d+)%",
                "(?m)Self >> There is a (?<effectRemoveChance>\\d+)% chance that \\[ (?<effectRemoveName>.+)] will be removed"
        };

        columns = appendLineEnd(columns);
        return CSVColumn.from(ParsingMode.TEXT, columns);
    }

    @Nonnull
    @Override
    public Map<Integer, String> parse(@Nonnull Map<Integer, String> columns, int start, @Nonnull Element input, CSVSheet csvSheet) {
        return super.parse(columns, start, input, csvSheet);
    }

}
