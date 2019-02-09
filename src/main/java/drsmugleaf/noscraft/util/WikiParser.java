package drsmugleaf.noscraft.util;

import drsmugleaf.noscraft.common.item.IModellable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by DrSmugleaf on 08/02/2019
 */
public class WikiParser {

    public static void parse(@Nonnull String path, @Nonnull String file, @Nonnull String savePath) {
        try {
            Document doc = Jsoup.parse(new File(path + "/" + file), "UTF-8");
            Elements tables = doc.getElementsByClass("wikitable");
            int i = 1;
//            int j = 1;
            for (Element table : tables) {
                String folder;
//                String type;
//                if (i < 3) folder = "swordsman"; else if (i < 5) folder = "mage"; else if (i < 7) folder = "archer"; else folder = "martialartist";
                if (i < 2) folder = "swordsman"; else if (i < 3) folder = "mage"; else if (i < 4) folder = "archer"; else folder = "martialartist";
//                if (j % 2 == 0) type = "main"; else type = "secondary";

                Elements rows = table.getElementsByTag("tr");
                rows.remove(0);
                for (Element row : rows) {
                    Elements elements = row.getElementsByTag("td");
                    if (elements.get(0).getElementsByTag("img").isEmpty()) {
                        continue;
                    }

                    String imageAttr = elements.get(0).getElementsByTag("img").get(0).attr("src");
                    BufferedImage image = ImageIO.read(new File(path + imageAttr.replaceFirst(".", "")));
                    String name = elements.get(2).getElementsByTag("a").get(0).attr("title");
//                    File saveFile = new File(savePath + "/" + folder + "/" + type + "/" + name.toLowerCase().replaceAll(" ", "_").replaceAll(":", "") + ".png");
                    File saveFile = new File(savePath + "/" + folder + "/" + IModellable.toRegistryName(name) + ".png");
                    ImageIO.write(image, "png", saveFile);
                }

                i++;
//                j++;
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
