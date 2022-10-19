package seedu.address.model.property;

import seedu.address.model.pricerange.PriceRange;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's price
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {


    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain numbers and an optional exponent. For example: 123.45";
    public static final String VALIDATION_REGEX = "^[0-9]*\\.*[0-9]+$";
    public static final double EPSILON = 0.01d;
    public final String value;
    public final double numericalValue;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price number.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = price;
        numericalValue = Double.parseDouble(price);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    // TODO testing for these methods

    /**
     * Returns true if the stored numerical value is greater than a given {@code Price}.
     */
    public boolean isGreaterThan(Price p) {
        double d = p.getNumericalValue();
        return numericalValue - d > EPSILON || numericalValue - d == 0;
    }

    /**
     * Returns true if the stored numerical value is smaller than a given {@code Price}.
     */
    public boolean isSmallerThan(Price p) {
        double d = p.getNumericalValue();
        return d - numericalValue > EPSILON || numericalValue - d == 0;
    }

    public double getNumericalValue() {
        return this.numericalValue;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && value.equals(((Price) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
