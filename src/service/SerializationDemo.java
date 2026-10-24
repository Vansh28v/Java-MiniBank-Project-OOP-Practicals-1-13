
package service;

import model.Account;
import java.nio.file.Path;

public class SerializationDemo {
    public static void main(String[] args) {
        try {
            Path file = Path.of("accounts.dat");

            Account account = new Account(5000, 4.0);

            Account[] accounts = {account};

            StatePersister.save(accounts, file);

            Account[] restored = StatePersister.load(file);

            System.out.println("Accounts restored successfully.");
            for (Account a : restored) {
                System.out.println("Account: " + a.getAccountNumber());
                System.out.println("Balance: Rs. " + a.getBalance());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
