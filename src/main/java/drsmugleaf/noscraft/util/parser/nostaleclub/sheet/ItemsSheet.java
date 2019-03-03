package drsmugleaf.noscraft.util.parser.nostaleclub.sheet;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import drsmugleaf.noscraft.util.parser.nostaleclub.NostaleClubParser;
import drsmugleaf.noscraft.util.parser.nostaleclub.parsers.EffectsParser;
import drsmugleaf.noscraft.util.parser.nostaleclub.parsers.GeneralParser;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by DrSmugleaf on 03/03/2019
 */
public class ItemsSheet extends CSVSheet {

    public ItemsSheet() {
        super(new GeneralParser(), new EffectsParser());
    }

    private static void checkDuplicateRegistryNames(@Nonnull File csv) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(csv);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File " + csv.getAbsolutePath() + " not found");
        }

        List<String> namesSeen = new ArrayList<>();
        CSVReaderBuilder builder = new CSVReaderHeaderAwareBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS);
        try (CSVReaderHeaderAware reader = (CSVReaderHeaderAware) builder.build()) {
            Map<String, String> line;
            while ((line = reader.readMap()) != null) {
                String registryName = line.get("registry_name");
                boolean changed = namesSeen.add(registryName);
                if (!changed) {
                    NostaleClubParser.LOG.error("Repeat registry name found: " + registryName);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error closing csv reader", e);
        }
    }

    public static void createImages(@Nonnull File csv, @Nonnull File imagesFolder, @Nonnull File outputFolder) {
        try (FileReader fileReader = new FileReader(csv)) {
            CSVReaderHeaderAware reader = (CSVReaderHeaderAware) new CSVReaderHeaderAwareBuilder(fileReader)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .build();

            Map<String, String> line;
            while ((line = reader.readMap()) != null) {
                String id = line.get("id");
                String name = line.get("registryName");
                File imageFile = new File(imagesFolder.getAbsolutePath() + "/" + "e" + id + ".png");
                BufferedImage image = ImageIO.read(imageFile);
                String outputName = outputFolder.getAbsolutePath() + "/" + name;
                ImageIO.write(image, "png", new File(outputName));
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File " + csv.getAbsolutePath() + " not found", e);
        } catch (IOException e) {
            throw new IllegalStateException("Error creating images for csv " + csv.getAbsolutePath(), e);
        }
    }

    @Override
    public void parseItems(@Nonnull File htmlInput, @Nonnull File csvOutput) {
        super.parseItems(htmlInput, csvOutput);
        Set<String> duplicateColumns = getDuplicateColumns();
        if (!duplicateColumns.isEmpty()) {
            throw new IllegalStateException();
        }

        checkDuplicateRegistryNames(csvOutput);
    }

}
