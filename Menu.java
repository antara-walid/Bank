/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ensahbank;

import java.util.ArrayList;
import java.util.Scanner;


public class Menu {
     // instance variables
    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;

    public static void main(String[] args)
    {
        Menu menu = new Menu();
        menu.runMenu();
    }
    public void runMenu()
    {
        printHeader();
        while(!exit)
        {
            printMenu();
            int choice = getInput();
            performAction(choice);
        }
    }
    private void performAction(int choice) {
        switch (choice) {
            case 0 -> {
                // exit
                System.out.println("thank you for using Ensah Bank");
                System.exit(0);
            }
            case 1 -> createNewAccount();
            case 2 -> makeADeposit();
            case 3 -> makeAWithdrawal();
            case 4 -> listBalances();
            default -> System.out.println("Unkown error has occured.");
        }

    }
    private void listBalances() {
        displayHeader("List Balance ");
        int account = selectAccount();
        if(account >= 0)
        {
            System.out.println();
            System.out.println( bank.getCustomer(account).getAccount() ) ;
        }


    }
    private void makeAWithdrawal() {
        displayHeader("make a Withdrawal");
        int account = selectAccount();
        if(account >= 0)
        {
            System.out.println("please enter the amount you want to withdraw");
            double amount;
            try {
                amount = Double.parseDouble(keyboard.nextLine());
            }
            catch(NumberFormatException e){
                amount = 0;
            }
            bank.getCustomer(account).getAccount().withdraw(amount);
        }

    }
    private void makeADeposit() {
        displayHeader("Make a Deposit");
        int account = selectAccount();
        if(account >= 0)
        {
            System.out.println("please enter the amount you want to deposit");
            double amount;
            try {
                amount = Double.parseDouble(keyboard.nextLine());
            }
            catch(NumberFormatException e){
                amount = 0;
            }
            bank.getCustomer(account).getAccount().deposit(amount);
        }

    }
    private int selectAccount() {
        ArrayList<Customer> customers = bank.getCustomers();
        if( customers.size() <= 0)
        {
            System.out.println("their is no customer in your bank");
            return -1;
        }
        System.out.println("please select an account: ");
        // displaying the accounts
        for(int i=0 ; i < customers.size() ; i++)
        {
            System.out.println( "\t" + (i+1) +  ") " + customers.get(i).basicInfo());
        }
        // selecting the account
        int account;
        System.out.println1("enter your selection ");
        try {
            account = Integer.parseInt(keyboard.nextLine()) - 1 ;
        }
        catch(NumberFormatException e){
            account = -1;
        }
        if(account < 0 || account > customers.size() )
        {
            System.out.println("invalid account selected");
            account = -1 ;
        }

        return account;
    }
    private String askQuestion(String question)
    {
        String response;
        System.out.println(question);
        Scanner input = new Scanner(System.in);
        response = input.nextLine();
        return response;
    }

    private void createNewAccount() {
        String firstName, lastName , CIN;
        double initialDeposit = 0;
        boolean valid = false;
        displayHeader("Create an Account");

        firstName = askQuestion("please enter your first name:");
        lastName = askQuestion("please enter your last name:");
        CIN = askQuestion("please enter your CIN :");

        // initial deposit must be a number

        while(!valid)
        {
            System.out.println("Please enter your initial deposit:");
            try {
                initialDeposit = Double.parseDouble(keyboard.nextLine());
                valid = true;
            }
            catch(NumberFormatException e){
                System.out.println("please enter a valid number !!");
            }

        }
        // now we create a account
        Account account = new Account(initialDeposit);
        Customer customer = new Customer(firstName,lastName,CIN,account);
        bank.addCustomer(customer);

    }
    private int getInput() {
        // this method get input from the keyboard and store it in choice
        int choice = -1 ;
        do {
            System.out.println("type your choice");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            }
            catch(NumberFormatException e) {
                System.out.println("Please type a valid number.");
            }
            if(choice < 0 || choice > 4)
            {
                System.out.println("choice outside of range. please chose again");
            }
        }while(choice < 0 || choice > 4);
        return choice;
    }
    private void printMenu() {
        displayHeader("Please select an option");
        System.out.println("1. Create a new Account");
        System.out.println("2. Make a deposit");
        System.out.println("3. Make a withdrawal");
        System.out.println("4. List account balance");
        System.out.println("0. Exit");

    }
    private void printHeader() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|             Welcome to Ensah Bank          |");
        System.out.println("|             Our small Bank project         |");
        System.out.println("+--------------------------------------------+");

    }
    private void displayHeader(String message){
        System.out.println();
        int width = message.length() + 6;
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for(int i = 0; i < width ; i++)
        {
            sb.append("-");
        }
        sb.append("+");
        System.out.println(sb);
        System.out.println("|   "+ message +"   |");
        System.out.println(sb);

    }
}
