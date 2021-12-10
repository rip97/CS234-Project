/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccount;

/*
 * @author alyazzie
 */
import java.util.ArrayList;

import java.util.*;
import java.io.*;

public class MainAccount {

    public static void main(String[] args) {
           
        File bankCsv = new File("C:\\Data\\BankAccounts.csv");
        File holdersCsv = new File("C:\\Data\\AccountHolders.csv");
        
        String acctHolderFileName = holdersCsv.getPath();
        String bankAcctFileName = bankCsv.getPath();

        //Database objects 
        DBAccount dbAccount = new DBAccount(acctHolderFileName);
        DBBankAcct bank = new DBBankAcct(bankAcctFileName);
        
        // data structure for account holder
        ArrayList<AccountHolder> accountHolders = new ArrayList<>();

        //Create an ArrayList of Bank Account objects
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        //Hash Map to tie AccountNumber to AccountHolder
        Map<Integer, ArrayList<BankAccount>> acctdef = new TreeMap<>();

        // read data from accounts
        dbAccount.read(accountHolders);
        bank.read(bankAccounts);

        //  ties account holder with thier assicoated bank accounts
        tieAccounts(accountHolders, bankAccounts, acctdef);
        
        // launch GUI 
        new BankAccountGUI(dbAccount,bank,accountHolders,bankAccounts,acctdef).setVisible(true);

    }
    // updates the tree map
    private static void tieAccounts(ArrayList<AccountHolder> accountHolders, ArrayList<BankAccount> bankAccounts, Map<Integer, ArrayList<BankAccount>> acctDef) {
        for (AccountHolder holder : accountHolders) {
            ArrayList<BankAccount> tiedAccounts = searchBankAccountMap(bankAccounts, holder.getCustomerId());
            acctDef.put(holder.getCustomerId(), tiedAccounts);
        }
    }
    
   // Search Bank Account method, returns the account
    private static BankAccount searchBankAccounts(ArrayList<BankAccount> bankAccounts, int accountNumber) {
        for (BankAccount bankAccount: bankAccounts) {
            if (bankAccount.getAccountNumber()== accountNumber) {
                return bankAccount;
            }
        }
        return null;
    }


    // Search Bank Account method, returns array of bank accounts
    private static ArrayList<BankAccount> searchBankAccountMap(ArrayList<BankAccount> bankAccounts, int holderID) {
        ArrayList<BankAccount> tiedAccounts = new ArrayList<>();
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.getHolderID() == holderID)
                tiedAccounts.add(bankAccount);
        }
        return tiedAccounts;
    }

    /*
     * Uses the Search Bank Account Method to ensure correct account is pulled
     * Calls deposit method from sub classes.
     */
    public static void initializeDeposit(ArrayList<BankAccount> bankAccounts, int count, Scanner userInput, DBBankAcct bank) {
        System.out.println("Please enter your account number: ");
        int accountNumber = userInput.nextInt();

        BankAccount account = searchBankAccounts(bankAccounts,accountNumber);

        if (account != null) {
            System.out.println("Enter the amount you would like to deposit: ");
            double amount = userInput.nextDouble();
            account.deposit(amount);

            bank.update(accountNumber, account.getBalance());
            bank.read(bankAccounts);

        } else {
            System.out.println("Cannot find account with that account number. AcctNo: " + accountNumber);
        }
    }

    /*
     * Uses the Search Bank Account Method to ensure correct account is pulled
     * Calls withdraw method from sub classes.
     */
    public static void initializeWithdraw(ArrayList<BankAccount> bankAccounts, int count, Scanner userInput, DBBankAcct bank) {
        System.out.println("Please enter your account number: ");
        int accountNumber = userInput.nextInt();

        BankAccount account = searchBankAccounts(bankAccounts,accountNumber);

        if (account != null) {
            if (account instanceof Traditional) {
                ((Traditional) account).checkRequiredAge();
                System.out.println("Enter the amount you would like to withdraw: ");
                double amount = userInput.nextDouble();
                account.withdraw(amount);

                bank.update(account.getAccountNumber(), account.getBalance());
                bank.read(bankAccounts);

            } else {
                System.out.println("Enter the amount you would like to withdraw: ");
                double amount = userInput.nextDouble();
                account.withdraw(amount);

                bank.update(accountNumber, account.getBalance());
                bank.read(bankAccounts);
            }
        } else {
            System.out.println("Cannot find account with that account number. AcctNo: " + accountNumber);
        }
    }

    /*
     * Search Account Holders method
     */
    public static AccountHolder searchAccountHolders(ArrayList<AccountHolder> accountHolders, int customerId) {
        for (AccountHolder holder: accountHolders) {
            if (holder.getCustomerId() == customerId) {
                return holder;
            }
        }
        return null;
    }

    /*
     * Update Account Holders prompt
     */
    public static int updateAccountHolderPrompt(Scanner userInput) {
        System.out.println("What would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Address");
        int option;
        do {
            System.out.println("Select option from menu above...");
            option = userInput.nextInt();
            userInput.nextLine();
        } while (option < 1 || option > 3);

        return option;
    }


    /*
     * Update Account Holders based on selection from updateAccountHolder propmt
     * Uses the AccountHolder search method based on CustomerId entry.
     */
    public static void updateAccountHolderInfo(ArrayList<AccountHolder> accountHolders, int count, Scanner userInput, DBAccount conn) {
        int customerId;
        int option = updateAccountHolderPrompt(userInput);

        Scanner in = new Scanner(System.in);

        if (option == 1) { //Update AccountHolder Name
            System.out.println("Please enter your CustomerID:");
            customerId = userInput.nextInt();
            AccountHolder accountHolder = searchAccountHolders(accountHolders,customerId);
            if (accountHolder != null) {
                System.out.println("Please enter your First Name: ");
                String firstName = in.nextLine();
                System.out.println("Please enter your Last Name: ");
                String lastName = in.nextLine();

                accountHolder.setFirstName(firstName);
                accountHolder.setLastName(lastName);

                conn.update(customerId, accountHolder);
                conn.read(accountHolders);

                System.out.println("SUCCESS! Your account has been updated!");
                System.out.println(accountHolder.toString());

            } else {
                System.out.println("Cannot find Customer with Id: " + customerId);
            }
        } else {
            //Update AccountHolder Address
            System.out.println("Please enter your CustomerID:");
            customerId = userInput.nextInt();
            AccountHolder accountHolder = searchAccountHolders(accountHolders,customerId);
            if (accountHolder != null) {
                System.out.println("Please enter the Street Name: ");
                String street = in.nextLine();
                System.out.println("Please enter the City: ");
                String city = in.nextLine();
                System.out.println("Please enter the State Name: ");
                String state = in.nextLine();
                System.out.println("Please enter the Zip Code: ");
                int zip = in.nextInt();

                accountHolder.setStreet(street);
                accountHolder.setCity(city);
                accountHolder.setState(state);
                accountHolder.setZip(zip);

                conn.update(customerId, accountHolder);
                conn.read(accountHolders);
                System.out.println("SUCCESS! Your account has been updated!");
                System.out.println(accountHolder.toString());

            } else {
                System.out.println("Cannot find Customer with Id: " + customerId);
            }
        }
    }



    public static void printBankAccounts(Map<Integer, ArrayList<BankAccount>> acctTie, ArrayList<AccountHolder> holders, ArrayList<BankAccount> accounts,Scanner userInput) {

        // ask user for input
        System.out.println("Please enter your account holder id: ");
        int userId = userInput.nextInt();

        AccountHolder holder = searchAccountHolders(holders, userId);
        if (holder != null) {

            System.out.println(holder.toString());

            int numOfAccounts = -1;
            if (acctTie.get(holder.getCustomerId()).isEmpty()) {
                numOfAccounts = 0;
                System.out.printf("%s %s, you have %d accounts with the Java Bank.", holder.getFirstName(), holder.getLastName(), numOfAccounts);
            }
            else
            {
                numOfAccounts = acctTie.get(holder.getCustomerId()).size();
                System.out.printf("%s %s, you have %d account(s) with the Java Bank. They are listed below:\n", holder.getFirstName(), holder.getLastName(), numOfAccounts);
                for (BankAccount account: acctTie.get(holder.getCustomerId())) {

                    System.out.println(account.toString());
                }
            }
        }
    }

    public static void closeBankAccount(ArrayList<AccountHolder> accountHolders, ArrayList<BankAccount> bankAccounts, Map<Integer, ArrayList<BankAccount>> acctDef, int count, Scanner userInput, DBBankAcct bankAcct) {
        System.out.println("Please enter your CustomerId: ");
        int customerId = userInput.nextInt();

        AccountHolder holder = searchAccountHolders(accountHolders,customerId);

        if (holder != null) {
            System.out.println("Please enter the account number you would like to close: ");
            int accountNumber = userInput.nextInt();

            BankAccount account = searchBankAccounts(bankAccounts,accountNumber);

            if (account != null) {
                // remove account from data file
                bankAcct.delete(account.getAccountNumber());
                bankAcct.read(bankAccounts);

                //  ties account holder with thier assicoated bank accounts
                tieAccounts(accountHolders, bankAccounts, acctDef);

                //Notify use that Account has been closed
                System.out.println("Account Number: " + accountNumber + " has been closed.");


                //This block creates another String Array to split Values from HashMap linked to AccountHolderID
                //String[] acctDefValue = acctDef.get(customerId).split(",");

                /*//Create another array to trim whitespace around the values in the array
                String[] trimmedAcctNums = new String[acctDefValue.length];
                for (int i = 0; i < acctDefValue.length; i++) {
                    trimmedAcctNums[i] = acctDefValue[i].trim();
                }

                //Moves values from Array into an ArrayList for easier removal
                List<String> mapValues = new ArrayList<>(); //Arrays.asList(trimmedAcctNums);
                Collections.addAll(mapValues, trimmedAcctNums);

                int locateIndex = findIndex(mapValues, mapValues.size(), accountNumber);
                mapValues.remove(locateIndex);

                String updatedAccountNum = mapValues.toString();

                //Notify use that Account has been closed
                System.out.println("Account Number: " + accountNumber + " has been closed.");
                bankAccounts.remove(searchKey2);

                //Update AccountHolder to BankAccount Association in the HashMap
                acctDef.put(customerId, updatedAccountNum.substring(1, updatedAccountNum.length() - 1));*/
            } else {
                //System.out.println("That Account Number is not associated with this Account Holder.");
                System.out.println("Cannot find account with that account number. AcctNo: " + accountNumber);
            }
        } else {
            System.out.println("Cannot find Customer with Id: " + customerId);
        }
    }

    /*
     * Delete Account Holders + Associated Bank Accounts
     */
    public static void deleteAccountHolder(ArrayList<AccountHolder> accountHolders, ArrayList<BankAccount> bankAccounts, Map<Integer, ArrayList<BankAccount>> acctDef, int count, Scanner userInput, DBAccount conn, DBBankAcct bankAcct) {
        System.out.println("Please enter your CustomerId: ");
        int customerId = userInput.nextInt();

        AccountHolder holder = searchAccountHolders(accountHolders,customerId);

       if (holder != null)
       {
           //  remove bank accounts from BankAccounts.csv
           ArrayList<BankAccount> accounts = acctDef.get(holder.getCustomerId());
           StringBuilder acctNumbers = new StringBuilder();
           for(BankAccount account: accounts)
           {
               acctNumbers.append(account.getAccountNumber());
               acctNumbers.append(",");
               bankAcct.delete(account.getAccountNumber());
           }

           // remove account holder from CSV file and tree map
           acctDef.remove(holder.getCustomerId());
           conn.delete(holder.getCustomerId());

           bankAcct.read(bankAccounts);
           conn.read(accountHolders);

           System.out.println("Account has been closed for: " + holder.getFirstName() + " " + holder.getLastName());
           System.out.print("Associated Account Numbers: " + acctNumbers.toString());


//            //Pulls account holder name
//            String accountPerson = accountHolders.get(searchKey).getName();
//
//            //This block creates another String Array to split Values from HashMap linked to AccountHolderID
//            String[] acctDefValue = acctDef.get(customerId).split(",");
//
//            //Create another array to trim whitespace around the values in the array
//            String[] trimmedAcctNums = new String[acctDefValue.length];
//            for (int i = 0; i < acctDefValue.length; i++) {
//                trimmedAcctNums[i] = acctDefValue[i].trim();
//            }
//
//            //Notify the User which their account has been closed and what their account numbers were.
//            System.out.println("Account has been closed for: " + accountPerson);
//            System.out.print("Associated Account Numbers: " + acctDef.get(customerId));
//
//            //Remove Accounts from BankAccounts ArrayList
//            for (int i = 0; i < trimmedAcctNums.length; i++) {
//                int searchaccountNum = searchBankAccounts(bankAccounts, bankAccounts.size(), Integer.parseInt(trimmedAcctNums[i]));
//                bankAccounts.remove(searchaccountNum);
//            }
//
//            //Delete AccountHolder to BankAccount Association in the HashMap
//            acctDef.remove(accountHolders.get(searchKey).getCustomerId());
//
//            //Remove the AccountHolder from the AccountHolder ArrayList
//            accountHolders.remove(searchKey);
        }
        else
        {
            System.out.println("Cannot find Customer with Id: " + customerId);
        }
    }

}//END OF MAIN CLASS*/