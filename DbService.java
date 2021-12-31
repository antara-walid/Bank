
package com.mycompany.ensahbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbService 
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
        int userId = -1 ;
        int accountId = -1;
        Connection connection = connect();
        try {
            connection.setAutoCommit(false);
            // adding the user 
            String addUserSql = "INSERT INTO users (FirstName, LastName, CIN) VALUES(?,?,?)";
            PreparedStatement addUser = connection.prepareStatement(addUserSql ,Statement.RETURN_GENERATED_KEYS);
            addUser.setString(1,firstName);
            addUser.setString(2,lastName);
            addUser.setString(3,CIN);
            addUser.executeUpdate();
            ResultSet addUserResults = addUser.getGeneratedKeys();
            if(addUserResults.next()) 
            {
                userId = addUserResults.getInt(1);
            }
            // adding the account 
            String addAccountSql = "INSET INTO accounts(Balance) VALUES (?)";
            PreparedStatement addAccount = connection.prepareStatement(addAccountSql ,Statement.RETURN_GENERATED_KEYS);
            addAccount.setDouble(1, balance);
            addAccount.executeUpdate();
            ResultSet addAccountResults = addAccount.getGeneratedKeys();
            if(addAccountResults.next())
            {
                accountId = addAccountResults.getInt(1);
            }
            // Linking User to Account using the mapping table 
            if(userId > 0 && accountId > 0)
            {
                String linkAccountsql = "INSERT INTO mappings (UserId, AccountId) values (?,?)";
                PreparedStatement linkAccount = connection.prepareStatement(linkAccountsql);
                linkAccount.setInt(1,userId);
                linkAccount.setInt(2, accountId);
                linkAccount.executeUpdate();
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
       Customer GetAccount (int accountID)
       {
           Customer customer = null ;
           Connection connection = connect();
           findUserSql = "SELECT FirstName"
           PreparedStatement findUser = connection.prepareStatement(findUserSql);
       }
    //Update 
    //Delete 
    
    
}
