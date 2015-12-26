/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controlador.Motorentals;
import Vista.Consola;
import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class Local {
    private String idLocal;
    private int maxMoto;
    private Address add;
    private ArrayList<Moto> lstMotos;

    public Local(String idLocal, int maxMoto, Address add, ArrayList<Moto> lstMotos){
        this.idLocal = idLocal;
        this.maxMoto = maxMoto;
        this.add = add;
        if(lstMotos == null){
            this.lstMotos = new ArrayList<Moto>();
        }else{
            this.lstMotos = lstMotos;
        }
    }

    public Moto getMotoByIndex(int index) {
        int lenght = lstMotos.size();
        index = Motorentals.getInstance().checkNumber(index, lenght);
        return lstMotos.get(index-1);
    }           

    public boolean IsEmpty() {
        return lstMotos.size()<5;
    }

    public void printInfoLocal() {
        add.printStreet();
        Consola.escriu("Max quantity");
        Consola.escriu(maxMoto);
        int size = lstMotos.size();
        Consola.escriu("Current moto quantity");
        Consola.escriu(size);
    }

    public boolean IsFull() {
        return (lstMotos.size()/(double)maxMoto)>0.75;
    }

    public void printMotoList() {
        int i=0;
        for(Moto m: lstMotos){
            i++;
            Consola.escriu("************");
            Consola.escriu("Moto " + i);
            m.printInfoMoto();
            Consola.escriu("************");
        }
    }

    public boolean compareLocalById(String id) {
        return id.equals(this.idLocal);
    }

    public Moto getMotoById(String moto) {
        for(Moto m:lstMotos){
            if(m.compareMotoById(moto)){
                return m;
            }
        }
        return null;
    }
    
    public void addMoto(Moto m) {
        this.lstMotos.add(m);
    }

    public void removeMoto(Moto m) {
        this.lstMotos.remove(m);
    }

    public int getQuantityMotos() {
        return lstMotos.size();
    }

    public ArrayList<Moto> getAvailableMotos() {
        ArrayList<Moto> lst = new ArrayList<Moto>();
        for(Moto m:lstMotos){
            if(m.isAvailable()){
                lst.add(m);
            }
        }
        return lst;
    }

    public String getId() {
        return idLocal;
    }
}
