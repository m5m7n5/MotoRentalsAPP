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
    
    public boolean compareByUser(String user){
        return (userName.equals(user));
    }
    
    public boolean compareByPassword(String pass){
        return (password.equals(pass));
    }
}
