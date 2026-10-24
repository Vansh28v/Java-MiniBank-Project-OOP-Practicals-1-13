
package service;

import model.Account;

public class TransactionProcessorDemo {
    public static void main(String[] args)
            throws InterruptedException {

        Account account = new Account(0, 4.0);
        TransactionProcessor processor =
                new TransactionProcessor();

        for (int i = 1; i <= 10; i++) {
            final int taskNumber = i;

            processor.submit(() -> {
                account.deposit(100);

                System.out.println(
                    "Transaction " + taskNumber
                    + " processed by "
                    + Thread.currentThread().getName()
                );
            });
        }

        processor.stop();

        System.out.println("Final balance: Rs. "
                + account.getBalance());
    }
}
