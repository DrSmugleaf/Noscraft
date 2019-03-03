package drsmugleaf.noscraft.util.parser.nostaleclub.parsers;

import drsmugleaf.noscraft.common.IRegistrable;
import drsmugleaf.noscraft.util.parser.nostaleclub.sheet.CSVColumn;
import org.jsoup.nodes.Element;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by DrSmugleaf on 03/03/2019
 */
public class GeneralParser extends ColumnParser {

    @Nonnull
    private static final Map<String, String> SLOT_MAP = new HashMap<>();

    @Nonnull
    private static final Map<String, String> CLASS_MAP = new HashMap<>();

    static {
        SLOT_MAP.put("e0", "main weapon");
        SLOT_MAP.put("e1", "clothes");
        SLOT_MAP.put("e2", "hats");
        SLOT_MAP.put("e3", "gloves");
        SLOT_MAP.put("e4", "shoes");
        SLOT_MAP.put("e5", "secondary weapon");
        SLOT_MAP.put("e6", "necklace");
        SLOT_MAP.put("e7", "ring");
        SLOT_MAP.put("e8", "armlet");
        SLOT_MAP.put("e9", "accessory");
        SLOT_MAP.put("e10", "fairy");
        SLOT_MAP.put("e11", "scroll");
        SLOT_MAP.put("e12", "specialist card");
        SLOT_MAP.put("e13", "nosmall dress");
        SLOT_MAP.put("e14", "nosmall hat");
        SLOT_MAP.put("e15", "nosmall weapon");
        // Trophy slot?

        CLASS_MAP.put("ADV", "adventurer");
        CLASS_MAP.put("WAR", "swordsman");
        CLASS_MAP.put("ARC", "archer");
        CLASS_MAP.put("MAG", "mage");
        CLASS_MAP.put("M.A", "martialartist");
    }

    private final int SLOT_ID;
    private final int CLASS_ID;
    private final int GOLD_PRICE_ID;
    private final int REPUTATION_PRICE_ID;
    private final int NAME_ID;
    private final int REGISTRY_NAME_ID;
    private final @Nonnull Set<String> namesSeen = new HashSet<>();

    public GeneralParser() {
        super(getGeneralColumns());
        SLOT_ID = getColumn("slot").getIndex();
        CLASS_ID = getColumn("classRequired").getIndex();
        GOLD_PRICE_ID = getColumn("goldPrice").getIndex();
        REPUTATION_PRICE_ID = getColumn("reputationPrice").getIndex();
        NAME_ID = getColumn("name").getIndex();
        REGISTRY_NAME_ID = getColumn("registryName").getIndex();
    }

    @Nonnull
    private static Set<CSVColumn> getGeneralColumns() {
        return CSVColumn.from(
                "(?m) \\[(?<id>\\d+)]\\[$",
                "(?m)<b>(?<name>.+)</b>$",
                "(?m)<b>(?<registryName>.+)</b>$",
                "(?m)Inventory: (?<inventory>.+)$",
                "(?m)<mainlight>\n" +
                "\\s*(?<category>.+) \\(\\d+:\\d+\\^\\d+\\)\n\\s*</mainlight></i>$",
                "(?m)<img width=\"\\d+\" height=\"\\d+\" src=\"\\./.+/(?<slot>e\\d+)\\.png\">$",
                "(?m)Visual change: (?<visualChange>\\d+)$",
                "(?m)<price>\n\\s*Price: (?<goldPrice>[\\d+ ]+) gold\n\\s*</price>$",
                "(?m)<price>\n\\s*Price: (?<reputationPrice>[\\d+ ]+) reputation\n\\s*</price>$",
                "(?m)Class: (?<classRequiredMODETEXT>.+)$",
                "(?m)Lvl: (?<itemLvl>\\d+)$",
                "(?m)Type: (?<attackType>.+)$",
                "(?m)DMG: (?<minimumDamage>\\d+)~\\d+$",
                "(?m)DMG: \\d+~(?<maximumDamage>\\d+)$",
                "(?m)Crit: (?<critChance>\\d+)%/\\d+%$",
                "(?m)Crit: \\d+%/(?<critDamage>\\d+)%$",
                "(?m)Hitrate: (?<hitrate>\\d+)$"
        );
    }

    @Nonnull
    private String[] doReplacements(@Nonnull String[] columns, int start) {
        int index = start + SLOT_ID;
        String slot = columns[index];
        columns[index] = SLOT_MAP.get(slot);

        index = start + CLASS_ID;
        String classes = columns[index];
        if (classes != null) {
            String[] classesArray = classes.split(", ");
            for (int i = 0; i < classesArray.length; i++) {
                classesArray[i] = CLASS_MAP.get(classesArray[i]);
            }
            columns[index] = String.join(", ", classesArray);
        }

        index = start + GOLD_PRICE_ID;
        columns[index] = removeSpaces(columns[index]);

        index = start + REPUTATION_PRICE_ID;
        columns[index] = removeSpaces(columns[index]);

        index = start + REGISTRY_NAME_ID;
        columns[index] = IRegistrable.toRegistryName(columns[start + NAME_ID]);
        if (!namesSeen.add(columns[index])) {
            String newName = resolveDuplicateName(namesSeen, columns[index]);
            columns[index] = newName;
            namesSeen.add(newName);
        }

        return columns;
    }

    @Nonnull
    @Override
    public String[] parse(@Nonnull String[] columns, int start, @Nonnull Element input) {
        columns = super.parse(columns, start, input);
        columns = doReplacements(columns, start);
        return columns;
    }

}
