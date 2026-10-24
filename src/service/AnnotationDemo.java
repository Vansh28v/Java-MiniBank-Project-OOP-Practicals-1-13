
package service;

import model.Account;
import util.AnnotationValidator;

public class AnnotationDemo {
    public static void main(String[] args) {

        Account validAccount = new Account(5000, 4.0);
        Account invalidAccount = new Account(-100, 4.0);

        System.out.println("Valid account:");
        String[] validErrors =
                AnnotationValidator.validate(validAccount);

        if (validErrors.length == 0) {
            System.out.println("No validation errors.");
        } else {
            for (String error : validErrors) {
                System.out.println(error);
            }
        }

        System.out.println("\nInvalid account:");
        String[] invalidErrors =
                AnnotationValidator.validate(invalidAccount);

        for (String error : invalidErrors) {
            System.out.println(error);
        }
    }
}
