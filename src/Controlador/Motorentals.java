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
import Model.Reserve;
import Vista.Consola;
import java.util.ArrayList;
import java.util.Calendar;

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
    
    /*-------------------------------------------------------------*/
    /* -------------------- LogIn & SignUp ------------------------*/
    /*-------------------------------------------------------------*/
    
    private void logIn(){
        boolean check;
        boolean exists = false;
        int tries = 0;
        String user, pass;
        Person pers = null;
        
        /**
         * Trying to select the person by its username.
         * Maximum number of tries: 3.
         */
        
        while (!exists && tries < 3){
            int i;
            Consola.escriu("Insert username: ");
            user = Consola.llegeixString();
            
            //Checking: is our user a client?
            //We made this for because we want to get out if we found the client.
            for (i = 0; i < lstClient.size() && !exists; i++){
                Client c = lstClient.get(i);
                check = c.compareByUser(user);
                if (check){
                    exists = true;
                    pers = c;
                }
            }
            
            //Checking: is our user a manager?
            //Since we made this kind of for loop, if we already found a person we won't enter here.
            for (i = 0; i < lstManager.size() && !exists; i++){
                Manager m = lstManager.get(i);
                check = m.compareByUser(user);
                if (check){
                    exists = true;
                    pers = m;
                }
            }
            
            //Checking: is our user an admin?
            //Same situation as before.
            for (i = 0; i < lstAdmin.size() && !exists; i++){
                Admin a = lstAdmin.get(i);
                check = a.compareByUser(user);
                if (check){
                    exists = true;
                    pers = a;
                }
            }
            
            //If we don't find anything, then the username was incorrect.
            if (!exists){
                Consola.escriu("Invalid username, please try again.");
                tries +=1;
            }
        }
        
        /**
         * If we reached the end, we wither have a person or we have more than 3 tries.
         * More than 3 tries means that exists = false.
         * Let's ask for the password in the case that exists is true.
         */
        
        if (exists){
            exists = false;
            tries = 0; //You can try 3 times for your password.
            
            while (!exists && tries < 3){
                Consola.escriu("Insert password: ");
                pass = Consola.llegeixString();
            
                check = pers.compareByPassword(pass);
            
                if (check){
                    exists = true;
                    Consola.escriu("Correct password");
                } else {
                    Consola.escriu("Incorrect password.");
                    tries +=1;
                }
            }
        }
        
        /**
         * We can have two situations: either we have a correct person (with correct user and password)
         * or we have exceeded the maximum tries permited.
         * If we have a correct person, we show the pertinent menu.
         * If we reach here with more than 3 tries, then you can't log in.
         */
        
        if (pers instanceof Client && tries < 3){
            //Given the client, we have to check if he can logIn
            //id est, if he has less than 3 admonishes.
            if(((Client)pers).canLogIn()){
                //Succeed, the client can log in and is now in use of our application.
                current = pers;
                selectOptionMenuClient();
            } else {
                //Our client can log in =( Inform him/her.
                Consola.escriu("You have 3 admonishes, you can't log in, sorry.");
            }
        } else if (pers instanceof Manager && tries < 3){
            current = pers;
            selectOptionMenuManager();
        } else if (pers instanceof Admin && tries < 3){
            current = pers;
            selectOptionMenuAdmin(); 
        } else {
            Consola.escriu("You've exceeded the maximum permited tries");
        }
           
    }
    
    private void signUp(){
        String name,surname,street,DNI, pobl, email, iban;
        String DNINumber;
        String user,pass1,pass2;
        DNI = null;
        user = null;
        pass1 = null;
        

        
        int dniNumber, tlphNumber, numStreet, numDoor, CP;
        int entity, office, control, accNumber;
        
        
        boolean check = false,exists = false;
        
        Consola.escriu("Real name: ");
        name = Consola.llegeixString();
        
        Consola.escriu("Surname: ");
        surname = Consola.llegeixString();
        
        while (check == false) {
            Consola.escriu("DNI: ");
            DNI = Consola.llegeixString();
            
            DNINumber = DNI.substring(0, DNI.length()-1);
            dniNumber = Integer.parseInt(DNINumber);
            
            if (dniNumber - 100000001  < 0 && dniNumber >= 0 ){
                check = true;
            } else {
                Consola.escriu("Invalid number");
            }
            
        }
        
        check = false;
        while (check == false){
            Consola.escriu("Telephon number: ");
            tlphNumber = Consola.llegeixInt();
            
            if (tlphNumber - 1000000001 < 0 && tlphNumber <= 999999999){
                check = true;
            } else {
                Consola.escriu("Invalid telephon");
            }
            
        }
        check = false;
        Consola.escriu("Street: ");
        street = Consola.llegeixString();
        
        Consola.escriu("Number: ");
        numStreet = Consola.llegeixInt();
        
        Consola.escriu("Door: ");
        numDoor = Consola.llegeixInt();
        
        Consola.escriu("Postal code: ");
        CP = Consola.llegeixInt();
        
        Consola.escriu("Poblation: ");
        pobl = Consola.llegeixString();
        
        Consola.escriu("E-mail: ");
        email = Consola.llegeixString();
        
        while (check == false){
            Consola.escriu("Write the entity of your bank account: ");
            entity = Consola.llegeixInt();
            if (entity >= 0 && entity <= 9999){
                check = true;
            } else{
                Consola.escriu("Invalid entity");
            }   
            
        }
        check = false;
        while (check == false){
            Consola.escriu("Write the office of your bank account: ");
            office = Consola.llegeixInt();
            if (office >= 0 && office <= 9999){
                check = true;
            } else{
                Consola.escriu("Invalid office");
            }   
            
        }
        check = false;
        while (check == false){
            Consola.escriu("Write the DC of your bank account: ");
            control = Consola.llegeixInt();
            if (control >= 0 && control <= 99){
                check = true;
            } else{
                Consola.escriu("Invalid DC");
            }   
            
        }
        check = false;
        while (check == false){
            Consola.escriu("Write the account number of your bank account: ");
            accNumber = Consola.llegeixInt();
            if (accNumber >= 0 && accNumber <= 999999999*10+9){
                check = true;
            } else{
                Consola.escriu("Invalid account number");
            }   
            
        }
        check = false;
        for(int i = 0; i< this.lstClient.size() && check == false; i++){
            check = this.lstClient.get(i).compareByDni(DNI);
            if (check == true) {
                Consola.escriu("You have already have an acoount");
            }
        }
        
        if (check == false){
            while (check == false){
                Consola.escriu("Username: ");
                user = Consola.llegeixString();
                exists = false;
                for(int i = 0; i< this.lstClient.size() && check == false; i++){
                    exists = this.lstClient.get(i).compareByUser(user);
                    if (exists == true){
                        Consola.escriu("This username already exists");
                    }
                }
                check = !exists;
            }
            
            check = false;
            while (check == false) {
                Consola.escriu("Password: ");
                pass1 = Consola.llegeixString();

                Consola.escriu("Write your password again: ");
                pass2 = Consola.llegeixString();

                if (pass1.equals(pass2)){
                    check = true;
                } else {
                    Consola.escriu("Your password does not match");
                }
            }

            String id = "c" + Integer.toString(this.lstClient.size()+1);

            Client newClient = new Client(user,pass1,id,name,surname,DNI,0,street);
            this.lstClient.add(newClient);
            Consola.escriu("Correctly registered ");
            Consola.escriu(user);
        }
    }
    
    /**
     * Admin method. It allows the admin to recieve a moto from a client.
     */
    public void pickUpMoto(){
        String code,answer;
        boolean check = false;
        boolean yesNo;
        boolean yesNoDesperfecto = true;
        Client myclient = null;
        Moto m;
        Local lend;
        
        Consola.escriu("Insert the code");
        code = Consola.llegeixString();
        
        code = this.checkCode(code);
        
        if (code != ""){
            for (int i=0; i<=this.lstClient.size() && !check; i++){
                check = this.lstClient.get(i).compareCode(code);
                
                if (check){
                    myclient = this.lstClient.get(i);
                }
            }
            
            m = myclient.getMotoFromActiveReserve();
            lend = myclient.getLocalEFromActiveReserve();
            
            Consola.escriu("Insert a 'S' if the moto is broken, insert a 'N' otherwise");
            answer = Consola.llegeixString();
            
            yesNo = this.checkYesNo(answer);
            if (!yesNo){
                Consola.escriu("Insert a 'S' to admonish a minor flaw, a 'N' otherwise");
                answer = Consola.llegeixString();
                
                yesNoDesperfecto = this.checkYesNo(answer);
               
            } else {
                m.changeStatusNonAvailable();
            }
            
            if (yesNoDesperfecto){
                myclient.admonish();
            }
            
            myclient.printActiveReserve();
            
            Consola.escriu("Insert a 'S' if the moto has been returned with delay or a 'N' otherwise");
            
            answer = Consola.llegeixString();
            
            yesNo = this.checkYesNo(answer);
            
            if (yesNo){
                myclient.delay();
            }
            Consola.escriu("Write 'S' to confirm or 'N' to cancel");
            answer = Consola.llegeixString();
            
            yesNo = this.checkYesNo(answer);
            
            if (yesNo){
                this.pickUpMakeChange(m,lend);
            
            } else {
                myclient.removeAdmonishFromActiveReserve();
                myclient.removeDelayFromActiveReserve();
                m.changeStatusAvailable();
                Consola.escriu("Cancelled");
                
            }
        }
    }
    
    /*------------------------------------------------------------*/
    /* --------------   Menus and Select Options -----------------*/
    /*------------------------------------------------------------*/
    
    private void showUserMenu(){
        Consola.escriu("1. Log in");
        Consola.escriu("2. Sign up");
        Consola.escriu("3. Exit application");
    }
    
    private void selectOptionMenuUser(){
        int index = 0;
        do{
            showUserMenu();
            Consola.escriu("Insert the index of the option you want to do: ");
        
            index = Consola.llegeixInt();
            index = checkNumber(index, 3);
            
            switch(index){
                case 1: 
                    Consola.escriu("Log in");
                    logIn();
                    break;
                case 2:
                    Consola.escriu("Sign up");
                    signUp();
                    break;
                case 3:
                    Consola.escriu("Exit application");
                    break;
                }
           
        }while(index!=3);
    }
    
    private void showClientMenu(){
        Consola.escriu("1. Admin information");
        Consola.escriu("2. Discharge account");
        Consola.escriu("3. Book");
        Consola.escriu("4. Modify Arrival");
        Consola.escriu("5. Cancel Reserve");
        Consola.escriu("6. See code");
        Consola.escriu("7. See reserve");
        Consola.escriu("8. Log Out");
        
    }
    
    private void selectOptionMenuClient(){
        int index = 0;
        do {
            showClientMenu();
            Consola.escriu("Insert the index of the option you want to do.");
            index = Consola.llegeixInt();
            index = checkNumber(index, 8);
            
            switch (index){
                case 1: 
                    Consola.escriu("Admin information");
                    break;
                case 2: 
                    Consola.escriu("Discharge account");
                    break;
                case 3: 
                    Consola.escriu("Book");
                    book();
                    break;
                case 4: 
                    Consola.escriu("Modify Arrival");
                    break;
                case 5: 
                    Consola.escriu("Cancel Reserve");
                    break;
                case 6:
                    Consola.escriu("See code");
                    break;
                case 7:
                    Consola.escriu("See Reserve");
                    break;
                case 8:
                    Consola.escriu("Log Out");
                    break;
            }
        } while (index!=8);
    }
    
    private void showManagerMenu(){
        Consola.escriu("1. Pick Up Moto");
        Consola.escriu("2. Give Moto");
        Consola.escriu("3. Change Moto Status");
        Consola.escriu("4. Log out");  
    }
    
    private void selectOptionMenuManager(){
        int index = 0;
        
        do{
            showManagerMenu();
            Consola.escriu("Insert the index of the option you want to do");
            index = Consola.llegeixInt();
            index = checkNumber(index, 4);
            switch(index){
                case 1: 
                    Consola.escriu("Pick up moto");
                    pickUpMoto();
                    break;
                case 2:
                    Consola.escriu("Give Moto");
                    break;
                case 3:
                    Consola.escriu("Change Moto Status");
                    break;
                case 4:
                    Consola.escriu("Log out");
                    break;
            }
        }while(index!=4);
    }
    
    private void showAdminMenu(){
        Consola.escriu("1. Load data");
        Consola.escriu("2. See motos");
        Consola.escriu("3. Admin distribution");
        Consola.escriu("4. Add moto");
        Consola.escriu("5. Remove moto");
        Consola.escriu("6. Log out");
    }
    
    private void selectOptionMenuAdmin(){
        int index = 0;
        do{
            showAdminMenu();
            Consola.escriu("Insert the index of the option you want to do");
            index = Consola.llegeixInt();
            index = checkNumber(index, 6);
            switch(index){
                case 1: 
                    Consola.escriu("Load data");
                    break;
                case 2:
                    Consola.escriu("See motos");
                    seeMotos();
                    break;
                case 3:
                    Consola.escriu("Admin distribution");
                    break;
                case 4: 
                    Consola.escriu("Add moto");
                    break;
                case 5:
                    Consola.escriu("Remove moto");
                    break;
                case 6:
                    Consola.escriu("Log out");
                    break;
            }
        }while(index!=6);
                
    }
    
   /**
    * 
    */
    
    private void book(){
        boolean r = ((Client)current).hasActiveReserve();
        boolean correct = false;
        Calendar date = null;
        if(!r){
            while(!correct){
                Consola.escriu("Insert a date (hh/dd/mm/yyyy)");
                String fecha = Consola.llegeixString();
                String[] parser = fecha.split("/");
                date = Consola.llegeixDataSistema();
                int year = Integer.parseInt(parser[3]);
                int month = Integer.parseInt(parser[2]);
                int days = Integer.parseInt(parser[1]);
                int hours = Integer.parseInt(parser[0]);
                date.set(year, month, days, hours, 0);
                Calendar currentDate = Consola.llegeixDataSistema();
                correct = currentDate.before(date);
                if(!correct){
                    Consola.escriu("Invalid date, insert again");
                }   
            }
            
            Local localS = bookGetLocalS();
            Local localE = bookGetLocalE();
            Moto motoreta = bookGetMoto(localS);
        
            Consola.escriu("Insert number of days you would like to reserve:");
            int cantDias = Consola.llegeixInt();
            Consola.escriu("Insert number of hours you would like to reserve:");
            int cantHoras = Consola.llegeixInt();
            /**
             Falta implementar :P
            bookShowInfo();
            */
            Consola.escriu("Insert an S to confirm or an N to cancel the reserve");
            String ans = Consola.llegeixString();
            correct = checkYesNo(ans);
            if(correct){
                ((Client)current).addActiveReserve(localS, localE, date, cantHoras, cantDias, motoreta);
                Consola.escriu("The reserve has been done and saved");
            }
        }
    }

    private Local bookGetLocalS() {
        ArrayList<Local> auxLstLocals = getStartAvailableLocals();
        
        printLocalList(auxLstLocals);
        Consola.escriu("Insert the index of your prefered local.");
        int indice = Consola.llegeixInt();
        int lenght = auxLstLocals.size();
        indice = checkNumber(indice,lenght);
        Local localS = getLocalByIndex(auxLstLocals,indice);
        return localS;
    }

    private ArrayList<Local> getStartAvailableLocals() {
        ArrayList<Local> lstLocalAux = new ArrayList<Local>(); 
        for(int i=0;i<lstLocal.size();i++){
            boolean cond = lstLocal.get(i).IsEmpty();
            if(!cond){
                lstLocalAux.add(lstLocal.get(i));
            }
        }
        return lstLocalAux;
    }
    
    private void printLocalList(ArrayList<Local> auxLstLocals) {
        for(int i=0;i<auxLstLocals.size();i++){
            auxLstLocals.get(i).printInfoLocal();
        }
    }

    private Local getLocalByIndex(ArrayList<Local> auxLstLocal, int indice) {
        return auxLstLocal.get(indice-1);
    }

    private Local bookGetLocalE() {
        ArrayList<Local> auxLstLocals = getEndAvailableLocals();
        
        printLocalList(auxLstLocals);
        Consola.escriu("Insert the index of your prefered local.");
        int indice = Consola.llegeixInt();
        int lenght = auxLstLocals.size();
        indice = checkNumber(indice,lenght);
        Local localS = getLocalByIndex(auxLstLocals,indice);
        return localS;
    }

    private ArrayList<Local> getEndAvailableLocals() {
        ArrayList<Local> lstLocalAux = new ArrayList<Local>(); 
        for(int i=0;i<lstLocal.size();i++){
            boolean cond = lstLocal.get(i).IsFull();
            if(!cond){
                lstLocalAux.add(lstLocal.get(i));
            }
        }
        return lstLocalAux;
    }

    private Moto bookGetMoto(Local localS) {
        localS.printMotoList();
        Consola.escriu("Insert the index of your prefered moto.");
        int indice = Consola.llegeixInt();
        Moto motoreta = localS.getMotoByIndex(indice);
        return motoreta;
    }

    /**
     * Method that allows the admin to see all the motos.
     */
    private void seeMotos(){
        //Print the motos located in locals.
        for(Local l: lstLocal){
            //First, let the admin know in which local are the motos.
            l.printInfoLocal();
            
            //Second, lets print all the motos
            l.printMotoList();
        }
        
        //Print the motos currently being driven.
        for(Moto m: lstDriving){
            m.printInfoMoto();
        }
    }
    
    private void seeReport(){
        int m, a;
        boolean check = false;
        m = 0;
        a = 0;
        
        //Ask for a month until the number of the month is correct.
        while (!check){
            Consola.escriu("Insert the number of the month you would like to see");
            m = Consola.llegeixInt();
            if ( (0 < m) && ( m < 13) ){
                check = true;
            } else {
                Consola.escriu("Your number is incorrect. Please, try it again");
            }
        }
        
        check = false;
        //Ask for a year until the number of the year is correct.
        while (!check){
            Consola.escriu("Insert the number of the year you would like to check");
            a = Consola.llegeixInt();
            
            if (a == 2015){
                check = true;
            } else {
                Consola.escriu("Your number is incorrect. Please, try it again");
            }
        }
        for(Client c: lstClient){
            c.printDNI();
            Consola.escriu("Reserves done by this client");
            c.printReservesByMonthYear(m, a);
        }
    }

    public void addManager(Manager m) {
        lstManager.add(m);
    }

    public void addAdmin(Admin a) {
        lstAdmin.add(a);
    }

    public Local getLocalById(String id) {
        for(Local l:lstLocal){
            if(l.compareLocalById(id)){
                return l;
            }
        }
        return null;
    }

    void addReserveToClient(String client, Reserve r) {
        for(Client c:lstClient){
            if(c.compareById(client)){
                c.addReserveDone(r);
            }
        }
    }

    void addActiveReserveToClient(String client, Reserve r) {
        for(Client c:lstClient){
            if(c.compareById(client)){
                c.setActiveReserve(r);
            }
        }
    }


    private void pickUpMakeChange(Moto m, Local lend) {
        lend.addMoto(m);
        this.lstDriving.remove(m);
    }
    
    /*------------------------------------------------------------*/
    /* ------------------  Control functions ---------------------*/
    /*------------------------------------------------------------*/
    
    
    /**
     * Method that, given a code checks if it exists
     * @param code
     * @return a correct code or an empty string.
     */
    private String checkCode(String code) {
       boolean check = false;
       boolean ans;
       
       String rCode = "";
       String chr;
       
       while (!check){
           for (int i = 0; i <= this.lstClient.size() && !check; i++){
               check = this.lstClient.get(i).compareCode(code);
               
               if (check){
                   check = true;
                   rCode = code;
               }
           }
           if (!check){
               Consola.escriu("The given code is incorrect");
               Consola.escriu("Do you want to insert it again? Type a 'S' for yes and a 'N' for no");
               
               chr = Consola.llegeixString();
               ans = this.checkYesNo(chr);
               
               if (ans){
                   Consola.escriu("Insert the code");
                   code = Consola.llegeixString();
               }else{
                   Consola.escriu("Cancelled");
                   check = true;
               }
           }
       }
       return rCode;
    }    

    
    /**
     * Given a number, it checks if it is within a range.
     * @param index the number we want to check
     * @param max maximum value 
     * @return the correct index
     */
    public int checkNumber(int index, int max){
        boolean correct = false;
        while (!correct){
            if (1 <= index && index <= max){
                correct = true;
            } else {
                System.out.println("Invalid index, please try again");
                index = Consola.llegeixInt();
            }
        }
        return index;
    }
    
    /**
     * Method that compares a string to an N or an S.
     * @param chr we want to compare
     * @return true if the answer is S, false if the answer is N.
     */
    private boolean checkYesNo(String chr) {
        boolean correct = false;
        boolean found = true;
        while(!correct){
            if("S".equals(chr)){
                correct = true;
                found = true;
            }else if("N".equals(chr)){
                correct = true;
                found = false;
            }else{
                Consola.escriu("Invalid char, try again.");
                chr = Consola.llegeixString();
            }
        }
        return found;
    }

    public void addLocal(Local l) {
        lstLocal.add(l);
    }

    public void addMotoToLocalFromParser(Moto m) {
        lstLocal.get(lstLocal.size()-1).addMoto(m);
    }
}
  
