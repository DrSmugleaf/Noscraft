package drsmugleaf.noscraft.common.item.equipment.weapon;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.classes.Classes;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by DrSmugleaf on 09/02/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class ModWeapons {

    private static final @Nonnull String CSV_PATH = Noscraft.ASSETS + "/csv/weapon/";

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS).build();

        for (WeaponSlot slot : WeaponSlot.values()) {
            for (Classes clazz : Classes.values()) {
                String filePath = CSV_PATH + slot.name().toLowerCase() + "/" + clazz.name().toLowerCase() + ".csv";

                try (
                        FileReader fileReader = new FileReader(filePath);
                        CSVReaderHeaderAware reader = (CSVReaderHeaderAware) new CSVReaderHeaderAwareBuilder(fileReader)
                                .withCSVParser(parser)
                                .build()
                ) {
                    Map<String, String> line;
                    while ((line = reader.readMap()) != null) {
                        ItemModWeapon weapon = new ItemModWeapon(line.get("name"), clazz, slot);
                        event.getRegistry().register(weapon);
                    }
                } catch (FileNotFoundException e) {
                    Noscraft.LOG.error("File " + filePath + " not found", e); // TODO: 09/02/2019 Remove when all weapon csvs are added
                } catch (IOException e) {
                    throw new IllegalStateException("Error reading file " + filePath, e);
                }
            }
        }
    }

}
