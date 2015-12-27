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
public class Model {
    private String name;
    private String fuel;
    private int cylinder;
    private int horsepower;
    private int tank;

    
    public Model(String n, String f, int c, int h, int t){
        name = n;
        fuel = f;
        cylinder = c;
        horsepower = h;
        tank = t;
    }
    
    /**
     * Show the all the information about a model of the moto.
     */
    public void printInfoModel() {
        Consola.escriu("Model name "+name);
        Consola.escriu("Fuel type "+fuel);
        Consola.escriu("Tank capacity "+Integer.toString(tank));
        Consola.escriu("Horsepower "+Integer.toString(horsepower));
        Consola.escriu("Cylinder "+Integer.toString(cylinder));
    }
}
