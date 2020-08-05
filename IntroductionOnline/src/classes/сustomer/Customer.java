package classes.сustomer;

/**
 * Class Customer for task 8
 */

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private long creditCartNumber;
    private long bankAccountNumber;
    private static int counter = 1;

    public Customer(String firstName, String lastName, String middleName, String address, long creditCartNumber,
        long bankAccountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.creditCartNumber = creditCartNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.id = counter++;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreditCartNumber() {
        return creditCartNumber;
    }

    public void setCreditCartNumber(long creditCartNumber) {
        this.creditCartNumber = creditCartNumber;
    }

    public long getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(long bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Customer.counter = counter;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +

            ", middleName='" + middleName + '\'' +
            ", address='" + address + '\'' +
            ", creditCartNumber=" + creditCartNumber +
            ", bankAccountNumber=" + bankAccountNumber +
            '}';
    }
}
