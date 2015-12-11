/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Model.Admin;
import Model.Client;
import Model.Local;
import Model.Manager;
import Model.Moto;
import Model.Person;
import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class Motorentals {
    private static Motorentals instance;
    private ArrayList<Admin> lstAdmin;
    private ArrayList<Manager> lstManager;
    private ArrayList<Client> lstClient;
    private ArrayList<Moto> lstDriving;
    private ArrayList<Local> lstLocal;
    private Person current;
    
    private Motorentals(){
        MotoRentDataManager dm = new MotoRentDataManager();
        
        
    }
    
    public static Motorentals getInstance(){
        if(instance == null){
            return instance = new Motorentals();
        }
        return instance;
    }
    
    public void addClient(Client c) {
       lstClient.add(c);
    }
}
