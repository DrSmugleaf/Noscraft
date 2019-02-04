package drsmugleaf.noscraft.client.util;

import drsmugleaf.noscraft.Noscraft;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public class Json {

    public static void createJson(@Nonnull String name, @Nonnull String path) {
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

}
