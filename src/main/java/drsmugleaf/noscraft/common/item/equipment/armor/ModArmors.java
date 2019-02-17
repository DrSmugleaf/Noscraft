package drsmugleaf.noscraft.common.item.equipment.armor;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.classes.Classes;
import drsmugleaf.noscraft.util.Json;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by DrSmugleaf on 01/02/2019
 */
public class ModArmors {

    private static final @Nonnull String CSV_PATH = Noscraft.ASSETS + "/csv/armor/";

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS).build();

        for (Classes clazz : Classes.values()) {
            String filePath = CSV_PATH + clazz.name().toLowerCase() + ".csv";

            try (
                    FileReader fileReader = new FileReader(filePath);
                    CSVReaderHeaderAware reader = (CSVReaderHeaderAware) new CSVReaderHeaderAwareBuilder(fileReader)
                            .withCSVParser(parser)
                            .build()
            ) {
                Map<String, String> line;
                while ((line = reader.readMap()) != null) {
                    ItemModArmor armor = new ItemModArmor(EntityEquipmentSlot.CHEST, line.get("name"), clazz);
                    event.getRegistry().register(armor);
                }
            } catch (FileNotFoundException e) {
                Noscraft.LOG.error("File " + filePath + " not found", e); // TODO: 09/02/2019 Remove when all armor csvs are added
            } catch (IOException e) {
                throw new IllegalStateException("Error reading file " + filePath, e);
            }
        }
    }

}
