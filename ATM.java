package com.Titan.atmapp;

import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
        // init Scanner
        Scanner sc = new Scanner(System.in);

        // init Bank
        Bank theBank = new Bank("Bank of Dayspring");

        // add a user, which also creates a savings account
        User aUser = theBank.addUser("Dayspring", "Idahosa", "1234");
        
        // Print the user ID and pin
        System.out.println("User created with ID: " + aUser.getUUID());
        System.out.println("User pin is: 1234");

        // add a checking account for our user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true) {
            // stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank, sc);
            // stay in the main menu until user quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        String userID;
        String pin;
        User authUser;

        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();

            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID/pin combination. Please try again.");
            }

        } while (authUser == null);

        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc) {
        theUser.printAccountsSummary();

        int choice;

        do {
            System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
            System.out.println(" 1) Show account transaction history");
            System.out.println(" 2) Withdrawal");
            System.out.println(" 3) Deposit");
            System.out.println(" 4) Transfer");
            System.out.println(" 5) Quit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose 1-5.");
            }
        } while (choice < 1 || choice > 5);

        switch (choice) {
            case 1:
                ATM.showTransHistory(theUser, sc);
                break;
            case 2:
                ATM.withdrawFunds(theUser, sc);
                break;
            case 3:
                ATM.depositFunds(theUser, sc);
                break;
            case 4:
                ATM.transferFunds(theUser, sc);
                break;
            case 5:
                sc.nextLine(); // Consume newline left-over
                break;
        }

        if (choice != 5) {
            ATM.printUserMenu(theUser, sc);
        }
    }

    public static void showTransHistory(User theUser, Scanner sc) {
        int theAcct = ATM.getAccount(theUser, sc, "show the transaction history for");

        theUser.printAcctTransHistory(theAcct);
    }

    public static void transferFunds(User theUser, Scanner sc) {
        int fromAcct = ATM.getAccount(theUser, sc, "transfer from");
        int toAcct = ATM.getAccount(theUser, sc, "transfer to");

        System.out.printf("Enter the amount to transfer (max $%.02f): $", theUser.getAcctBalance(fromAcct));
        double amount = sc.nextDouble();

        if (amount < 0) {
            System.out.println("Amount must be greater than zero.");
        } else if (amount > theUser.getAcctBalance(fromAcct)) {
            System.out.printf("Amount must not be greater than balance of $%.02f.\n", theUser.getAcctBalance(fromAcct));
        } else {
            theUser.addAcctTransaction(fromAcct, -1 * amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
            theUser.addAcctTransaction(toAcct, amount, String.format("Transfer from account %s", theUser.getAcctUUID(fromAcct)));
        }
    }

    public static void withdrawFunds(User theUser, Scanner sc) {
        int fromAcct = ATM.getAccount(theUser, sc, "withdraw from");

        System.out.printf("Enter the amount to withdraw (max $%.02f): $", theUser.getAcctBalance(fromAcct));
        double amount = sc.nextDouble();

        if (amount < 0) {
            System.out.println("Amount must be greater than zero.");
        } else if (amount > theUser.getAcctBalance(fromAcct)) {
            System.out.printf("Amount must not be greater than balance of $%.02f.\n", theUser.getAcctBalance(fromAcct));
        } else {
            theUser.addAcctTransaction(fromAcct, -1 * amount, "Withdrawal");
        }
    }

    public static void depositFunds(User theUser, Scanner sc) {
        int toAcct = ATM.getAccount(theUser, sc, "deposit in");

        System.out.print("Enter the amount to deposit: $");
        double amount = sc.nextDouble();

        if (amount < 0) {
            System.out.println("Amount must be greater than zero.");
        } else {
            sc.nextLine(); // consume newline left-over
            System.out.print("Enter a memo: ");
            String memo = sc.nextLine();
            theUser.addAcctTransaction(toAcct, amount, memo);
        }
    }

    private static int getAccount(User theUser, Scanner sc, String action) {
        int acctIdx;
        do {
            System.out.printf("Enter the number (1-%d) of the account to %s: ", theUser.numAccounts(), action);
            acctIdx = sc.nextInt() - 1;
            if (acctIdx < 0 || acctIdx >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (acctIdx < 0 || acctIdx >= theUser.numAccounts());
        return acctIdx;
    }
}
