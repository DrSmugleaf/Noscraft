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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
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
        lines.add("        \"layer0\": \"" + Noscraft.MOD_ID + ":" + path + name + "\"");
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

    private static void fixImage(@Nonnull String name) {
        name = name.replace(' ', '_').replaceAll("[':]", "").toLowerCase();
        String path = Noscraft.ASSETS + "textures/items/armor/swordsman/" + name + ".png";

        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new IllegalStateException("Error reading file " + path, e);
        }

        WritableRaster raster = image.getAlphaRaster();
        for (int width = 0; width < image.getWidth(); width++) {
            for (int height = 0; height < image.getHeight(); height++) {
                int[] pixel = raster.getPixel(width, height, new int[4]);
                if (pixel[0] > 0) {
                    pixel[0] = 255;
                    raster.setPixel(width, height, pixel);
                }
            }
        }

        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            throw new IllegalStateException("Error overwriting image " + path, e);
        }
    }

}
