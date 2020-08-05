package classes.сustomer;

import classes.AbstractHolder;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Customers holder for aggregate array of Customers and make some sort and filter
 */

public class CustomersHolder extends AbstractHolder<Customer> {

    public CustomersHolder(int countOfCustomers) {
        super(Customer.class, countOfCustomers);
    }

    /**
     * Sorting customers by name
     */

    public void sortAndPrintCustomers() {
        Arrays.sort(array, Comparator.comparing(Customer::getLastName).thenComparing(Customer::getFirstName)
            .thenComparing(Customer::getMiddleName));
        for (Customer c : array) {
            System.out.println(c.toString());
        }
    }

    /**
     * Filter customers by credit cart number
     * @param min - left border
     * @param max - right border
     */

    public void filterByCreditCartNumber(long min, long max) {
        Arrays.stream(array)
            .filter(customer -> customer.getCreditCartNumber() < max && customer.getCreditCartNumber() > min)
            .forEach(customer -> System.out.println(customer.toString()));
    }
}
