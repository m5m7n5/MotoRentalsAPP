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
public class Price {
    private int amount;
    
    
    public Price(int amount){
        this.amount = amount;
    }
    /**
     * Return the amount of the price
     * @return amount
     */
    public int getAmount(){
        return amount;
    }
}
