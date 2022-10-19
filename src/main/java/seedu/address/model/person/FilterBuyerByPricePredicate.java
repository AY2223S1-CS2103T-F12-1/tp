package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.model.property.Price;

/**
 * Tests that a {@code Person}'s {@code PriceRange} contains the given price value.
 */
public class FilterBuyerByPricePredicate extends AbstractFilterBuyerPredicate {

    private final Price price;

    /**
     * Standard constructor for the predicate. stringPrice will be validated
     * by Price's constructor.
     */
    public FilterBuyerByPricePredicate(Price price) {
        requireNonNull(price);
        this.price = price;
    }

    @Override
    public boolean test(Person p) {
        // N.B.: Returns true if the target person does not have a PriceRange object in their attributes.
        if (p.getPriceRange().isEmpty()) {
            return true;
        }
        return p.getPriceRange().get().isWithinPriceRange(price);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBuyerByPricePredicate // instanceof handles nulls
                && price.equals(((FilterBuyerByPricePredicate) other).price)); // state check
    }

}
