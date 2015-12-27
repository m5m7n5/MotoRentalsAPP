/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Vista.Consola;

/**
 *
 * @author Marcos
 */
public class MotoStatus {
    String description;
    
    public MotoStatus(String description){
        this.description= description;
    }
    
    /**
     * Show the description of the status of a moto.
     */
    public void printStatus() {
        Consola.escriu(this.description);
    }
    
}
