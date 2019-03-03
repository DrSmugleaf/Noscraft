package drsmugleaf.noscraft.util.parser.nostaleclub.parsers.regex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DrSmugleaf on 02/03/2019
 */
public class RegexPattern {

    @Nonnull
    private final Pattern PATTERN;

    private RegexPattern(@Nonnull Pattern pattern) {
        PATTERN = pattern;
    }

    @Nonnull
    public static RegexPattern compile(@Nonnull String regex) {
        return new RegexPattern(Pattern.compile(regex));
    }

    @Nonnull
    public Pattern getPattern() {
        return PATTERN;
    }

    @Nonnull
    public List<String> find(@Nonnull String input, @Nonnull String groupName) {
        List<String> results = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(input);
        while (matcher.find()) {
            String match = matcher.group(groupName);
            results.add(match);
        }

        return results;
    }

    @Nonnull
    public List<String> find(@Nonnull String input) {
        List<String> results = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(input);
        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                String match = matcher.group(i);
                results.add(match);
            }
        }

        return results;
    }

    @Nullable
    public String findFirst(@Nonnull String input) {
        Matcher matcher = PATTERN.matcher(input);
        if (matcher.find()) {
            return matcher.group().toLowerCase();
        }

        return null;
    }

    @Nullable
    public Integer findFirstInt(@Nonnull String input) {
        String match = findFirst(input);
        if (match == null) {
            return null;
        }

        return Integer.valueOf(match);
    }

}
