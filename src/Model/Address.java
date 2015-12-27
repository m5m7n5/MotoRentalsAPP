package Model;

import Vista.Consola;

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

    /**
     * Show the street of an address.
     */
    public void printStreet() {
        Consola.escriu("Street"+street);
    }
}