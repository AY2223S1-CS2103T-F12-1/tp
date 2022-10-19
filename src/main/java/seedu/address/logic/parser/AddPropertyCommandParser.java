package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.address.Address;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.property.Description;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.person.Priority;

/**
 * Parses input arguments and creates a new AddPropertyCommand object
 */
public class AddPropertyCommandParser extends Parser<AddPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPropertyCommand
     * and returns an AddPropertyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPropertyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE, PREFIX_ADDRESS,
                        PREFIX_DESCRIPTION, PREFIX_TAG, PREFIX_SELLER, PREFIX_CHARACTERISTICS);

        if (!arePrefixesPresent(
                argMultimap, PREFIX_NAME, PREFIX_PRICE, PREFIX_ADDRESS, PREFIX_DESCRIPTION, PREFIX_SELLER)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
        }

        PropertyName propertyName = ParserUtil.parsePropertyName(argMultimap.getValue(PREFIX_NAME).get());
        Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Set<Priority> priorityList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        String seller = ParserUtil.parseSellerName(argMultimap.getValue(PREFIX_SELLER).get());

        Characteristics characteristics = null;
        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            characteristics = ParserUtil.parseCharacteristics(argMultimap.getValue(PREFIX_CHARACTERISTICS).get());
        }

        Property property = new Property(propertyName, price, address, description, priorityList, seller, characteristics);

        return new AddPropertyCommand(property);
    }

}
