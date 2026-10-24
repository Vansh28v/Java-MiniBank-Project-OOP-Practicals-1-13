
import java.util.regex.Pattern;

enum TransactionType {
    DEPOSIT, WITHDRAW, TRANSFER
}

record Command(
    TransactionType type,
    String accountNumber,
    long amount
) {}

class Validator {
    private static final Pattern MOBILE =
        Pattern.compile("^[6-9][0-9]{9}$");

    private static final Pattern EMAIL =
        Pattern.compile("^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern PAN =
        Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");

    private static final Pattern IFSC =
        Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    static boolean isValidMobile(String s) {
        return MOBILE.matcher(s).matches();
    }

    static boolean isValidEmail(String s) {
        return EMAIL.matcher(s).matches();
    }

    static boolean isValidPan(String s) {
        return PAN.matcher(s).matches();
    }

    static boolean isValidIfsc(String s) {
        return IFSC.matcher(s).matches();
    }
}

class CommandParser {
    static Command parse(String line) {
        String[] parts = line.trim().split("\\s+");

        if (parts.length != 3) {
            throw new IllegalArgumentException(
                "Expected: TYPE ACCOUNT AMOUNT"
            );
        }

        TransactionType type =
            TransactionType.valueOf(parts[0].toUpperCase());

        long amount = Long.parseLong(parts[2]);

        return new Command(type, parts[1], amount);
    }
}

class Account {
    private final String accountNumber;
    private final String ownerName;
    private long balance;

    Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
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
}

class StatementFormatter {
    static String buildStatement(Account account) {
        StringBuilder sb = new StringBuilder();

        sb.append("===== MINIBANK STATEMENT =====\n");
        sb.append("Account Number: ")
          .append(account.getAccountNumber()).append("\n");
        sb.append("Account Holder: ")
          .append(account.getOwnerName()).append("\n");
        sb.append("Balance: Rs. ")
          .append(account.getBalance()).append("\n");
        sb.append("==============================");

        return sb.toString();
    }
}

public class MiniBankValidationDemo {
    public static void main(String[] args) {

        System.out.println("--- Validation ---");
        System.out.println("Valid mobile: "
            + Validator.isValidMobile("9876543210"));
        System.out.println("Invalid mobile: "
            + Validator.isValidMobile("12345"));
        System.out.println("Valid email: "
            + Validator.isValidEmail("riya@example.com"));
        System.out.println("Valid PAN: "
            + Validator.isValidPan("ABCDE1234F"));
        System.out.println("Valid IFSC: "
            + Validator.isValidIfsc("SBIN0001234"));

        System.out.println("\n--- Command Parsing ---");
        Command command =
            CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println(command);

        System.out.println("\n--- Account Statement ---");
        Account account =
            new Account("AC0001", "Riya", 5000);
        System.out.println(
            StatementFormatter.buildStatement(account)
        );
    }
}
