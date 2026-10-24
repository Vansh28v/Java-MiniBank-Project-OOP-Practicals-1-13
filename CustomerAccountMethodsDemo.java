
class Customer implements Cloneable {
    private static long counter = 100;

    private final String customerId;
    private final String name;
    private final String email;
    private final String mobile;
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static class Address {
        private final String line;
        private final String city;
        private final String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() {
            return line;
        }

        public String getCity() {
            return city;
        }

        public String getPincode() {
            return pincode;
        }

        @Override
        public String toString() {
            return line + ", " + city + " - " + pincode;
        }
    }

    @Override
    public Customer clone() {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;

    Account(String ownerName, long balance, String accountNumber) {
        this.ownerName = ownerName;
        this.balance = balance;
        this.accountNumber = accountNumber;
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

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Account other)) {
            return false;
        }

        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}

public class CustomerAccountMethodsDemo {
    public static void main(String[] args) {
        Customer c1 = new Customer(
            "Riya", "riya@example.com", "9876543210"
        );

        Customer.Address address = new Customer.Address(
            "123 Main Road", "Vadodara", "390001"
        );

        c1.setAddress(address);

        System.out.println("--- Customer Details ---");
        System.out.println("ID: " + c1.getCustomerId());
        System.out.println("Name: " + c1.getName());
        System.out.println("Address: " + c1.getAddress());

        Customer c2 = c1.clone();

        System.out.println("\n--- Cloned Customer ---");
        System.out.println("Name: " + c2.getName());
        System.out.println("ID: " + c2.getCustomerId());

        Account a1 = new Account("Riya", 4000, "AC0001");
        Account a2 = new Account("Riya", 4000, "AC0001");

        System.out.println("\n--- Account Details ---");
        System.out.println(a1);

        System.out.println("Accounts equal: " + a1.equals(a2));
        System.out.println("Same hash code: "
                + (a1.hashCode() == a2.hashCode()));
        System.out.println("Is Account object: "
                + (a1 instanceof Account));
    }
}
