
package com.mycompany.ensahbank;


public class Customer {
    private final String firstname;
    private final String lastname;
    private final String CIN;
    private final Account account;

    Customer(String firstName, String lastName , String CIN , Account account){
        this.firstname= firstName;
        this.lastname = lastName ;
        this.CIN = CIN ;
        this.account = account;
        System.out.println("your account have being created successfully");
    }

    @Override
    public String toString() {
        return "\n-------- Customer Informations --------\n" +
                "First Name : " + firstname + "\n" +
                "LastName : " + lastname + "\n" +
                "CIN : " + CIN + "\n" +
                account;
    }
    public String basicInfo() {
        return "account number :" + account.getAccountNbr()+ " - name:  "+
                firstname + " " + lastname + "\n" ;

    }
    Account getAccount()
    {
        return account;

    }
}
