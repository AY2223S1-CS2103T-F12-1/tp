package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.pricerange.property.FilterPropsByOwnerNamePredicate;
import seedu.address.model.pricerange.property.Owner;
import seedu.address.testutil.PropertyBuilder;

public class FilterPropsByOwnerNamePredicteTest {

    @Test
    public void equals() {
        Name name1 = new Name("Tommy");
        Name name2 = new Name("Johnny Walker");

        FilterPropsByOwnerNamePredicate firstPredicate =
                new FilterPropsByOwnerNamePredicate(name1);
        FilterPropsByOwnerNamePredicate secondPredicate =
                new FilterPropsByOwnerNamePredicate(name2);
        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FilterPropsByOwnerNamePredicate firstPredicateCopy =
                new FilterPropsByOwnerNamePredicate(name1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals("Tommy"));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different owner -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_propertyOwnerNameMatch_returnsTrue() {
        Owner rickyTan = new Owner(new Name("Ricky Tan"), new Phone("1324019384"));
        Owner kim = new Owner(new Name("Kim"), new Phone("124801324"));
        // Owner Name matches
        FilterPropsByOwnerNamePredicate predicate =
                new FilterPropsByOwnerNamePredicate(new Name("Ricky Tan"));
        assertTrue(predicate.test(new PropertyBuilder().withOwner(rickyTan).build()));

        // Owner Name partially matches
        predicate = new FilterPropsByOwnerNamePredicate(new Name("Ricky"));
        assertTrue(predicate.test(new PropertyBuilder().withOwner(rickyTan).build()));

        // Different case
        predicate = new FilterPropsByOwnerNamePredicate(new Name("KIM"));
        assertTrue(predicate.test(new PropertyBuilder().withOwner(kim).build()));

        //Mixed Case
        predicate = new FilterPropsByOwnerNamePredicate(new Name("RickY TAN"));
        assertTrue(predicate.test(new PropertyBuilder().withOwner(rickyTan).build()));
    }

    @Test
    public void test_propertyOwnerNameDoesNotMatch_returnsFalse() {
        Owner rickyTan = new Owner(new Name("Ricky Tan"), new Phone("1324019384"));
        Owner kim = new Owner(new Name("Kim"), new Phone("124801324"));
        // Owner name does not match
        FilterPropsByOwnerNamePredicate predicate =
                new FilterPropsByOwnerNamePredicate(new Name("Ricky Tan"));
        assertFalse(predicate.test(new PropertyBuilder().withOwner(kim).build()));

        predicate = new FilterPropsByOwnerNamePredicate(new Name("Ricky Tan Hi"));
        assertFalse(predicate.test(new PropertyBuilder().withOwner(rickyTan).build()));
    }
}
