package drsmugleaf.noscraft.common.item;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import drsmugleaf.noscraft.Noscraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        event.getRegistry().register(new ItemMod("Logo Icon") {
            @Nonnull
            @Override
            public String getNameToRegister() {
                return "logo_icon";
            }

            @Nonnull
            @Override
            public String getLayer0Path() {
                return LAYER0_PREFIX + toRegistryName();
            }
        });

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
                String name = line.get("name");
                String registryName = line.get("registryName");
                event.getRegistry().register(new ItemMod(name) {
                    @Nonnull
                    @Override
                    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
                        return name;
                    }

                    @Nonnull
                    @Override
                    public String getNameToRegister() {
                        return registryName;
                    }

                    @Nonnull
                    @Override
                    public String getLayer0Path() {
                        return LAYER0_PREFIX + toRegistryName();
                    }
                });
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File " + CSV_PATH + " not found", e);
        } catch (IOException e) {
            throw new IllegalStateException("Error reading file " + CSV_PATH, e);
        }
    }

}
