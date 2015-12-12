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
    /**
     * Method that allows us to compare two clients by their DNI.
     * @param dni
     * @return 
     */
    public boolean compareByDni(String dni){
        return(DNI.endsWith(dni));
    }
    /**
     * Method that returns true if the client has an active reserve or false otherwise.
     * @return 
     */
    public boolean hasActiveReserve(){
        boolean cond = false;
        if (active != null){
            cond = true;
        } 
        return cond;
    }
    
    /**
     * Method that adds an active reserve to the client
     * @param localS
     * @param localE
     * @param date
     * @param cantHoras
     * @param cantDias
     * @param motoreta 
     */
    public void addActiveReserve(Local localS, Local localE, Date date, int cantHoras, int cantDias, Moto motoreta){
        /**
         * Se tendra que modificar pero ahora me da palo.
         * Se tiene que poner el cantHoras, cantDias en función del date dado.
         * Necesitamos un objeto tipo date.
         */
        reserve = new Reserve(localS, localE, date, motoreta);
        active = reserve;
    }
}
