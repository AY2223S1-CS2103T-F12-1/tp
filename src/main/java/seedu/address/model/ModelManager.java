package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.property.Property;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final PersonBook personBook;
    private final PropertyBook propertyBook;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Property> filteredProperties;

    /**
     * Initializes a ModelManager with the given personBook and userPrefs.
     */
    public ModelManager(ReadOnlyPersonBook personBook, ReadOnlyPropertyBook propertyBook,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(personBook, propertyBook, userPrefs);

        logger.fine("Initializing with person book: " + personBook + " and property book: " + propertyBook
                + " and user prefs " + userPrefs);

        this.personBook = new PersonBook(personBook);
        this.propertyBook = new PropertyBook(propertyBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personBook.getPersonList());
        filteredProperties = new FilteredList<>(this.propertyBook.getPropertyList());
    }

    public ModelManager() {
        this(new PersonBook(), new PropertyBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPersonBookFilePath() {
        return userPrefs.getPersonBookFilePath();
    }

    @Override
    public void setPersonBookFilePath(Path personBookFilePath) {
        requireNonNull(personBookFilePath);
        userPrefs.setPersonBookFilePath(personBookFilePath);
    }

    @Override
    public Path getPropertyBookFilePath() {
        return userPrefs.getPropertyBookFilePath();
    }

    @Override
    public void setPropertyBookFilePath(Path propertyBookFilePath) {
        requireNonNull(propertyBookFilePath);
        userPrefs.setPropertyBookFilePath(propertyBookFilePath);
    }

    //=========== PersonBook ================================================================================

    @Override
    public void setPersonBook(ReadOnlyPersonBook personBook) {
        this.personBook.resetData(personBook);
    }

    @Override
    public ReadOnlyPersonBook getPersonBook() {
        return personBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        personBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        personBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        personBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code PersonBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }
    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== PropertyBook ================================================================================

    @Override
    public void setPropertyBook(ReadOnlyPropertyBook propertyBook) {
        this.propertyBook.resetData(propertyBook);
    }

    @Override
    public ReadOnlyPropertyBook getPropertyBook() {
        return propertyBook;
    }

    @Override
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return propertyBook.hasProperty(property);
    }

    @Override
    public void deleteProperty(Property target) {
        propertyBook.removeProperty(target);
    }

    @Override
    public void addProperty(Property property) {
        propertyBook.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);
        propertyBook.setProperty(target, editedProperty);
    }

    //=========== Filtered Property List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Property} backed by the internal list of
     * {@code PropertyBook}
     */
    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return filteredProperties;
    }

    @Override
    public void updateFilteredPropertyList(Predicate<Property> predicate) {
        requireNonNull(predicate);
        filteredProperties.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        return userPrefs.equals(other.userPrefs)
                && personBook.equals(other.personBook)
                && propertyBook.equals(other.propertyBook)
                && filteredPersons.equals(other.filteredPersons)
                && filteredProperties.equals(other.filteredProperties);
    }

}
