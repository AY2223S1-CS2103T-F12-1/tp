package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.role.Buyer;
import seedu.address.model.role.Seller;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class PersonModel implements ReadOnlyPersonModel {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public PersonModel() {}

    /**
     * Creates an PersonModel using the Persons in the {@code toBeCopied}
     */
    public PersonModel(ReadOnlyPersonModel toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code PersonModel} with {@code newData}.
     */
    public void resetData(ReadOnlyPersonModel newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code PersonModel}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Sets the given {@code person} in the list as a {@code buyer}.
     * {@code person} exists uniquely in the address book.
     */
    public void setBuyerRole(Person person, Buyer buyer) {
        requireAllNonNull(person, buyer);
        persons.setBuyerRole(person, buyer);
    }

    /**
     * Sets the given {@code person} in the list as a {@code seller}.
     * {@code person} exists uniquely in the address book.
     */
    public void setSellerRole(Person person, Seller seller) {
        requireAllNonNull(person, seller);
        person.setSeller(seller);
        // TODO: consider adding the new seller into a UniqueSellerList
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonModel // instanceof handles nulls
                && persons.equals(((PersonModel) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
