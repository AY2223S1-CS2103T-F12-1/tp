package seedu.address.model.property;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Property in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Property {

    // Identity fields
    private final PropertyName propertyName;
    private final Price price;

    // Data fields
    private final PropertyAddress propertyAddress;
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Property(PropertyName propertyName, Price price, PropertyAddress propertyAddress, Description description, Set<Tag> tags) {
        requireAllNonNull(propertyName, price, propertyAddress, description, tags);
        this.propertyName = propertyName;
        this.price = price;
        this.propertyAddress = propertyAddress;
        this.description = description;
        this.tags.addAll(tags);
    }

    public PropertyName getName() {
        return propertyName;
    }

    public PropertyAddress getPropertyAddress() {
        return propertyAddress;
    }

    public Price getPrice() {
        return price;
    }
    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both properties have the same name and price.
     * This defines a weaker notion of equality between two properties.
     */
    public boolean isSameProperty(Property otherProperty) {
        if (otherProperty == this) {
            return true;
        }

        return otherProperty != null
                && otherProperty.getName().equals(getName())
                && otherProperty.getPrice().equals(getPrice());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Property)) {
            return false;
        }

        Property otherProperty = (Property) other;
        return otherProperty.getName().equals(getName())
                && otherProperty.getPrice().equals(getPrice())
                && otherProperty.getPropertyAddress().equals(getPropertyAddress())
                && otherProperty.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(propertyName, price, propertyAddress, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Address: ")
                .append(getPropertyAddress())
                .append("; Price: ")
                .append(getPrice())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}