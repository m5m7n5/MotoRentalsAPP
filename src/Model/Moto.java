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
    
    /**
     * Show all the information about a moto.
     */
    public void printInfoMoto() {
        model.printInfoModel();
        Consola.escriu("License plate:"+ plate);
        Consola.escriu("Color:"+ color);
        status.printStatus();
        Consola.escriu("");
    }

    /**
     * Compares two motos from the id.
     * @param moto
     * @return true if the id's are equal, false otherwise.
     */
    public boolean compareMotoById(String moto) {
        return moto.equals(this.idMoto);
    }
    
    /**
     * Changes the status of a moto to non available.
     */
    public void changeStatusNonAvailable() {
        String desc;
        Consola.escriu("Write a description of the status");
        
        desc = Consola.llegeixString();
        this.status = new MotoStatus(desc);
    }

    /**
     * Changes the status of a moto to available.
     */
    public void changeStatusAvailable() {
        this.status = new AvailableStatus(null);
    }

    /**
     * Check if the moto is available or not.
     * @return true if the moto is available, false otherwise.
     */
    public boolean isAvailable() {
        if(status instanceof AvailableStatus){
            return true;
        }
        return false;
    }

    /**
     * Gets the id of the moto
     * @return the id of the moto
     */
    public String getId() {
        return idMoto;
    }
}
