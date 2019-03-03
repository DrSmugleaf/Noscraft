package drsmugleaf.noscraft.util.parser.nostaleclub.parsers;

import com.google.common.collect.ImmutableList;
import drsmugleaf.noscraft.util.parser.nostaleclub.sheet.CSVColumn;
import drsmugleaf.noscraft.util.parser.nostaleclub.sheet.CSVSheet;
import org.jsoup.nodes.Element;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by DrSmugleaf on 03/03/2019
 */
public abstract class ColumnParser {

    @Nonnull
    public static final String NO_END_APPEND_MARKER = "!$";

    @Nonnull
    private final ImmutableList<CSVColumn> COLUMNS;

    @Nonnull
    private final List<String> COLUMN_NAMES;

    public ColumnParser(@Nonnull Collection<CSVColumn> columns) {
        COLUMNS = ImmutableList.copyOf(columns);
        COLUMN_NAMES = COLUMNS.stream().map(CSVColumn::getName).collect(Collectors.toList());
    }

    @Nonnull
    public static String resolveDuplicateName(@Nonnull Collection<String> names, @Nonnull String name) {
        while (names.contains(name)) {
            int nextNumber = 1;
            if (name.matches(".*_\\d+$")) {
                int underscoreIndex = name.lastIndexOf("_");
                String suffix = name.substring(underscoreIndex + 1, name.length());
                nextNumber += Integer.parseInt(suffix);
                name = name.substring(0, underscoreIndex);
            }

            name += "_" + nextNumber;
        }

        return name;
    }

    @Nullable
    protected static String removeSpaces(@Nullable String string) {
        return string == null ? null : string.replaceAll(" ", "");
    }

    @Nonnull
    private static String appendLineEnd(@Nonnull String regex) {
        if (regex.endsWith(NO_END_APPEND_MARKER)) {
            return regex.substring(0, regex.length() - NO_END_APPEND_MARKER.length());
        } else {
            return regex + "\\.$";
        }
    }

    @Nonnull
    protected static String[] appendLineEnd(@Nonnull String[] regexes) {
        for (int i = 0; i < regexes.length; i++) {
            regexes[i] = appendLineEnd(regexes[i]);
        }

        return regexes;
    }

    public int size() {
        return COLUMNS.size();
    }

    @Nonnull
    public ImmutableList<CSVColumn> getColumns() {
        return COLUMNS;
    }

    @Nonnull
    public CSVColumn getColumn(int i) {
        return COLUMNS.get(i);
    }

    @Nullable
    public CSVColumn getColumn(@Nonnull String name) {
        for (CSVColumn column : COLUMNS) {
            if (column.getName().equalsIgnoreCase(name)) {
                return column;
            }
        }

        return null;
    }

    @Nonnull
    public String[] getColumnNames() {
        return COLUMNS.stream().map(CSVColumn::getName).toArray(String[]::new);
    }

    @Nonnull
    public Map<Integer, String> parse(@Nonnull Map<Integer, String> columns, int start, @Nonnull Element input, @Nonnull CSVSheet csvSheet) {
        for (CSVColumn column : COLUMNS) {
            List<String> results = column.parse(input);
            if (results.isEmpty()) {
                continue;
            }

            for (int i = 0; i < results.size(); i++) {
                int index = i + start + column.getIndex();
                if (i > 0) {
                    String newColumn = resolveDuplicateName(COLUMN_NAMES, column.getName());
                    index = csvSheet.addHeader(newColumn);
                }

                columns.put(index, results.get(i));
            }
        }

        return columns;
    }

}
