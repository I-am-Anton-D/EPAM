package aggregation.bank;

/**
 * Class for bank account
 */

public class Account {
    private String name;
    long balance;
    boolean blocked;

    public Account(String name, long balance, boolean blocked) {
        this.name = name;
        this.balance = balance;
        this.blocked = blocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "Account{" +
            "name='" + name + '\'' +
            ", balance=" + balance +
            ", blocked=" + blocked +
            '}';
    }
}
