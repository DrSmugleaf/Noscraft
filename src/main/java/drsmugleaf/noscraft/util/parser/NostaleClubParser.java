package drsmugleaf.noscraft.util.parser;

import com.opencsv.*;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import drsmugleaf.noscraft.common.IRegistrable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DrSmugleaf on 01/03/2019
 */
public class NostaleClubParser {

    @Nonnull
    private static final Logger LOG = LogManager.getLogger(NostaleClubParser.class.getSimpleName());

    @Nonnull
    private static final Pattern ID = Pattern.compile("\\[(?<id>\\d+)]\\[");

    @Nonnull
    private static final Pattern INVENTORY = Pattern.compile("Inventory: (?<inventory>.+)");

    @Nonnull
    private static final Pattern CATEGORY = Pattern.compile("(?m)(?<category>.+) \\(.+\\)$");

    @Nonnull
    private static final Pattern SLOT_IMAGE = Pattern.compile("/(?<slot>e\\d+)\\.png");

    @Nonnull
    private static final Map<String, String> SLOT_MAP = new HashMap<>();

    @Nonnull
    private static final Pattern VISUAL_CHANGE = Pattern.compile("Visual change: (?<change>\\d+)");

    @Nonnull
    private static final Pattern GOLD_PRICE = Pattern.compile("Price:(?<gold>\\d+)gold");

    @Nonnull
    private static final Pattern REPUTATION_PRICE = Pattern.compile("Price:(?<reputation>\\d+)reputation");

    @Nonnull
    private static final Pattern CLASS = Pattern.compile("(?m)Class: (?<classes>.+)$");

    @Nonnull
    private static final Map<String, String> CLASS_MAP = new HashMap<>();

    @Nonnull
    private static final Pattern LVL = Pattern.compile("(?m)Lvl: (?<lvl>\\d+)$");

    @Nonnull
    private static final Pattern TYPE = Pattern.compile("(?m)Type: (?<type>.+)$");

    @Nonnull
    private static final Pattern MINIMUM_DAMAGE = Pattern.compile("(?m)DMG: (?<minimum>\\d+)~\\d+$");

    @Nonnull
    private static final Pattern MAXIMUM_DAMAGE = Pattern.compile("(?m)DMG: \\d+~(?<maximum>\\d+)$");

    @Nonnull
    private static final Pattern CRIT_CHANCE = Pattern.compile("(?m)Crit: (?<chance>\\d+)%/\\d+%$");

    @Nonnull
    private static final Pattern CRIT_ADDITIONAL_DAMAGE = Pattern.compile("(?m)Crit: \\d+%/(?<damage>\\d+)%$");

    @Nonnull
    private static final Pattern HITRATE = Pattern.compile("(?m)Hitrate: (\\d+)$");

    static {
        SLOT_MAP.put("e0", "main weapon");
        SLOT_MAP.put("e1", "clothes");
        SLOT_MAP.put("e2", "head");
        SLOT_MAP.put("e3", "gloves");
        SLOT_MAP.put("e4", "shoes");
        SLOT_MAP.put("e5", "alt weapon");
        SLOT_MAP.put("e6", "necklace");
        SLOT_MAP.put("e7", "ring");
        SLOT_MAP.put("e8", "bracelet");
        SLOT_MAP.put("e9", "accessory");
        SLOT_MAP.put("e10", "fairy");
        SLOT_MAP.put("e11", "amulet");
        SLOT_MAP.put("e12", "s card");
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

    public static void main(String[] args) {
        String directory = "F:\\Downloaded Htmls\\NosTale";
        String filePath = "items.html";
        String outputPath = "F:\\Downloaded Htmls\\NosTale\\output";

        NostaleClubParser.parse(directory, filePath, outputPath);
    }

    @Nullable
    private static String findFirst(@Nonnull Pattern pattern, @Nonnull String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1).toLowerCase();
        }

        return null;
    }

    @Nullable
    private static Integer findFirstInt(@Nonnull Pattern pattern, @Nonnull String input) {
        String match = findFirst(pattern, input);
        if (match == null) {
            return null;
        }

        return Integer.valueOf(match);
    }

    public static void parse(@Nonnull String directory, @Nonnull String file, @Nonnull String outputPath) {
        file = directory + "/" + file;
        Document doc;
        try {
            doc = Jsoup.parse(new File(file), "UTF-8");
        } catch (IOException e) {
            throw new IllegalArgumentException("Error parsing file " + file, e);
        }

        Element pre = doc.body().getElementsByTag("pre").first();
        Elements items = pre.getElementsByTag("main");

        File csvFile = new File(outputPath + "/items.csv");
        try (
                FileWriter fileWriter = new FileWriter(csvFile)
        ) {
            CSVWriterBuilder builder =  new CSVWriterBuilder(fileWriter).withQuoteChar('"').withSeparator(',');

            try (
                    ICSVWriter writer = builder.build()
            ) {
                writer.writeNext(new String[]{
                        "id", "name", "registry_name", "category", "inventory_tab",
                        "slot", "visual_change", "gold_price", "reputation_price",
                        "classes", "level", "type", "minimum_damage",
                        "maximum_damage", "critical_chance", "critical_bonus_damage",
                        "hitrate", "effects", "description"
                });

                for (Element item : items) {
                    String itemHtml = item.html();
                    String itemText = item.text();
                    Element white = item.getElementsByTag("white").first();

                    String id = findFirst(ID, white.html());
                    if (id == null) {
                        throw new IllegalStateException("No id found for " + itemHtml);
                    }

                    String imageUrl = white.getElementsByTag("img").first().attr("src");
                    imageUrl = directory + imageUrl.substring(1, imageUrl.length());
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File(imageUrl));
                    } catch (IOException e) {
                        throw new IllegalStateException("Error reading image file\n" + imageUrl, e);
                    }

                    String name = item.getElementsByTag("b").text();
                    String categoryHtml = item.getElementsByTag("i").first().getElementsByTag("mainlight").text();
                    String category = findFirst(CATEGORY, categoryHtml);

                    String inventoryTab = findFirst(INVENTORY, itemText);
                    if (inventoryTab == null) {
                        throw new IllegalStateException("No inventory category found for item\n" + itemHtml);
                    }

                    Elements images = item.getElementsByTag("img");
                    String slot = null;
                    for (Element element : images) {
                        slot = SLOT_MAP.get(findFirst(SLOT_IMAGE, element.attr("src")));
                        if (slot != null) {
                            break;
                        }
                    }

                    if (slot == null) {
                        slot = "none";
                    }

                    String visualChange = findFirst(VISUAL_CHANGE, itemText);

                    String priceTag = item.getElementsByTag("price").first().text().replace(" ", "");
                    String goldPrice = findFirst(GOLD_PRICE, priceTag);
                    String reputationPrice = findFirst(REPUTATION_PRICE, priceTag);
                    if (goldPrice == null && reputationPrice == null) {
                        throw new IllegalStateException("No gold or reputation price found for item\n" + itemHtml);
                    }

                    Matcher classMatcher = CLASS.matcher(itemText);
                    String classes = null;
                    if (classMatcher.find()) {
                        classes = classMatcher.group("classes");
                        for (Map.Entry<String, String> entry : CLASS_MAP.entrySet()) {
                            String abbreviation = entry.getKey();
                            String className = entry.getValue();

                            classes = classes.replaceAll(abbreviation, className);
                        }
                    }

                    String level = findFirst(LVL, itemText);
                    String type = findFirst(TYPE, itemText);
                    String minDmg = findFirst(MINIMUM_DAMAGE, itemText);
                    String maxDmg = findFirst(MAXIMUM_DAMAGE, itemText);
                    String critChance = findFirst(CRIT_CHANCE, itemText);
                    String critBonusDamage = findFirst(CRIT_ADDITIONAL_DAMAGE, itemText);
                    String hitrate = findFirst(HITRATE, itemText);

                    Elements effectsBCards = item.getElementsByTag("bcard");
                    StringBuilder effectsBuilder = new StringBuilder();
                    for (Element effect : effectsBCards) {
                        effectsBuilder.append(effect.text());
                    }
                    String effects = effectsBuilder.toString();

                    String description = item.getElementsByTag("i").last().text();

                    String fileName = IRegistrable.toRegistryName(name);
                    String filePath = outputPath + "/" + fileName + ".png";
                    File imageFile = new File(filePath);

                    while (imageFile.exists()) {
                        int nextNumber = 1;
                        if (fileName.matches(".*_\\d+$")) {
                            int underscoreIndex = fileName.lastIndexOf("_");
                            String suffix = fileName.substring(underscoreIndex + 1, fileName.length());
                            nextNumber += Integer.parseInt(suffix);
                            fileName = fileName.substring(0, underscoreIndex);
                        }

                        fileName += "_" + nextNumber;
                        filePath = outputPath + "/" + fileName + ".png";
                        imageFile = new File(filePath);
                    }

                    String registryName = fileName;
                    ImageIO.write(image, "png", imageFile);

                    writer.writeNext(new String[]{
                            id, name, registryName, category, inventoryTab,
                            slot, visualChange, goldPrice, reputationPrice,
                            classes, level, type, minDmg,
                            maxDmg, critChance, critBonusDamage,
                            hitrate, effects, description
                    });
                }
            } catch (IOException e) {
                throw new IllegalStateException("Error closing csv writer for file " + csvFile, e);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error creating file writer for file " + csvFile, e);
        }

        FileReader fileReader;
        try {
            fileReader = new FileReader(csvFile);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File " + csvFile.getAbsolutePath() + " not found");
        }

        List<String> namesSeen = new ArrayList<>();
        CSVReaderBuilder builder = new CSVReaderHeaderAwareBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS);
        try (CSVReaderHeaderAware reader = (CSVReaderHeaderAware) builder.build()) {
            Map<String, String> line;
            while ((line = reader.readMap()) != null) {
                String registryName = line.get("registry_name");
                boolean changed = namesSeen.add(registryName);
                if (!changed) {
                    LOG.error("Repeat registry name found: " + registryName);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error closing csv reader", e);
        }
    }

}
