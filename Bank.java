/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ensahbank;

import java.util.ArrayList;


public class Bank {
    private DataBaseService database = new DataBaseService();
    
    int openAccount(String firstName, String lastName, String CIN ,Double balance)
    {
        return database.AddAccount(firstName, lastName, CIN, balance);
        // returns the new account id
    }
    
    // get one account
    Account getAccount (int accountID)
    {
        return database.GetAccount(accountID);
    }
    // get all accounts 
    ArrayList<Account> getAccounts()
    {
        return database.GetAllAccounts();
    }
    // deleting an account 
    boolean closeAccount(int accountID)
    {
        return database.DeleteAccount(accountID);
    }
    
    // withdrawing and depositing  
     public boolean withdraw(int accountID ,double amount) {
         Account account = getAccount(accountID);
        if(amount > account.getBalance() ) {
            System.out.println("you have insufficient funds");
            // thise should be displaye in GUI
        }
        double newBalance = account.getBalance() - amount;
        return database.UpdateAccount(accountID, newBalance);
    }
    public boolean deposit(int accountID ,double amount) {
        Account account = getAccount(accountID);
        if(amount <= 0) {
            System.out.println("You can not deposit negative numbers");
            // this should be displayed in GUI 
            return false;
        }
        else {
            double newBalance = account.getBalance() + amount;
            return database.UpdateAccount(accountID, newBalance);
        }
    }
    


    
}
