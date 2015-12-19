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
}
