package drsmugleaf.noscraft.util.parser.nostaleclub.parsers;

import com.google.common.collect.ImmutableList;
import drsmugleaf.noscraft.util.parser.nostaleclub.sheet.CSVColumn;
import org.jsoup.nodes.Element;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by DrSmugleaf on 03/03/2019
 */
public abstract class ColumnParser {

    @Nonnull
    public static final String NO_END_APPEND_MARKER = "!$";

    @Nonnull
    private final ImmutableList<CSVColumn> COLUMNS;

    public ColumnParser(@Nonnull Collection<CSVColumn> columns) {
        COLUMNS = ImmutableList.copyOf(columns);
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
    public String[] parse(@Nonnull String[] columns, int start, @Nonnull Element input) {
        for (CSVColumn column : COLUMNS) {
            String parse = column.parse(input);
            if (parse != null) {
                columns[start + column.getIndex()] = parse;
            }
        }

        return columns;
    }

}
