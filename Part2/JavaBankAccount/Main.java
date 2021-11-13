import java.util.ArrayList;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Variables
        String firstName, lastName, street, city, state;
        int zip, customerId;

        //Variable for Scanner object
        int option;
        int existCustomerMenu;

        //Instantiate Scanner objects
        Scanner userInput = new Scanner(System.in);

        // data structure for account holder
        ArrayList<AccountHolder> accountHolders = new ArrayList<>();

        //Create an ArrayList of Bank Account objects
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        //Hash Map to tie AccountNumber to AccountHolder
        Map<Integer, String> acctdef = new TreeMap<>();

        // create a method that will create 3 default bank account objects
        AccountHolder holder1 = new AccountHolder("John", "Smith", "100 North Ave", "Rio Rancho", "NM", 87124);
		IRA ira = new Roth(70000,"1990-10-09", 14000, holder1.getName(), 15000);
		accountHolders.add(holder1);
		bankAccounts.add(ira);
		acctdef.put(holder1.getCustomerId(), String.valueOf(ira.getAccountNumber()));
		System.out.println();
		System.out.println("*********************************");
		System.out.println("Your Customer Number is: " + holder1.getCustomerId());
		System.out.println("Please keep this number in your records!");
		System.out.println();

		AccountHolder holder2 = new AccountHolder("Alan", "Turing", "", "", "", 00000);
		Savings savings = new Savings(50);
		accountHolders.add(holder2);
		bankAccounts.add(savings);
		acctdef.put(holder2.getCustomerId(), String.valueOf(savings.getAccountNumber()));
		System.out.println();
		System.out.println("*********************************");
		System.out.println("Your Customer Number is: " + holder2.getCustomerId());
		System.out.println("Please keep this number in your records!");
		System.out.println();

		AccountHolder holder3 = new AccountHolder("Bob", "Ross", "900 Painter Road", "Albuquerque", "NM",87114);
		BankAccount checking = new Checking(100);
		accountHolders.add(holder3);
		bankAccounts.add(checking);
		acctdef.put(holder3.getCustomerId(), String.valueOf(checking.getAccountNumber()));
		System.out.println();
		System.out.println("*********************************");
		System.out.println("Your Customer Number is: " + holder3.getCustomerId());
		System.out.println("Please keep this number in your records!");
		System.out.println();


        // write a greeting
        do {
            option = writeGreeting(userInput);
            System.out.println();
            if (option == 1) { //New Customer
                System.out.println("Please enter your First Name: ");
                firstName = userInput.nextLine();
                System.out.println("Please enter your Last Name: ");
                lastName = userInput.nextLine();
                System.out.println("Please enter your Street Address: ");
                street = userInput.nextLine();
                System.out.println("Please enter the city: ");
                city = userInput.nextLine();
                System.out.println("Please enter the state: ");
                state = userInput.nextLine();
                System.out.println("Please enter your zipcode: ");
                zip = userInput.nextInt();

                //Creates AccountHolder based on User Entry
                AccountHolder newAcct = new AccountHolder(firstName, lastName, street, city, state, zip);
                accountHolders.add(newAcct);
                customerId = newAcct.getCustomerId();

                //Creates new BankAccount
                BankAccount newAccount = createAccount(userInput);
                bankAccounts.add(newAccount);
                
                System.out.println();
                System.out.println();
                System.out.println("*********************************");
                System.out.println("Your Customer Number is: " + customerId);
                System.out.println("Please keep this number in your records!");
                System.out.println();
                
                //Ties Bank Account to Account Holder
                acctdef.put(newAcct.getCustomerId(), String.valueOf(newAccount.getAccountNumber()));
            }
            else if (option == 2) { //Existing Customer
                existCustomerMenu = bankMenu(userInput);
                System.out.println();
                if (existCustomerMenu == 1) {
                    //Create New Account
                    System.out.println("Please enter your CustomerID:");
                    customerId = userInput.nextInt();
                    int searchKey = searchAccountHolders(accountHolders, accountHolders.size(), customerId);
                    if (searchKey >= 0) {
                        BankAccount addAcct = createAccount(userInput);
                        bankAccounts.add(addAcct);
                        //This block updates the Values for existing keys to keep track of AccountHolders with multiple accounts.
                        if (acctdef.containsKey(customerId)) {
                            if(acctdef.get(customerId).equals(""))
                                acctdef.replace(customerId, String.valueOf(addAcct.getAccountNumber()));
                            else
                                acctdef.put(customerId, acctdef.get(customerId) + ", " + String.valueOf(addAcct.getAccountNumber()));
                        }
                    } else {
                        System.out.println("Cannot find Customer with Id: " + customerId);
                    }
                } else if (existCustomerMenu == 2) { //Deposit
                    initializeDeposit(bankAccounts, bankAccounts.size(), userInput);
                } else if (existCustomerMenu == 3) { //Withdraw
                    initializeWithdraw(bankAccounts, bankAccounts.size(), userInput);
                } else if (existCustomerMenu == 4) { //Update Account Information
                    updateAccountHolderInfo(accountHolders, accountHolders.size(), userInput);
                } else if (existCustomerMenu == 5) { //Close Single Account
                    closeBankAccount(accountHolders, bankAccounts, acctdef, bankAccounts.size(), userInput);
                } else if (existCustomerMenu == 6) { //Close All Accounts & Delete Account Holder
                    deleteAccountHolder(accountHolders, bankAccounts, acctdef, accountHolders.size(), userInput);
                }
                else
                    printBankAccounts(acctdef,accountHolders,bankAccounts,userInput);
            }
            else {
                System.out.println("Thanks for visiting Java Bank! Come again!");
            }
            System.out.println();
        } while (option != 3);

    } //End of Main Program

    private static int writeGreeting(Scanner userInput) {
        System.out.println("\nWelcome to the Java Bank!");
        System.out.println("1. New Customer");
        System.out.println("2. Existing Customer");
        System.out.println("3. Quit");

        int option;
        do {
            System.out.println("Select option from menu above...");
            option = userInput.nextInt();
            userInput.nextLine();
        } while (option < 1 || option > 3);

        return option;
    }

    private static int bankMenu(Scanner userInput) {
        System.out.println("What would you like to do?");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Update Account Information");
        System.out.println("5. Close individual Account");
        System.out.println("6. Close All Accounts");
        System.out.println("7. Account Information Report");

        int option;
        do {
            System.out.println("Select option from menu above...");
            option = userInput.nextInt();
            userInput.nextLine();
        } while (option < 1 || option > 7);

        return option;
    }    

    /*
     * Types of Accounts Menu 
     */
    public static int mainAccountMenu(Scanner userInput) {
        System.out.println("\nWhat type of account would you like to open today?");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");
        System.out.println("3. IRA Account");

        int option;
        do {
            System.out.println("Select option from menu above...");
            option = userInput.nextInt();
            //userInput.nextLine();
        } while (option < 1 || option > 3);
        System.out.println();
        
        return option; 
    }

    /*
     * Checking Account submenu
     */
    public static int checkingAccountMenu(Scanner userInput) {
        //local variables
        int option;

        System.out.println("What type of Checking Account would you like to open today?");
        System.out.println("1. Basic Checking Account");
        System.out.println("2. Premium Checking Account");
        System.out.println();
        System.out.println("See account differences below to guide your selection.");
        
        //Create Array list to display Different Types of Checking Accounts.
        ArrayList<String> checkingAccountInfo = new ArrayList<>();
        checkingAccountInfo.add("Basic Checking Account");
        checkingAccountInfo.add("======================");
        checkingAccountInfo.add("$300 Daily Withdrawal Limit");
        checkingAccountInfo.add("$1 Fee on all Transactions");
        checkingAccountInfo.add("");
        checkingAccountInfo.add("Premium Checking Account");
        checkingAccountInfo.add("========================");
        checkingAccountInfo.add("$500 Daily Withdrawal Limit");
        checkingAccountInfo.add("No Transaction Fees");
        checkingAccountInfo.add("*** Both Account Types have a $35 Overdraft Fee ***");
        checkingAccountInfo.add("");

        //Print Information on Checking Account
        for (int i = 0; i < checkingAccountInfo.size(); i++) {
			System.out.println(checkingAccountInfo.get(i));
		}

        do {
            System.out.println("Select option from menu above...");
            option = userInput.nextInt();
            //userInput.nextLine();
        } while (option < 1 || option > 3);

        return option;   
    }    

    /*
     * Savings Account submenu 
     */
    public static int savingsAccountMenu(Scanner userInput) {
        //local variables
        int option;

        System.out.println("What type of Savings Account would you like to open today?");
        System.out.println("1. Standard Savings");
        System.out.println("2. HighYield");
        System.out.println();
        System.out.println("See account differences below to guide your selection.");

        //Create Array list to display Different Types of Savings Accounts.
        ArrayList<String> savingsAccountInfo = new ArrayList<>();
        savingsAccountInfo.add("Standard Savings Account Info");
        savingsAccountInfo.add("$50 minimum deposit");
        savingsAccountInfo.add("0.15% Annual Percentage Yield");
        savingsAccountInfo.add("Monthly withdrawal/transfer limit is 9 per month");
        savingsAccountInfo.add("$5.00 withdrawal fee");
        savingsAccountInfo.add("");
        savingsAccountInfo.add("High Yield Savings Account Info");
        savingsAccountInfo.add("$500 minimum deposit");
        savingsAccountInfo.add("0.40% Annual Percentage Yield");
        savingsAccountInfo.add("Monthly withdrawal/transfer limit is 5 per month");
        savingsAccountInfo.add("$5.00 withdrawal fee");
        savingsAccountInfo.add("");

        //Print Information on Savings Account
        for (int i = 0; i < savingsAccountInfo.size(); i++) {
            System.out.println(savingsAccountInfo.get(i));
        }

        do {
            System.out.println("Select option from menu above...");
            option = userInput.nextInt();
            //userInput.nextLine();
        } while (option < 1 || option > 3);

        return option;   
    }        

    public static int iraAccountMenu(Scanner userInput) {
        //local variables
        int option;

        System.out.println("What type of IRA Account would you like to open today?");
        System.out.println("1. Traditional IRA Account");
        System.out.println("2. Roth IRA Account");

        do {
            System.out.println("Select option from menu above...");
            option = userInput.nextInt();
            //userInput.nextLine();
        } while (option < 1 || option > 3);

        return option;   
    }    

    /*
    Creates the account based on what the user selects (Checking, Savings, or IRA).
    */
    public static BankAccount createAccount(Scanner userInput) {
        //Local Variables to drive how the account is created
        String entry, birthdate;
        double taxAmount;
        double initialDeposit = 0;

        //Instaniate new Scanner objects
        Scanner in = new Scanner(System.in);

        //Instantiate Bank Account object
        BankAccount bankAccount = null;

        int option = mainAccountMenu(userInput);

        if (option == 1) {
            int suboption = checkingAccountMenu(userInput);
            if (suboption == 1) { //Basic Checking
                System.out.println ("Do you have an initial deposit? Y/N");
                entry = in.nextLine();
                if(entry.toLowerCase().equals("y")) {
                    System.out.println("Please enter the amount you would like to deposit: ");
                    initialDeposit = in.nextDouble();
                    System.out.println();
                    bankAccount = new Checking(initialDeposit);
                }
                else {
                    bankAccount = new Checking();
                }
            } else { //Premium Checking
                System.out.println ("Do you have an initial deposit? Y/N");
                entry = in.nextLine();
                if(entry.toLowerCase().equals("y")) {
                    System.out.println("Please enter the amount you would like to deposit: ");
                    initialDeposit = in.nextDouble();
                    System.out.println();
                    bankAccount = new PremiumChecking(initialDeposit);
                }
                else {
                    bankAccount = new PremiumChecking();
                }
            }
        } else if (option == 2) { //Savings Account

            int suboption = savingsAccountMenu(userInput);
            if(suboption == 1) { //Standard Savings
                System.out.println ("Do you have an initial deposit? Y/N");
                entry = in.nextLine();
                if(entry.toLowerCase().equals("y")) {
                    System.out.println("Please enter the amount you would like to deposit: ");
                    initialDeposit = in.nextDouble();
                    System.out.println();
                    bankAccount = new Savings(initialDeposit);
                }
                else {
                    bankAccount = new Savings();
                }
            } else { //High Yield Savings
                System.out.println ("Do you have an initial deposit? Y/N");
                entry = in.nextLine();
                if(entry.toLowerCase().equals("y")) {
                    System.out.println("Please enter the amount you would like to deposit: ");
                    initialDeposit = in.nextDouble();
                    System.out.println();
                    bankAccount = new HighYield(initialDeposit);
                }
                else {
                    bankAccount = new HighYield();
                }
            }

            System.out.println("Savings Account created."); 
         }
         else { //IRA
             String name;
            int suboption = iraAccountMenu(userInput);
            if (suboption == 1) {
                //Traditional
                System.out.println("Please Enter your Full Name: ");
                name = in.nextLine();
                System.out.println("Please Enter your birthdate (yyyy-mm-dd): ");
                birthdate = in.nextLine();
                System.out.println("Please Enter your Total Taxable Income: ");
                taxAmount = userInput.nextDouble();
                System.out.println ("Do you have an initial deposit? Y/N");
                entry = in.nextLine();
                if(entry.toLowerCase().equals("y"))
                {
                    System.out.println("Please enter the amount you would like to deposit: ");
                    initialDeposit = userInput.nextDouble();
                }
                bankAccount = new Traditional(birthdate, taxAmount, name,initialDeposit);
                System.out.println();
            }
            else
            {   //Roth
                System.out.println("Please Enter your birthdate (yyyy-mm-dd):");
                birthdate = in.nextLine();
                System.out.println("Please Enter your full Name: ");
                name = in.nextLine();
                System.out.println ("Do you have an initial deposit? Y/N");
                entry = in.nextLine();
                if(entry.toLowerCase().equals("y"))
                {
                    System.out.println("Please enter the amount you would like to deposit: ");
                    initialDeposit = userInput.nextDouble();
                }
                System.out.println("What is your gross income?");
                int gross = userInput.nextInt();
                System.out.println("Please Enter your Total Taxable Income: ");
                taxAmount = userInput.nextDouble();

                bankAccount = new Roth(gross,birthdate,taxAmount,name, initialDeposit);
                System.out.println();             
            }
         }
        return bankAccount; 
    }

    /*
     * Search Bank Account method, returns index of account
    */
    public static int searchBankAccounts(ArrayList<BankAccount> bankAccounts, int count, int accountNumber) {
        for (int i = 0; i < count; i++) {
            if (bankAccounts.get(i).getAccountNumber() == accountNumber) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Search Bank Account method, returns toString of account
     */
    public static String searchBankAccounts(ArrayList<BankAccount> bankAccounts, int acctNumber)
    {
        for(int i = 0; i < bankAccounts.size(); i++)
        {
            if(bankAccounts.get(i).getAccountNumber() == acctNumber)
                return bankAccounts.get(i).toString();

        }
        return "Account Not Found";
    }

    /*
     * Uses the Search Bank Account Method to ensure correct account is pulled
     * Calls deposit method from sub classes.
     */
    public static void initializeDeposit(ArrayList<BankAccount> bankAccounts, int count, Scanner userInput) {
        System.out.println("Please enter your account number: ");
        int accountNumber = userInput.nextInt();

        int searchKey = searchBankAccounts(bankAccounts, count, accountNumber);

        if (searchKey >= 0) {
            System.out.println("Enter the amount you would like to deposit: ");
            double amount = userInput.nextDouble();
            bankAccounts.get(searchKey).deposit(amount);
        }
        else {
            System.out.println("Cannot find account with that account number. AcctNo: " + accountNumber);
        }
    }

    /*
     * Uses the Search Bank Account Method to ensure correct account is pulled
     * Calls withdraw method from sub classes.
     */    
    public static void initializeWithdraw(ArrayList<BankAccount> bankAccounts, int count, Scanner userInput) {
        System.out.println("Please enter your account number: ");
        int accountNumber = userInput.nextInt();

        int searchKey = searchBankAccounts(bankAccounts, count, accountNumber);

        if (searchKey >= 0) {
            if (bankAccounts.get(searchKey) instanceof Traditional) {
                ((Traditional) bankAccounts.get(searchKey)).checkRequiredAge();
                System.out.println("Enter the amount you would like to withdraw: ");
                double amount = userInput.nextDouble();
                bankAccounts.get(searchKey).withdraw(amount);
            } else
            {
                System.out.println("Enter the amount you would like to withdraw: ");
                double amount = userInput.nextDouble();
                bankAccounts.get(searchKey).withdraw(amount);
            }
        }
        else {
            System.out.println("Cannot find account with that account number. AcctNo: " + accountNumber);
        }
    }

    /*
     * Search Account Holders method 
    */
    public static int searchAccountHolders(ArrayList<AccountHolder> accountHolders, int count, int customerId) {
        for (int i = 0; i < count; i++) {
            if (accountHolders.get(i).getCustomerId() == customerId) {
                return i;
            }
        }
        return -1;
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
    public static void updateAccountHolderInfo(ArrayList<AccountHolder> accountHolders, int count, Scanner userInput) {
        int customerId;
        int option = updateAccountHolderPrompt(userInput);

        Scanner in = new Scanner(System.in);

        if(option == 1){ //Update AccountHolder Name
            System.out.println("Please enter your CustomerID:");
            customerId = userInput.nextInt();
            int searchKey = searchAccountHolders(accountHolders, count, customerId);
            if (searchKey >= 0) { 
                System.out.println("Please enter your First Name: ");
                String firstName = in.nextLine();
                System.out.println("Please enter your Last Name: ");
                String lastName = in.nextLine();  

                accountHolders.get(searchKey).updateAccountHolderName(firstName, lastName);
                System.out.println("SUCCESS! Your account has been updated!");
                System.out.println(accountHolders.get(searchKey).getAccountInfo());
            }
            else {
                System.out.println("Cannot find Customer with Id: " + customerId);
            } 
        }
        else { //Update AccountHolder Address
            System.out.println("Please enter your CustomerID:");
            customerId = userInput.nextInt();
            int searchKey = searchAccountHolders(accountHolders, count, customerId);
            if (searchKey >= 0) { 
                System.out.println("Please enter the Street Name: ");
                String street = in.nextLine();
                System.out.println("Please enter the City: ");
                String city = in.nextLine();  
                System.out.println("Please enter the State Name: ");
                String state = in.nextLine();
                System.out.println("Please enter the Zip Code: ");
                int zip = in.nextInt();  
            
                accountHolders.get(searchKey).updateAddress(street, city, state, zip);
                System.out.println("SUCCESS! Your account has been updated!");
                System.out.println(accountHolders.get(searchKey).getAccountInfo());
            }
            else {
                System.out.println("Cannot find Customer with Id: " + customerId);
            } 
        }
    }


    public static void printBankAccounts(Map<Integer, String> acctTie, ArrayList<AccountHolder> holders, ArrayList<BankAccount> accounts,Scanner userInput) {
        // ask user for input
        System.out.println("Please enter your account holder id: ");
        int userId = userInput.nextInt();
        int holderIndex = -1;

        // print account holder info
        for (int i = 0; i < holders.size(); i++) {
            if (holders.get(i).getCustomerId() == userId) {
                holderIndex = i;
                System.out.println(holders.get(i).getAccountInfo()+"\n");
                break;
            }
        }
		if(holderIndex != -1)
		{
			int numOfAccounts = -1;
			if(acctTie.get(userId).equals(""))
			{
				numOfAccounts = 0;
				System.out.printf("%s, you have %d accounts with the Java Bank.", holders.get(holderIndex).getName(), numOfAccounts);
			}
			else {

				String[] accountNums = acctTie.get(userId).split(", ");
				numOfAccounts = accountNums.length;
				System.out.printf("%s, you have %d account(s) with the Java Bank. They are listed below:\n", holders.get(holderIndex).getName(), numOfAccounts);
				for (int i = 0; i < accountNums.length; i++) {
					String num = accountNums[i];
					int actNum = Integer.parseInt(num);
					System.out.println(searchBankAccounts(accounts, actNum));
				}
			}
		}
		else 
			System.out.println("User with customer ID:" + userId + " does not exist");
    }

    /*
     * Search Array for Bank Account Closure
     * Returns the Index of found value 
     * Parameters is array and value to be searched for
    */
    public static int findIndex(List<String> accountNums, int count, int accountNum) {
        for (int i = 0; i < count; i++) {
            if (accountNums.get(i).contains(String.valueOf(accountNum))) {
                return i;
            }
        }
        return -1;
    } 

    /*
     * Close Bank Account Holders prompt
    */
    public static void closeBankAccount(ArrayList<AccountHolder> accountHolders, ArrayList<BankAccount> bankAccounts, Map<Integer, String> acctDef, int count, Scanner userInput) {
        System.out.println("Please enter your CustomerId: ");
        int customerId = userInput.nextInt();

        int searchKey = searchAccountHolders(accountHolders, count, customerId);

        if (searchKey >= 0) {
            System.out.println("Please enter the account number you would like to close: ");
            int accountNumber = userInput.nextInt();
    
            int searchKey2 = searchBankAccounts(bankAccounts, count, accountNumber);
    
            if (searchKey2 >= 0) {
                //This block creates another String Array to split Values from HashMap linked to AccountHolderID
                String [] acctDefValue = acctDef.get(customerId).split(",");

                //Create another array to trim whitespace around the values in the array
                String [] trimmedAcctNums = new String [acctDefValue.length];
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
                acctDef.put(customerId, updatedAccountNum.substring(1, updatedAccountNum.length()-1));
            }                  
            else {
                //System.out.println("That Account Number is not associated with this Account Holder.");
                System.out.println("Cannot find account with that account number. AcctNo: " + accountNumber);
            }
        }
        else {
            System.out.println("Cannot find Customer with Id: " + customerId);
        } 
    }
    
    /*
     * Delete Account Holders + Associated Bank Accounts
    */
    public static void deleteAccountHolder(ArrayList<AccountHolder> accountHolders, ArrayList<BankAccount> bankAccounts, Map<Integer, String> acctDef, int count, Scanner userInput) {
        System.out.println("Please enter your CustomerId: ");
        int customerId = userInput.nextInt();

        int searchKey = searchAccountHolders(accountHolders, count, customerId);

        if (searchKey >= 0) {
            //Pulls account holder name
            String accountPerson = accountHolders.get(searchKey).getName();

            //This block creates another String Array to split Values from HashMap linked to AccountHolderID
            String [] acctDefValue = acctDef.get(customerId).split(",");

            //Create another array to trim whitespace around the values in the array
            String [] trimmedAcctNums = new String [acctDefValue.length];
            for (int i = 0; i < acctDefValue.length; i++) {
                trimmedAcctNums[i] = acctDefValue[i].trim();
            }

            //Notify the User which their account has been closed and what their account numbers were.
            System.out.println("Account has been closed for: " + accountPerson);
            System.out.print("Associated Account Numbers: " + acctDef.get(customerId));
            
            //Remove Accounts from BankAccounts ArrayList
            for (int i = 0; i < trimmedAcctNums.length; i++) {
                int searchaccountNum = searchBankAccounts(bankAccounts, bankAccounts.size(), Integer.parseInt(trimmedAcctNums[i]));
                bankAccounts.remove(searchaccountNum);
            }
            
            //Delete AccountHolder to BankAccount Association in the HashMap
            acctDef.remove(accountHolders.get(searchKey).getCustomerId()); 
            
            //Remove the AccountHolder from the AccountHolder ArrayList
            accountHolders.remove(searchKey);
        }
        else {
            System.out.println("Cannot find Customer with Id: " + customerId);
        }
    }     

} //END OF MAIN CLASS
