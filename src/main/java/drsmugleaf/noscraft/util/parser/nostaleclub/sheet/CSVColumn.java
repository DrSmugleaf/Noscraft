package drsmugleaf.noscraft.util.parser.nostaleclub.sheet;

import drsmugleaf.noscraft.util.parser.nostaleclub.parsers.regex.ParsingMode;
import drsmugleaf.noscraft.util.parser.nostaleclub.parsers.regex.RegexPattern;
import org.intellij.lang.annotations.Language;
import org.jsoup.nodes.Element;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by DrSmugleaf on 02/03/2019
 */
public class CSVColumn {

    @Nonnull
    private final String NAME;

    @Nonnull
    private final RegexPattern REGEX;

    private final int INDEX;

    @Nonnull
    private final ParsingMode MODE;

    private CSVColumn(@Nonnull String name, @Nonnull RegexPattern pattern, int id, @Nonnull ParsingMode mode) {
        NAME = name;
        REGEX = pattern;
        INDEX = id;
        MODE = mode;
    }

    @Nonnull
    public static CSVColumn from(@Nonnull String name, @Nonnull RegexPattern pattern, int id, @Nullable ParsingMode mode) {
        if (mode == null) {
            mode = ParsingMode.from(name);
            name = mode.removeSuffix(name);
        }

        return new CSVColumn(name, pattern, id, mode);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    public static Set<CSVColumn> from(@Nullable ParsingMode mode, @Language("RegExp") @Nonnull String... regexes) {
        Set<CSVColumn> columns = new HashSet<>();

        for (String regex : regexes) {
            RegexPattern regexPattern = RegexPattern.compile(regex);
            Pattern pattern = regexPattern.getPattern();
            Field namedGroups;
            try {
                namedGroups = pattern.getClass().getDeclaredField("namedGroups");
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException("Error getting field namedGroups from pattern", e);
            }

            namedGroups.setAccessible(true);
            try {
                Map<String, Integer> groups = (Map<String, Integer>) namedGroups.get(pattern);
                if (groups == null) {
                    throw new IllegalArgumentException("No named groups found for regex " + regex);
                }

                List<String> keys = new ArrayList<>(groups.keySet());
                for (String name : keys) {
                    CSVColumn column = from(name, regexPattern, columns.size(), mode);
                    columns.add(column);
                    Integer id = groups.remove(name);
                    groups.put(column.getName(), id);
                }

                namedGroups.set(pattern, groups);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Error getting field namedGroups value", e);
            }
        }

        return columns;
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    public static Set<CSVColumn> from(@Language("RegExp") @Nonnull String... regexes) {
        return from(null, regexes);
    }

    @Nonnull
    public String getName() {
        return NAME;
    }

    @Nonnull
    public RegexPattern getRegex() {
        return REGEX;
    }

    public int getIndex() {
        return INDEX;
    }

    @Nonnull
    public List<String> parse(@Nonnull Element input) {
        return REGEX.find(MODE.getInput(input), NAME);
    }

}
