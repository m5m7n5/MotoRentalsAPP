/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Vista.Consola;
import java.util.Calendar;

/**
 *
 * @author Marcos
 */
public class Reserve {
    private String code;
    private Local arrival;
    private Local start;
    private Moto moto;
    private StatusReserve status;
    private Admonish admonish;
    private Price price;
    private Delay delay;
    private Calendar startDate;
    private Calendar endDate;
    
    public Reserve (Local localS, Local localA, Calendar dateS, Calendar dateE, Moto moto){
        start = localS;
        arrival = localA;
        startDate = dateS;
        endDate = dateE;
        this.moto = moto;
        long s = startDate.getTimeInMillis();
        long e = endDate.getTimeInMillis();
        e = e-s;
        e=e/1000;
        e=e/60;
        int h = (int) ((int)(e/60)%60);
        int d = (int) (e/3600);
        price = new Price(2*h+48*d);
    }
    
    public Reserve (Local localS, Local localA, Calendar dateS, Moto moto,int cantHoras,int cantDias){
        start = localS;
        arrival = localA;
        startDate = dateS;
        endDate = Calendar.getInstance();
        endDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH)+cantDias, startDate.get(Calendar.HOUR_OF_DAY)+cantHoras, 0);
        this.moto = moto;
        price = new Price(2*cantHoras+48*cantDias);
    }

    public boolean compareCode(String code) {
        return (code == this.code);
    }

    public Moto getMoto() {
        return this.moto;
    }

    public Local getLocalE() {
        return this.arrival;
    }

    public void addAdmonish(String explicacion, int priceAdmonish) {
        this.admonish = new Admonish(explicacion, priceAdmonish);
    }

    public void printInfoReserve() {
        this.start.printInfoLocal();
        this.arrival.printInfoLocal();
        this.moto.printInfoMoto();
        
        Consola.escriu(this.startDate);
        Consola.escriu(this.endDate);
        
    }

    public void addDelayToReserve(int numhorastard, int numdiastard) {
        this.delay = new Delay(numhorastard, numdiastard);
    }

    public void removeAdmonish() {
        this.admonish = null;
    }

    public void removeDelay() {
        this.delay = null;
    }
    
    public int printReserveByMontYear(int m, int a){
        boolean check;
        int priceD, priceA, total;
        priceD = 0;
        priceA = 0;
        total = 0;
        
        //Check if the end date has the same month and year
        //Months are indexed from 0 to 11 in java, so we have to add 1 to de date we obtain
        check = (endDate.get(Calendar.MONTH) + 1 == m) && (endDate.get(Calendar.YEAR) == a);
        if (check){
            //This function is used in much other cases, so it is not a support function.
            //But it's useful here, so we use the same function instead of writing more prints.
            printInfoReserve();
            if (delay != null){
                Consola.escriu("This reserve has had a delay");
                priceD = delay.getPrice();
                Consola.escriu("The cost of the delay is: ");
                Consola.escriu(priceD);
            } else {
                Consola.escriu("This reserve hasn't had delay");
            }
            
            if (admonish != null){
                Consola.escriu("The client returned the moto with flaws");
                priceA = admonish.getPrice();
                Consola.escriu("The cost of the admonish is: ");
                Consola.escriu(priceA);
            }
            total = this.price.getAmount();
            total = total + priceA + priceD;
            Consola.escriu("total reserve cost: ");
            Consola.escriu(total);
        }
        //If this reserve hasn't happened in the given month/year
        //we will return a 0, so it's ok.
        return total;
    }
}
