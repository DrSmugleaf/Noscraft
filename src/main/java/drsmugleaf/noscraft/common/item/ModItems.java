package drsmugleaf.noscraft.common.item;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import drsmugleaf.noscraft.Noscraft;
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
 * Created by DrSmugleaf on 16/02/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class ModItems {

    private static final @Nonnull String CSV_PATH = Noscraft.ASSETS + "/csv/items.csv";

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemMod("Logo Icon", "logo_icon") {});

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .build();

        try (
                FileReader fileReader = new FileReader(CSV_PATH);
                CSVReaderHeaderAware reader = (CSVReaderHeaderAware) new CSVReaderHeaderAwareBuilder(fileReader)
                        .withCSVParser(parser)
                        .build()
        ) {
            Map<String, String> line;
            while ((line = reader.readMap()) != null) {
                ItemMod item = null;
                if (line.get("slot") != null) {
                    item = ItemEquippable.from(line);
                }

                if (item != null) { // TODO: 04/03/2019 Remove = null when all item classes are created
                    event.getRegistry().register(item);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File " + CSV_PATH + " not found", e);
        } catch (IOException e) {
            throw new IllegalStateException("Error reading file " + CSV_PATH, e);
        }
    }

}
