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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File " + csvPath + " not found", e);
        } catch (IOException e) {
            throw new IllegalStateException("Error reading file " + csvPath, e);
        }
    }

}
