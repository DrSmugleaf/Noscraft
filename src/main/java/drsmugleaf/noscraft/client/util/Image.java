package drsmugleaf.noscraft.client.util;

import drsmugleaf.noscraft.Noscraft;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public class Image {

    @Nonnull
    private static BufferedImage getImage(@Nonnull String name) {
        name = name.replace(' ', '_').replaceAll("[':]", "").toLowerCase();
        String path = Noscraft.ASSETS + "textures/items/armor/swordsman/" + name + ".png";

        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new IllegalStateException("Error reading file " + path, e);
        }

        return image;
    }

    private static void saveImage(@Nonnull RenderedImage image, @Nonnull String path) {
        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            throw new IllegalStateException("Error overwriting image " + path, e);
        }
    }

    public static void translucentToOpaque(@Nonnull String imageName, @Nonnull String path) {
        BufferedImage image = getImage(imageName);

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

        saveImage(image, path);
    }

    public static void translucentToTransparent(@Nonnull String imageName, @Nonnull String path) {
        BufferedImage image = getImage(imageName);

        WritableRaster raster = image.getAlphaRaster();
        for (int width = 0; width < image.getWidth(); width++) {
            for (int height = 0; height < image.getHeight(); height++) {
                int[] pixel = raster.getPixel(width, height, new int[4]);
                if (pixel[0] < 255) {
                    pixel[0] = 0;
                    raster.setPixel(width, height, pixel);
                }
            }
        }

        saveImage(image, path);
    }

}
