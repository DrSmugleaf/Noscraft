package drsmugleaf.noscraft.util.parser.nostaleclub.parsers.regex;

import org.jsoup.nodes.Element;

import javax.annotation.Nonnull;

/**
 * Created by DrSmugleaf on 03/03/2019
 */
public enum ParsingMode {

    TEXT("MODETEXT") {
        @Nonnull
        @Override
        public String getInput(@Nonnull Element element) {
            return element.text();
        }
    },
    HTML("") {
        @Nonnull
        @Override
        public String getInput(@Nonnull Element element) {
            return element.html();
        }
    };

    @Nonnull
    private final String NAME;

    ParsingMode(@Nonnull String name) {
        NAME = name;
    }

    @Nonnull
    public static ParsingMode from(@Nonnull String regexGroupName) {
        for (ParsingMode mode : ParsingMode.values()) {
            String modeName = mode.getName();
            if (regexGroupName.endsWith(modeName)) {
                return mode;
            }
        }

        return HTML;
    }

    @Nonnull
    public String removeSuffix(@Nonnull String regexGroupName) {
        return regexGroupName.substring(0, regexGroupName.length() - getName().length());
    }

    @Nonnull
    public String getName() {
        return NAME;
    }

    @Nonnull
    public abstract String getInput(@Nonnull Element element);

}
