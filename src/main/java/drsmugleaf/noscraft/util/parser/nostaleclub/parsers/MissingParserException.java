package drsmugleaf.noscraft.util.parser.nostaleclub.parsers;

/**
 * Created by DrSmugleaf on 02/03/2019
 */
public class MissingParserException extends IllegalStateException {

    public MissingParserException(String line) {
        super("Missing parser for line \"" + line + "\"");
    }

}
