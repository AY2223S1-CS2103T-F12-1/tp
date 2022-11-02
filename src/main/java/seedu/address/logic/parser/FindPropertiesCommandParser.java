package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindPropertiesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPropertiesCommand object
 */
public class FindPropertiesCommandParser extends Parser<FindPropertiesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPropertiesCommand
     * and returns a FindPropertiesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPropertiesCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertiesCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPropertiesCommand(new PropertyNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}