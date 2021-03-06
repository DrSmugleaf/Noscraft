package drsmugleaf.noscraft.util;

import drsmugleaf.noscraft.common.IModellable;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrSmugleaf on 04/02/2019
 */
public class Json {

    public static void writeJson(@Nonnull IModellable modellable, @Nonnull String jsonPath, @Nonnull String fileName) {
        List<String> lines = new ArrayList<>();

        lines.add("{");
        lines.add("    \"parent\": \"builtin/generated\",");
        lines.add("    \"textures\": {");
        lines.add("        \"layer0\": \"" + modellable.getLayer0Path() + "\"");
        lines.add("    }");
        lines.add("}");

        Path filePath = Paths.get(jsonPath + "/" + fileName + ".json");
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            if (!(e instanceof FileAlreadyExistsException)) {
                throw new IllegalStateException("Error creating file " + filePath, e);
            }
        }

        try {
            Files.write(filePath, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new IllegalStateException("Error writing to file " + filePath, e);
        }
    }

}
