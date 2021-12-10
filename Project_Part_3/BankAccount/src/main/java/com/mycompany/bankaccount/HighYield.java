/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccount;

public class HighYield extends Savings{
    //Interest rate is 0.40%
    private static final double InterestRate = 0.0040;
    private double MinimumDeposit = 500.00;
    private int NumofWithdrawals = 0;
    private int WithdrawalLimit= 5;
    private static final int WithdrawalFee = 5;


    public HighYield() {}

    public HighYield(double initialDeposit, int holderID) {
        super(initialDeposit, holderID);
        /*if(initialDeposit < MinimumDeposit)
        {
            System.out.println("\n The minimum deposit is $500.00, please try again!");

        }
        else
        {
            System.out.println("\nCongratulations on your new Account!!");
            System.out.println(toString());
        }*/
    }

    public void withdrawlimit(int NumofWithdrawals){
        if(WithdrawalLimit >= NumofWithdrawals)
        {
            System.out.println("\n The withdrawalLimit is 5 transactions, please try again next month!");
        }
        else
        {
            System.out.println("You have made " +NumofWithdrawals+ ".");
        }
    }

    public void withdraw(double amount) {
        if (amount > MinimumDeposit)
        {
            System.out.println("Declined. This amount can not surpass minimum deposit");
        }
        else
        {
            if((amount + WithdrawalFee) >= balance)
            {
                System.out.println("Declined. This amount cannot have a zero balance");

            }
            else
            {
                balance -= amount;
                System.out.printf("Withdrawal Amount: $%.2f%n", amount);
                System.out.printf("Transaction Fee: %d.00", WithdrawalFee);
                System.out.printf("Your Account balance: $%.2f%n", balance);
            }
        }

    }

    public void deposit(double amount){
        if(amount<=0)
        {
            System.out.println("Amount to be deposited should be positive");
        }
        else
        {
            balance += amount;
            System.out.printf("You've deposited: $%.2f%n", amount);
            System.out.printf("Your new balance is: $%.2f%n", balance);
        }
    }

    public String toString() {
        return super.toString();
    }
}
