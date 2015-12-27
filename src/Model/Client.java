package Model;

import Vista.Consola;
import java.util.ArrayList;
import java.util.Calendar;

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
     * Method that allows us to check if the client can log in, id est, has less than 3 admonishes.
     * @return boolean true if the client can log in, false otherwise
     */
    public boolean canLogIn(){
        return (numberAdmonish < 3);
    }
    /**
     * Method that returns true if the client has an active reserve or false otherwise.
     * @return 
     */
    public boolean hasActiveReserve(){
        return (this.active != null);
        
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
    public void addActiveReserve(Local localS, Local localE, Calendar date, int cantHoras, int cantDias, Moto motoreta){
        /**
         * Se tendra que modificar pero ahora me da palo.
         * Se tiene que poner el cantHoras, cantDias en función del date dado.
         * Necesitamos un objeto tipo date.
         */
        Reserve reserve = new Reserve(localS, localE, date, motoreta, cantHoras, cantDias);
        active = reserve;
    }
    
    /**
     * Method that sets a reserve as an active reserve created outside to the client (Used by MotoRentalsXMLDataManager)
     * @param r 
     */
    public void setActiveReserve(Reserve r){
        active = r;
    }
    /**
     * Method that adds a reserve to the done reserve list.
     * @param r 
     */
    public void addReserveDone(Reserve r){
        lstDone.add(r);
    }

    public boolean compareById(String client) {
        return client.equals(id);
    }
    
    public boolean compareCode(String code) {
        boolean check = false;
        if (this.active != null){
            check = this.active.compareCode(code);
        }
        return check;
    }

    public Moto getMotoFromActiveReserve() {
        Moto m = null;
        m = this.active.getMoto();
        return m;
    }

    public Local getLocalSFromActiveReserve() {
        Local l = this.active.getLocalS();
        return l;
        
    }
    
    public Local getLocalEFromActiveReserve() {
        Local lend = null;
        lend = this.active.getLocalE();
        return lend;
    }

    public void admonish() {
        String explicacion;
        int priceAdmonish;
        
        Consola.escriu("Write a brief description about the flaws");
        explicacion = Consola.llegeixString();
        
        Consola.escriu("Insert the price");
        priceAdmonish = Consola.llegeixInt();
        
        Consola.escriu(explicacion);
        
        this.active.addAdmonish(explicacion, priceAdmonish);
        
    }

    public void delay() {
        int numhorastard = 0;
        int numdiastard = 0;
        boolean check = false;
        
        while (!check){
            Consola.escriu("Write how many hours of delay");
            numhorastard = Consola.llegeixInt();
            
            if (numhorastard >= 0){
                check = true;
               
            } else {
                Consola.escriu("Invalid number, please try again");
                numhorastard = Consola.llegeixInt();
            }
        }
        check = false;
        while (!check){
            Consola.escriu("Write how many days of delay");
            numdiastard = Consola.llegeixInt();
            if (numdiastard >= 0){
                check = true;
            } else {
                Consola.escriu("Invalid number, try again");
                numdiastard = Consola.llegeixInt();
            
            }
        }
        this.active.addDelayToReserve(numhorastard, numdiastard);
    }

    public void removeAdmonishFromActiveReserve() {
       this.active.removeAdmonish();
       this.numberAdmonish = this.numberAdmonish - 1;
    }

    public void removeDelayFromActiveReserve() {
        this.active.removeDelay();
    }
    
    /**
     * Method that calculated the total bill of a client given a month and a year.
     * it also prints the client's reserves' information.
     * @param m
     * @param a 
     */
    public void printReservesByMonthYear(int m, int a) {
        int total = 0;
        int amount;
        
        for(Reserve r: lstDone){
            amount = r.printReserveByMontYear(m, a);
            total += amount;
        }
        
        Consola.escriu("Month bill: ");
        Consola.escriu(total);
    }
    
    public void printActiveReserve() {
        this.active.printInfoReserve();
    }
    
    public void printDNI(){
        Consola.escriu("DNI: "+this.DNI);
    }

    public void updateAdmonish(int falta) {
        numberAdmonish += falta;
    }

    public String getId() {
        return id;
    }

    public void setCodeToActiveReserve(String code) {
        active.setCode(code);
    }
}
