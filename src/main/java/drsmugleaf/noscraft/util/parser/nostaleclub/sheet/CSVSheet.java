package drsmugleaf.noscraft.util.parser.nostaleclub.sheet;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import drsmugleaf.noscraft.util.parser.nostaleclub.parsers.ColumnParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by DrSmugleaf on 03/03/2019
 */
public class CSVSheet {

    @Nonnull
    private final List<ColumnParser> PARSERS = new ArrayList<>();

    public CSVSheet(@Nonnull Collection<ColumnParser> parsers) {
        PARSERS.addAll(parsers);
    }

    public CSVSheet(@Nonnull ColumnParser... parsers) {
        this(Arrays.asList(parsers));
    }

    public int size() {
        int i = 0;
        for (ColumnParser parser : PARSERS) {
            i += parser.size();
        }

        return i;
    }

    private boolean matchesAny(@Nonnull String line) {
        for (ColumnParser parser : PARSERS) {
            for (CSVColumn column : parser.getColumns()) {
                boolean matches = column.getRegex().findFirst(line) != null;
                if (matches) {
                    return true;
                }
            }
        }

        return false;
    }

    @Nullable
    public String isMissingParser(@Nonnull String effects) {
        if (effects.isEmpty()) {
            return null;
        }

        String[] lines = effects.split("\n");
        for (String line : lines) {
            if (!matchesAny(line)) {
                return line;
            }
        }

        return null;
    }

    @Nonnull
    public Set<String> getDuplicateColumns() {
        Set<String> namesSeen = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        for (ColumnParser parser : PARSERS) {
            for (CSVColumn column : parser.getColumns()) {
                String columnName = column.getName();
                if (!namesSeen.add(columnName)) {
                    duplicateNames.add(columnName);
                }
            }
        }

        return duplicateNames;
    }

    private void writeHeaders(@Nonnull ICSVWriter writer) {
        String[] headers = new String[size()];

        int i = 0;
        for (ColumnParser parser : PARSERS) {
            for (CSVColumn column : parser.getColumns()) {
                headers[i + column.getIndex()] = column.getName();
            }

            i += parser.size();
        }

        writer.writeNext(headers);
    }

    @Nullable
    private Integer getColumnId(@Nonnull String header) {
        int i = 0;
        for (ColumnParser parser : PARSERS) {
            CSVColumn column = parser.getColumn(header);
            if (column != null) {
                return column.getIndex() + i;
            }

            i += parser.size();
        }

        return null;
    }

    private void writeNext(@Nonnull ICSVWriter writer, @Nonnull Element item) {
        String[] columns = new String[size()];

        int i = 0;
        for (ColumnParser parser : PARSERS) {
            columns = parser.parse(columns, i, item);
            i += parser.size();
        }

        writer.writeNext(columns);
    }

    public void parseItems(@Nonnull File htmlInput, @Nonnull File csvOutput) {
        Set<String> duplicateColumns = getDuplicateColumns();
        if (!duplicateColumns.isEmpty()) {
            throw new IllegalStateException("Duplicate column names: " + String.join(", ", duplicateColumns));
        }

        Document doc;
        try {
            doc = Jsoup.parse(htmlInput, "UTF-8");
        } catch (IOException e) {
            throw new IllegalArgumentException("Error parsing file " + htmlInput, e);
        }

        Element pre = doc.body().getElementsByTag("pre").first();
        Elements items = pre.getElementsByTag("main");

        try (FileWriter fileWriter = new FileWriter(csvOutput)) {
            CSVWriterBuilder builder = new CSVWriterBuilder(fileWriter)
                    .withQuoteChar('"')
                    .withSeparator(',');

            try (ICSVWriter writer = builder.build()) {
                writeHeaders(writer);

                for (Element item : items) {
                    writeNext(writer, item);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error closing writer for file " + csvOutput.getAbsolutePath(), e);
        }
    }

}
