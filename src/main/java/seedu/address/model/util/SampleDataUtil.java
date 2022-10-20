package seedu.address.model.util;

import seedu.address.model.PersonBook;
import seedu.address.model.PropertyBook;
import seedu.address.model.ReadOnlyPersonBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.address.Address;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.property.Description;
import seedu.address.model.property.Owner;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;

/**
 * Contains utility methods for populating {@code PersonBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new PriceRange("200000 - 250000"), new Characteristics("Bright; 5-Room"),
                new Priority("high")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new PriceRange("250000 - 280000"), new Characteristics("Clean; Large"),
                new Priority("low")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new PriceRange("300000 - 400000"), new Characteristics("Near MRT"),
                new Priority("low")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new PriceRange("500000 - 800000"), new Characteristics("Near School"),
                new Priority("normal")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new PriceRange("200000 - 250000"), new Characteristics("Bishan"),
                new Priority("normal")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new PriceRange("100000 - 150000"), new Characteristics("Toa Payoh; Kid-Friendly"),
                new Priority("low"))
        };
    }

    public static Owner[] getSampleOwners() {
        return new Owner[] {
            new Owner(new Name("Alex Yeoh"), new Phone("87438807")),
            new Owner(new Name("Bernice Yu"), new Phone("99272758")),
            new Owner(new Name("Charlotte Oliveiro"), new Phone("93210283")),
            new Owner(new Name("David Li"), new Phone("91031282")),
            new Owner(new Name("Irfan Ibrahim"), new Phone("92492021")),
            new Owner(new Name("Roy Balakrishnan"), new Phone("92624417"))
        };
    }

    public static Property[] getSampleProperties() {
        return new Property[]{
            new Property(new PropertyName("Residential College 4"), new Price("50000"),
                new Address("6 College Avenue East"), new Description("A place for NUS students to stay."),
                    new Characteristics("Bright; Sunny"), getSampleOwners()[0]),
            new Property(new PropertyName("Tembusu College"), new Price("9999"),
                        new Address("26 College Avenue East"), new Description("A place for NUS students to stay."),
                        new Characteristics("Near MRT"), getSampleOwners()[1]),
            new Property(new PropertyName("Peak Residence"), new Price("10000000"),
                        new Address("333 Thompson Road"),
                        new Description("latest freehold luxurious exclusive condominium."),
                        new Characteristics("Near School"), getSampleOwners()[2]),
            new Property(new PropertyName("Hut"), new Price("25000"),
                        new Address("25 Regent Road"),
                        new Description("new 3-room HDB flat"),
                        new Characteristics("Kid-Friendly"), getSampleOwners()[3]),
        };
    }

    public static ReadOnlyPersonBook getSamplePersonModel() {
        PersonBook sampleAb = new PersonBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyPropertyBook getSamplePropertyModel() {
        PropertyBook propertyBook = new PropertyBook();
        for (Property sampleProperty : getSampleProperties()) {
            propertyBook.addProperty(sampleProperty);
        }
        return propertyBook;
    }


}
