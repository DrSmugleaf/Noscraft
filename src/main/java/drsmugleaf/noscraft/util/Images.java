package drsmugleaf.noscraft.util;

import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.IRegistrable;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public class Images {

    @Nonnull
    private static BufferedImage getImage(@Nonnull String name) {
        name = IRegistrable.toRegistryName(name);
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

    @Nonnull
    private static List<File> getAllImages(@Nonnull File path) {
        List<File> allImages = new ArrayList<>();
        File[] images = path.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));
        if (images != null) {
            allImages.addAll(Arrays.asList(images));
        }

        File[] folders = path.listFiles((dir, name) -> dir.isDirectory());
        if (folders != null) {
            for (File folder : folders) {
                allImages.addAll(getAllImages(folder));
            }
        }

        return allImages;
    }

    public static void resizeAllTo32(@Nonnull String path) {
        List<File> images = getAllImages(new File(path));
        if (images.isEmpty()) {
            throw new IllegalArgumentException("No valid images found in path " + path);
        }

        for (File imageFile : images) {
            Image image;
            try {
                image = ImageIO.read(imageFile);
            } catch (IOException e) {
                throw new IllegalStateException("Error reading image " + imageFile.getAbsolutePath(), e);
            }

            if (image.getHeight(null) != 32 || image.getWidth(null) != 32) {
                image = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                BufferedImage finalImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = finalImage.createGraphics();
                graphics.drawImage(image, 0, 0, null);
                graphics.dispose();

                try {
                    ImageIO.write(finalImage, "png", imageFile);
                } catch (IOException e) {
                    throw new IllegalStateException("Error writing image " + imageFile.getAbsolutePath(), e);
                }
            }
        }
    }

}
