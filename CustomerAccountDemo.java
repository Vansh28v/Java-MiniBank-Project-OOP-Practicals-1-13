
class Customer {
    private static long counter = 100;

    private final String customerId;
    private final String name;
    private final String email;
    private final String mobile;

    Customer(String name, String email, String mobile) {
        this.customerId = "CUST" + (++counter);
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }
}

class Account {
    private static int counter = 1;

    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active = true;

    Account(String owner, long opening) {
        accountNumber = String.format("AC%04d", counter++);
        ownerName = owner;
        balance = opening;
    }

    Account(String owner) {
        this(owner, 0);
    }

    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(long amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }
}

public class CustomerAccountDemo {
    public static void main(String[] args) {
        Customer c1 = new Customer(
            "Riya", "riya@example.com", "9876543210"
        );

        Customer c2 = new Customer(
            "Aman", "aman@example.com", "9123456780"
        );

        System.out.println("Customer ID: " + c1.getCustomerId());
        System.out.println("Name: " + c1.getName());
        System.out.println("Email: " + c1.getEmail());

        Account a1 = new Account("Riya", 5000);
        Account a2 = new Account("Aman");

        Account[] accounts = {a1, a2};

        System.out.println("\n--- Account Details ---");

        for (Account a : accounts) {
            System.out.println("Account: " + a.getAccountNumber());
            System.out.println("Owner: " + a.getOwnerName());
            System.out.println("Balance: Rs. " + a.getBalance());
        }

        a1.deposit(2000);
        System.out.println("\nAfter depositing Rs. 2000: Rs. "
                + a1.getBalance());

        System.out.println("Withdraw Rs. 3000: "
                + a1.withdraw(3000));
        System.out.println("Balance: Rs. " + a1.getBalance());

        System.out.println("Withdraw Rs. 10000: "
                + a1.withdraw(10000));
        System.out.println("Final balance: Rs. " + a1.getBalance());
    }
}
