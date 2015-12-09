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
public class Staff extends Person{
    private String idStaff;
    
    public Staff(String user,String password,String name,String id){
        super(user,password,name);
        this.idStaff=id;
    
    }
}
