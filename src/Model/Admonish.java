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
public class Admonish {
    private String description;
    private Price price;
    
    public Admonish(String description, int price){
        this.description = description;
        this.price = new Price(price);
    }
}
