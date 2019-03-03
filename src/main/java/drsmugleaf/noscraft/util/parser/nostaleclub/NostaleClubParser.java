package drsmugleaf.noscraft.util.parser.nostaleclub;

import drsmugleaf.noscraft.util.parser.nostaleclub.sheet.CSVSheet;
import drsmugleaf.noscraft.util.parser.nostaleclub.sheet.ItemsSheet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.io.File;

/**
 * Created by DrSmugleaf on 01/03/2019
 */
public class NostaleClubParser {

    @Nonnull
    public static final Logger LOG = LogManager.getLogger(NostaleClubParser.class.getSimpleName());

    @Nonnull
    private static final CSVSheet ITEMS_SHEET = new ItemsSheet();

    public static void main(String[] args) {
        String directory = "F:\\Downloaded Htmls\\NosTale";
        String filePath = "items.html";
        String outputPath = "F:\\Downloaded Htmls\\NosTale\\output";

        NostaleClubParser.parse(directory, filePath, outputPath);
    }

    public static void parse(@Nonnull String directory, @Nonnull String file, @Nonnull String outputPath) {
        File input = new File(directory + "/" + file);
        File output = new File(outputPath + "/" + "items.csv");
        ITEMS_SHEET.parseItems(input, output);
    }

}
