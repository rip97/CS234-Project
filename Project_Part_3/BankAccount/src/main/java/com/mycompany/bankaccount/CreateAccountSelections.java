/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccount;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CreateAccountSelections {
    public static int basicChecking;
    public static int premiumChecking;
    public static int standardSavings;
    public static int highyieldSavings;
    public static int traditionalIRA;
    public static int rothIRA;
    public static String acctHolderFileNameTemp;
    public static String bankAcctFileNameTemp;
    public static int CustomerID;
    public static String accountToString;
    
    
    //Temp ata structure for account holder
    public static ArrayList<AccountHolder> accountHoldersTemp = new ArrayList<>();                

    //Temp Create an ArrayList of Bank Account objects
    public static ArrayList<BankAccount> bankAccountsTemp = new ArrayList<>();
    
    //Temp Hash Map to tie AccountNumber to AccountHolder
    public static Map<Integer, ArrayList<BankAccount>>  acctdefTemp = new TreeMap<>();
    
    //Temp DBAccount and DBBankAcct Objects
    public static DBAccount dbAccountTemp; //= new DBAccount(acctHolderFileNameTemp);
    public static DBBankAcct bankTemp; //= new DBBankAcct(bankAcctFileNameTemp);

    
}
