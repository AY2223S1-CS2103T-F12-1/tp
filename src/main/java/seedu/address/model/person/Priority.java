package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Priority in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    private enum PriorityName {
        HIGH, NORMAL, LOW;
    }

    public static final String MESSAGE_CONSTRAINTS = "Priority should be high, low or normal.";
    public static final String VALIDATION_HIGH = "HIGH";
    public static final String VALIDATION_NORMAL = "NORMAL";
    public static final String VALIDATION_LOW = "LOW";

    public final PriorityName specifiedPriority;

    /**
     * Constructs a {@code Priority}.
     *
     * @param specifiedPriority A valid priority.
     */
    public Priority(String specifiedPriority) {
        requireNonNull(specifiedPriority);
        specifiedPriority = specifiedPriority.toUpperCase();
        checkArgument(isValidPriority(specifiedPriority), MESSAGE_CONSTRAINTS);
        this.specifiedPriority = PriorityName.valueOf(specifiedPriority);
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.equals(VALIDATION_HIGH)
                || test.equals(VALIDATION_NORMAL)
                || test.equals(VALIDATION_LOW);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && specifiedPriority.equals(((Priority) other).specifiedPriority)); // state check
    }

    @Override
    public int hashCode() {
        return specifiedPriority.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        //return '[' + specifiedPriority.toString() + ']';
        return specifiedPriority.toString();
    }

}