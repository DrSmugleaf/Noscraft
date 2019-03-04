package drsmugleaf.noscraft.util.parser.nostaleclub.sheet;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import drsmugleaf.noscraft.util.parser.nostaleclub.parsers.ColumnParser;
import drsmugleaf.noscraft.util.parser.nostaleclub.parsers.MissingParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.*;

/**
 * Created by DrSmugleaf on 03/03/2019
 */
public class CSVSheet {

    @Nonnull
    private final List<ColumnParser> PARSERS = new ArrayList<>();

    @Nonnull
    private final List<String> ADDITIONAL_HEADERS = new ArrayList<>();

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

        return i + ADDITIONAL_HEADERS.size();
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

    @Nonnull
    private String[] getHeaders() {
        String[] headers = new String[size()];

        int i = 0;
        for (ColumnParser parser : PARSERS) {
            for (CSVColumn column : parser.getColumns()) {
                headers[i + column.getIndex()] = column.getName();
            }

            i += parser.size();
        }

        for (int j = 0; j < ADDITIONAL_HEADERS.size(); j++) {
            headers[i + j] = ADDITIONAL_HEADERS.get(j);
        }

        return headers;
    }

    private void writeHeaders(@Nonnull ICSVWriter writer) {
        writer.writeNext(getHeaders());
    }

    public int addHeader(@Nonnull String header) {
        if (!ADDITIONAL_HEADERS.contains(header)) {
            ADDITIONAL_HEADERS.add(header);
        }

        return size() - ADDITIONAL_HEADERS.size() + ADDITIONAL_HEADERS.indexOf(header);
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
        Map<Integer, String> columns = new HashMap<>(size());

        int i = 0;
        for (ColumnParser parser : PARSERS) {
            columns = parser.parse(columns, i, item, this);
            i += parser.size();
        }

        for (int j = 0; j < size(); j++) {
            if (!columns.containsKey(j)) {
                columns.put(j, null);
            }
        }

        writer.writeNext(columns.values().toArray(new String[size()]));
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

                    for (Element bcard : item.getElementsByTag("bcard")) {
                        String lineMissingParser = isMissingParser(bcard.text());
                        if (lineMissingParser != null) {
                            throw new MissingParserException(lineMissingParser);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error closing writer for file " + csvOutput.getAbsolutePath(), e);
        }

        List<String[]> lines;
        try (FileReader fileReader = new FileReader(csvOutput)) {
            CSVReaderBuilder readerBuilder = new CSVReaderBuilder(fileReader)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .withSkipLines(1);

            try (CSVReader reader = readerBuilder.build()) {
                lines = reader.readAll();
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Error finding file " + csvOutput.getAbsolutePath(), e);
        } catch (IOException e) {
            throw new IllegalStateException("Error closing reader for file " + csvOutput.getAbsolutePath());
        }

        try (FileWriter fileWriter = new FileWriter(csvOutput)) {
            CSVWriterBuilder writerBuilder = new CSVWriterBuilder(fileWriter)
                    .withQuoteChar('"')
                    .withSeparator(',');

            try (ICSVWriter writer = writerBuilder.build()) {
                writer.writeNext(getHeaders());

                String[] completeLine = new String[size()];
                for (int i = 1; i < lines.size(); i++) {
                    String[] line = lines.get(i);
                    System.arraycopy(line, 0, completeLine, 0, line.length);
                    writer.writeNext(completeLine);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error closing writer for file " + csvOutput.getAbsolutePath());
        }
    }

}
