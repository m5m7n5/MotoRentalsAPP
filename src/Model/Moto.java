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
    private String idMoto;
    private Model model;
    private MotoStatus status;

    public Moto(String id, String matri, String c, String marca, String modelo,Boolean estado,String estadoDesc){
        idMoto = id;
        plate = matri;
        color = c;
        model = new Model(marca+" "+modelo,"",0,0,0);
        if(estado){
            status = new AvailableStatus(estadoDesc);
        }else{
            status = new NonAvailableStatus(estadoDesc);
        }
    }
    
    public void printInfoMoto() {
        model.printInfoModel();
        Consola.escriu("License plate:"+plate);
        Consola.escriu("Color:"+color);
        status.printStatus();
    }

    public boolean compareMotoById(String moto) {
        return moto.equals(this.idMoto);
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

    public boolean isAvailable() {
        if(status instanceof AvailableStatus){
            return true;
        }
        return false;
    }

    public String getId() {
        return idMoto;
    }
}
