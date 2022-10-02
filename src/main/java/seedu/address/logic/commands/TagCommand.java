package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class TagCommand extends Command {

    private final Index index;
    private final Tag tag;

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Tag: %2$s";

    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing tag will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "t/ [TAG]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "t/ Buyer";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added tag to Person: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed tag from Person: %1$s";

    public TagCommand(Index index, Tag tag) {
        requireAllNonNull(index, tag);

        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> newTags = new HashSet<>(personToEdit.getTags());
        newTags.add(tag);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), newTags);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !tag.tagName.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return index.equals(e.index)
                && tag.equals(e.tag);
    }

}
