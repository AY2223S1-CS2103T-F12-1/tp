package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CobbParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyPersonBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.Property;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CobbParser cobbParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        cobbParser = new CobbParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = cobbParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getPersonModel());
            storage.savePropertyBook(model.getPropertyModel());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyPersonBook getPersonModel() {
        return model.getPersonModel();
    }

    @Override
    public ObservableList<Buyer> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }


    @Override
    public ReadOnlyPropertyBook getPropertyModel() {
        return model.getPropertyModel();
    }

    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return model.getFilteredPropertyList();
    }


    public Path getPersonModelFilePath() {
        return model.getPersonModelFilePath();
    }

    @Override
    public Path getPropertyModelFilePath() {
        return model.getPropertyModelFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
