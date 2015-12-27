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
    private Calendar registerDate;
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

    /**
     * Method that comproves if the id given is correct
     * @param client
     * @return true if both id's are equal, false otherwise.
     */
    public boolean compareById(String client) {
        return client.equals(id);
    }
    
    /**
     * Method that comproves if the code given is correct
     * @param code
     * @return true if both codes are equal, false otherwise.
     */
    public boolean compareCode(String code) {
        boolean check = false;
        if (this.active != null){
            check = this.active.compareCode(code);
        }
        return check;
    }

    /**
     * Method that gets a moto from the active reserve.
     * @return a moto from the active reserve.
     */
    public Moto getMotoFromActiveReserve() {
        Moto m = null;
        m = this.active.getMoto();
        return m;
    }

    /**
     * Method that gets a start local from the active reserve.
     * @return a local from the active reserve.
     */
    public Local getLocalSFromActiveReserve() {
        Local l = this.active.getLocalS();
        return l;
        
    }
    
    /**
     * Method that gets an arrival local from the active reserve.
     * @return a local from the active reserve.
     */
    public Local getLocalEFromActiveReserve() {
        Local lend = null;
        lend = this.active.getLocalE();
        return lend;
    }

    /**
     * Method that gets the active reserve from the client.
     * @return a reserve from the client.
     */
    public Reserve getActiveReserve(){
        return this.active;
    }
    
    /**
     * Method to admonish a client.
     */
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

    /**
     * Method to give a delay to a client.
     */
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

    /**
     * Method to remove an admonish from an active reserve.
     */
    public void removeAdmonishFromActiveReserve() {
       this.active.removeAdmonish();
       this.numberAdmonish = this.numberAdmonish - 1;
    }

    /**
     * Method to remove a delay from an active reserve.
     */
    public void removeDelayFromActiveReserve() {
        this.active.removeDelay();
    }
    
    /**
     * Method to remove an active reserve.
     */
    public void removeActiveReserve() {
        this.active = null;
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
        
        Consola.escriu("Month bill: " +total);
        
    }
    
    /**
     * Method to show al the information about the active reserve.
     */
    public void printActiveReserve() {
        this.active.printInfoReserve();
    }
    
    /**
     * Mehtod to show the DNI.
     */
    public void printDNI(){
        Consola.escriu("DNI: "+this.DNI);
    }

    /**
     * Method to update an admonish of a client.
     * @param falta 
     */
    public void updateAdmonish(int falta) {
        numberAdmonish += falta;
    }

    /**
     * Method to get the id of a client.
     * @return the id of a client
     */
    public String getId() {
        return id;
    }

    /**
     * Method to set the code from an active reserve.
     * @param code 
     */
    public void setCodeToActiveReserve(String code) {
        active.setCode(code);
    }

    public void changeActiveToDone() {
        this.addReserveDone(active);
        this.removeActiveReserve();
    }

    
}
