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
public class Reserve {
    private String code;
    private Local arrival;
    private Local start;
    private Moto moto;
    private StatusReserve status;
    private Admonish admonish;
    private Price price;
    private Delay delay;
    private Date startDate;
    private Date endDate;
    
    public Reserve (Local localS, Local localA, Date dateS, Date dateE, Moto moto){
        start = localS;
        arrival = localA;
        startDate = dateS;
        endDate = dateE;
        this.moto = moto;
    }
}
