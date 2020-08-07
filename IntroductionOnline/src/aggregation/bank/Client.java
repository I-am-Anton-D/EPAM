package aggregation.bank;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class for bank client
 */

public class Client {

    private final String name;
    private ArrayList<Account> accounts = new ArrayList<>();

    public Client(String name) {
        this.name = name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void sortAntPrintAccounts() {
        accounts.stream().sorted(Comparator.comparing(Account::getName)).forEach(System.out::println);
    }

    public void getAndPrintAllSum() {
        System.out.println("Balance on all accounts = " + accounts.stream().mapToLong(Account::getBalance).sum());
    }

    public void getAndPrintPositiveSum() {
        System.out.println(
            "Positive balance = " + accounts.stream().mapToLong(Account::getBalance).filter(e -> e >= 0).sum());
    }

    public void getAntPrintNegativeSum() {
        System.out.println(
            "Positive balance = " + accounts.stream().mapToLong(Account::getBalance).filter(e -> e < 0).sum());
    }

}
