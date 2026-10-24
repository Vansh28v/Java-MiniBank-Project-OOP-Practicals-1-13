
package service;

import model.Account;

public class ThreadSafetyDemo {
    public static void main(String[] args)
            throws InterruptedException {

        Account account = new Account(0, 4.0);

        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(
                new AccountWorker(account, 1000, 1),
                "worker-" + i
            );

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(
            "Expected balance: Rs. 10000"
        );

        System.out.println(
            "Final balance: Rs. " + account.getBalance()
        );
    }
}
