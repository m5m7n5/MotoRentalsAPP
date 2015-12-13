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
public class Address {
    private String street;
    private String city;
    private int number;
    private int stair;
    private int door;
    private int PC;

    public Address(String street) {
        this.street=street;
    }

    void printStreet() {
        Consola.escriu("Street"+street);
    }
}