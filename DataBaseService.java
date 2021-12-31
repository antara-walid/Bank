
        
package com.mycompany.ensahbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBaseService 
{
    String url="jdbc:mysql://localhost:3306/bankdb";
    String user = "bank";
    String password = "securepassword";
    
    // methode to handle connexion 
    private Connection connect(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException ex) {
            connection = null;
        }
        return connection;
    }
    
    // Create 
    int AddAccount(String firstName, String lastName, String CIN ,Double balance)
    {
        int accountId = -1;
        Connection connection = connect();
        try {
            connection.setAutoCommit(false);
            // adding the Account
            String addAccountSql = "INSERT INTO accounts (FirstName, LastName, CIN,Balance) VALUES(?,?,?,?)";
            PreparedStatement addAccount = connection.prepareStatement(addAccountSql ,Statement.RETURN_GENERATED_KEYS);
            addAccount.setString(1,firstName);
            addAccount.setString(2,lastName);
            addAccount.setString(3,CIN);
            addAccount.setDouble(4, balance);
            addAccount.executeUpdate();
            ResultSet addAccountResults = addAccount.getGeneratedKeys();
            if(addAccountResults.next()) 
            {
                accountId = addAccountResults.getInt(1);
            }
         
            // Commiting manually
            if(accountId > 0)
            {
                connection.commit();
            }
            else 
            {
                connection.rollback();
            }
            connection.close();
            
        } catch (SQLException ex) {
            System.err.println("An error has occured: " + ex.getMessage());
        }
        return accountId;
    }
    //Read 
       Account GetAccount (int accountID)
       {
           Account account = null ;
           Connection connection = connect();
           try{
                String findAccountSql = "SELECT FirstName , LastName, CIN ,Balance FROM accounts WHERE ID = ?";
                PreparedStatement findAccount = connection.prepareStatement(findAccountSql); 
                findAccount.setInt(1, accountID);
                ResultSet findAccountResults = findAccount.executeQuery();
                if(findAccountResults.next())
                {
                    String firstName = findAccountResults.getString("FirstName");
                    String lastName = findAccountResults.getString("LastName");
                    String CIN = findAccountResults.getString("CIN");
                    double balance  = findAccountResults.getDouble("Balance");
                    account = new Account(accountID,firstName,lastName,CIN,balance);
                }
                else 
                {
                    System.err.println("Can't find account for ID :" + accountID);
                }
                
           }
           catch (SQLException ex)
           {
               System.err.println(ex.getMessage());
           }
           return account;
       }
    //Update 
       boolean UpdateAccount(int accountID ,double balance)
       {
           boolean success = false;
           Connection connection = connect();
           try {
               String updateSql = "UPDATE accounts SET Balance = ? WHERE ID = ?";
               PreparedStatement updateBalance = connection.prepareStatement(updateSql);
               updateBalance.setDouble(1,balance);
               updateBalance.setInt(2, accountID);
               updateBalance.executeUpdate();
               success = true;
           }
           catch(SQLException ex)
                   {
                       System.err.println(ex.getMessage());
                   }
           return success;
       }
    //Delete 
    boolean DeleteAccount(int accountID )
       {
           boolean success = false;
           Connection connection = connect();
           try {
               String deleteSql = "DELETE FROM accounts WHERE ID = ?";
               PreparedStatement deleteAccount = connection.prepareStatement(deleteSql);
               deleteAccount.setInt(1, accountID);
               deleteAccount.executeUpdate();
               success = true;
           }
           catch(SQLException ex)
                   {
                       System.err.println(ex.getMessage());
                   }
           return success;
       }
    // Get all accounts 
    ArrayList<Account> GetAllAccounts()
    {
        ArrayList<Account> accounts = new ArrayList<>();
        Connection connection = connect();
        String findAllAccountsSql = "SELECT ID, FirstName , LastName, CIN ,Balance FROM accounts";
        PreparedStatement findAllAccounts;
        try {
            findAllAccounts = connection.prepareStatement(findAllAccountsSql);
         
         ResultSet findAccountResults = findAllAccounts.executeQuery();
                while(findAccountResults.next())
                {
                    String firstName = findAccountResults.getString("FirstName");
                    String lastName = findAccountResults.getString("LastName");
                    String CIN = findAccountResults.getString("CIN");
                    double balance  = findAccountResults.getDouble("Balance");
                    int accountID = findAccountResults.getInt("ID");
                    accounts.add(new Account(accountID,firstName,lastName,CIN,balance));
                }
        } catch (SQLException ex) {
            System.err.println("An error has occured " + ex.getMessage());
        }   
        return  accounts;
    }
}
