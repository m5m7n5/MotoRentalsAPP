/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Marcos
 */
public class Person {
    private String userName;
    private String password;
    private String name;
    
    public Person(String userName,String password,String name){
        this.userName = userName;
        this.password = password;
        this.name=name;
    }
  
    /**
     * Checks if the user has a correct username.  
     * @param user
     * @return true if both of usernames are equal, false otherwise.
     */
    public boolean compareByUser(String user){
        return (userName.equals(user));
    }
    
    /**
     * Checck if the password is correct.
     * @param pass
     * @return  true if both passwords are equal, false otherwise.
     */
    public boolean compareByPassword(String pass){
        return (password.equals(pass));
    }
}
