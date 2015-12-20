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
public class Delay {
    private int days;
    private int hours;
    private Price price;

    public Delay(int hours, int days){
        this.hours = hours;
        this.days = days;
        this.price = new Price(2*hours + 48*days);
    }
    
    /**
     * Gives the amount of its price
     * @return int, amount of price.
     */
    public int getPrice(){
        return price.getAmount();
    }
}
