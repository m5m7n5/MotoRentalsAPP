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
public class Moto {
    private String color;
    private String plate;
    private int idMoto;
    private Model model;
    private MotoStatus status;

    public void printInfoMoto() {
        model.printInfoModel();
        Consola.escriu("License plate:"+plate);
        Consola.escriu("Color:"+color);
        status.printStatus();
    }

    public void changeStatusNonAvailable() {
        String desc;
        Consola.escriu("Write a description of the status");
        
        desc = Consola.llegeixString();
        this.status = new MotoStatus(desc);
    }

    public void changeStatusAvailable() {
        this.status = new AvailableStatus(null);
    }
}
