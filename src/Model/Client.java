/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class Client extends Person{
    private String surname;
    private String DNI;
    private long phone;
    private int numberAdmonish;
    private BankAccount bankAcc;
    private Address address;
    private ArrayList<Reserve> lstDone;
    private Reserve active;
    private Date registerDate;
    private String id;
    
    public Client(String user,String password,String id,String name,String surname,String DNI,int numberAdmonish,String address){
        super(user,password,name);
        this.id = id;
        this.surname = surname;
        this.DNI = DNI;
        this.numberAdmonish = numberAdmonish;
        this.address = new Address(address);
        this.registerDate = registerDate;
        
        this.lstDone = new ArrayList<Reserve>();
        
        this.bankAcc = null;
        this.phone = 666666666;
        this.active = null;
        this.registerDate = null;
    }
}
