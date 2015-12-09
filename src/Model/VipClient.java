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
public class VipClient extends Client{
    private int discount;
    
    public VipClient(String user,String password,String id,String name,String surname,String DNI,int numberAdmonish,String address){
        super(user,password,id,name,surname,DNI,numberAdmonish,address);
        discount = 10;
    }
    
}
