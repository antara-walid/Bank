/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ensahbank;


public class Account {
    private int accountID ;
    private String firstname;
    private String lastname;
    private String CIN;
    private double balance ;

    

    // constructor
    
    Account(int accountID, String firstname,String lastname,String CIN,double initialDeposit)
    {
        
            this.accountID = accountID;
            this.firstname = firstname;
            this.lastname = lastname;
            this.CIN = CIN; 
            this.setBalance(initialDeposit);
          
    }

    // geters and seters
    public double getBalance() {
        return balance;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountID() {
        return this.accountID;
    }

   

    // overriding the toString method
    @Override
    public String toString() {
        return "Account Number: " + this.getAccountID() +"\n" +
                "Balance: " + this.getBalance() + " DH" + "\n";
    }

}
