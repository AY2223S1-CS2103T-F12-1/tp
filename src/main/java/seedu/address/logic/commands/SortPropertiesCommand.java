package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;

/**
 * Sorts the properties list.
 */
public class SortPropertiesCommand extends Command {
    public static final String COMMAND_WORD = "sortprops";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts properties by name or price "
            + "in a specified order. You can only sort by one criteria at a time."
            + "Parameters: "
            + "[" + PREFIX_NAME + " ASC/DESC] "
            + "[" + PREFIX_PRICE + " ASC/DESC]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " ASC";

    public static final String MESSAGE_SUCCESS = "Property list: %s";

    private final Comparator<Property> comparator;

    /**
     * Creates a SortPropertiesCommand to add the specified {@code Property}
     * @param comparator
     */
    public SortPropertiesCommand(Comparator<Property> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortPropertyList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, comparator.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortPropertiesCommand // instanceof handles nulls
                && comparator.equals(((SortPropertiesCommand) other).comparator));
    }
}
