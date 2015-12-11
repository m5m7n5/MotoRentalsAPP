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
    private static Motorentals instance = null;
    private ArrayList<Admin> lstAdmin;
    private ArrayList<Manager> lstManager;
    private ArrayList<Client> lstClient;
    private ArrayList<Moto> lstDriving;
    private ArrayList<Local> lstLocal;
    private Person current;
    
    private Motorentals(){
        lstAdmin = new ArrayList<Admin>();
        lstManager = new ArrayList<Manager>();
        lstClient = new ArrayList<Client>();
        lstDriving =  new ArrayList<Moto>();
        lstLocal = new ArrayList<Local>();
        current = null;
    }
    
    public static Motorentals getInstance(){
        if(instance == null){
            instance = new Motorentals();
            MotoRentDataManager dm = new MotoRentDataManager();
            dm.obtenirDades("data/MotoRent.xml");
        }
        return instance;
    }
    
    public void addClient(Client c) {
       lstClient.add(c);
    }
    
    public void run(){
        this.selectOptionMenuUser();
    }
    
    private void selectOptionMenuUser(){
        
    }
}
