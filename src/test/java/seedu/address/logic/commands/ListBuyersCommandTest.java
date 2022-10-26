package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.BeforeEach;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListBuyersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonsBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getPersonModel(), model.getPropertyModel(), new UserPrefs());
    }

    //    @Test
    //    public void execute_listIsNotFiltered_showsSameList() {
    //        assertCommandSuccess(new ListBuyersCommand(), model, ListBuyersCommand.MESSAGE_SUCCESS, expectedModel);
    //    }

    //    @Test
    //    public void execute_listIsFiltered_showsEverything() {
    //        showPersonAtIndex(model, INDEX_FIRST_ITEM);
    //        assertCommandSuccess(new ListBuyersCommand(), model, ListBuyersCommand.MESSAGE_SUCCESS, expectedModel);
    //    }
}
