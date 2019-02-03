package drsmugleaf.noscraft.common.item.equipment.armor;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import drsmugleaf.noscraft.Noscraft;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ModArmors {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS).build();
        String csvPath = Noscraft.ASSETS + "/csv/armor/swordsman.csv";

        try (
                FileReader fileReader = new FileReader(csvPath);
                CSVReaderHeaderAware reader = (CSVReaderHeaderAware) new CSVReaderHeaderAwareBuilder(fileReader)
                        .withCSVParser(parser)
                        .build()
        ) {
            Map<String, String> line;
            while ((line = reader.readMap()) != null) {
                ItemArmor armor = new ItemArmor(EntityEquipmentSlot.CHEST, line.get("name"));
                event.getRegistry().register(armor);
//                createJson(line.get("name"), "/items/armor/swordsman");
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File " + csvPath + " not found", e);
        } catch (IOException e) {
            throw new IllegalStateException("Error reading file " + csvPath, e);
        }
    }

    private static void createJson(@Nonnull String name, @Nonnull String path) {
        if (path.charAt(0) == '/') {
            path = path.substring(1, path.length());
        }

        if (path.charAt(path.length() - 1) != '/') {
            path = path + '/';
        }

        name = name.replace(' ', '_').replaceAll("[':]", "").toLowerCase();
        List<String> lines = new ArrayList<>();
        lines.add("{");
        lines.add("    \"parent\": \"builtin/generated\",");
        lines.add("    \"textures\": {");
        lines.add("        \"layer0\": \"noscraft:" + path + name + "\"");
        lines.add("    }");
        lines.add("}");
        String modelsPath = "models/" + path.replaceFirst("items", "item");
        Path filePath = Paths.get(Noscraft.ASSETS + modelsPath + name.toLowerCase() + ".json");

        try {
            Files.createFile(filePath);
        } catch (IOException ignored) {}

        try {
            Files.write(filePath, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to file " + filePath, e);
        }
    }

}
