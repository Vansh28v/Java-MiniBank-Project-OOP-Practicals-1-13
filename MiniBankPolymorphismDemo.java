
abstract class AccountBase {
    protected long balance;

    protected AccountBase(long opening) {
        balance = opening;
    }

    public long getBalance() {
        return balance;
    }

    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);
}

class SavingsAccount extends AccountBase {
    private final long minBalance;

    SavingsAccount(long opening, long minBalance) {
        super(opening);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0 && balance - amount >= minBalance;
    }
}

class CurrentAccount extends AccountBase {
    private final long overdraftLimit;

    CurrentAccount(long opening, long overdraftLimit) {
        super(opening);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() {
        return 0.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0 && balance - amount >= -overdraftLimit;
    }
}

class FixedDepositAccount extends AccountBase {
    FixedDepositAccount(long opening) {
        super(opening);
    }

    @Override
    public double interestRate() {
        return 7.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return false;
    }
}

public class MiniBankPolymorphismDemo {
    public static void main(String[] args) {

        AccountBase[] accounts = {
            new SavingsAccount(5000, 1000),
            new CurrentAccount(5000, 2000),
            new FixedDepositAccount(5000)
        };

        String[] names = {
            "Savings Account",
            "Current Account",
            "Fixed Deposit Account"
        };

        long withdrawal = 3000;

        for (int i = 0; i < accounts.length; i++) {
            AccountBase account = accounts[i];

            System.out.println("\n" + names[i]);
            System.out.println("Balance: Rs. " + account.getBalance());
            System.out.println(
                "Interest Rate: " + account.interestRate() + "%"
            );
            System.out.println(
                "Can withdraw Rs. " + withdrawal + "? "
                + account.canWithdraw(withdrawal)
            );
        }
    }
}
