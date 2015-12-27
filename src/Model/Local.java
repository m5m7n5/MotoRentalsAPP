package Model;

import Controlador.Motorentals;
import Vista.Consola;
import java.util.ArrayList;

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

    /**
     * Gets a moto from lstMotos giving a index 
     * @param index
     * @return the moto selected
     */
    public Moto getMotoByIndex(int index) {
        int lenght = lstMotos.size();
        index = Motorentals.getInstance().checkNumber(index, lenght);
        return lstMotos.get(index-1);
    }           

    /**
     * Checks if lstMotos is empty.
     * @return true if there aren't any motos in lstMotos, false otherwise.
     */
    public boolean IsEmpty() {
        return lstMotos.size()<5;
    }

    /**
     * Show all the information about a local.
     */
    public void printInfoLocal() {
        add.printStreet();
        Consola.escriu("Max quantity");
        Consola.escriu(maxMoto);
        int size = lstMotos.size();
        Consola.escriu("Current moto quantity");
        Consola.escriu(size);
    }

    /**
     * Check if the list of motos has more motos than the allowed.
     * @return true if the list is full, false otherwise.
     */
    public boolean IsFull() {
        return (lstMotos.size()/(double)maxMoto)>0.75;
    }

    /**
     * Show the motos of lstMotos.
     */
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

    /**
     * Check if the id of the locals are equal or not.
     * @param id
     * @return true if the id of the locals are equal, false otherwise. 
     */
    public boolean compareLocalById(String id) {
        return id.equals(this.idLocal);
    }

    /**
     * Gets a moto from the id.
     * @param moto
     * @return the moto with the id if it is in the list, null otherwise.
     */
    public Moto getMotoById(String moto) {
        for(Moto m:lstMotos){
            if(m.compareMotoById(moto)){
                return m;
            }
        }
        return null;
    }
    
    /**
     * Adds a moto in lstMotos.
     * @param m 
     */
    public void addMoto(Moto m) {
        this.lstMotos.add(m);
    }

    /**
     * Removes a moto from lstMotos.
     * @param m 
     */
    public void removeMoto(Moto m) {
        this.lstMotos.remove(m);
    }

    /**
     * Gets the quantity of motos in lstMotos.
     * @return 
     */
    public int getQuantityMotos() {
        return lstMotos.size();
    }

    /**
     * Gets the available motos from lstMotos.
     * @return a list of the available motos.
     */
    public ArrayList<Moto> getAvailableMotos() {
        ArrayList<Moto> lst = new ArrayList<Moto>();
        for(Moto m:lstMotos){
            if(m.isAvailable()){
                lst.add(m);
            }
        }
        return lst;
    }

    /**
     * Gets the id of the local.
     * @return the id of the local.
     */
    public String getId() {
        return idLocal;
    }
}
