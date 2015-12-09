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
public class Address {
    private String street;
    private String city;
    private int number;
    private int stair;
    private int door;
    private int PC;

    public Address(String address) {
        this.street=address;
    }
}