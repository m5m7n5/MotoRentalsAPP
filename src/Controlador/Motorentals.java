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
import Vista.Consola;
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
        this.showUserMenu();
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
            showClientMenu();
            current = pers; //This will be our current user
        } else if (pers instanceof Manager && tries < 3){
            showManagerMenu();
            current = pers; 
        } else if (pers instanceof Admin && tries < 3){
            showAdminMenu(); 
            current = pers;
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
            dniNumber = Integer.parseInt(DNI);
            
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
            
            if (tlphNumber - 1000000001 > 0 && tlphNumber <= 999999999){
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
        Consola.escriu("Correctly registered");
        Consola.escriu(user);
    }
    
    /*------------------------------------------------------------*/
    /* --------------   Menus and Select Options -----------------*/
    /*------------------------------------------------------------*/
    
    private void showUserMenu(){
        Consola.escriu("1. Log in");
        Consola.escriu("2.Sign up");
        Consola.escriu("3. Exit application");
        
        Consola.escriu("Insert the index of the option you want to do");
        
        int index;
        index = Consola.llegeixInt();
        index = checkNumber(index, 3);
        selectOptionMenuUser(index);
    }
    
    private void selectOptionMenuUser(int index){
        switch(index){
            case 1: 
                Consola.escriu("Log in");
                break;
            case 2:
                Consola.escriu("Sign up");
                break;
            case 3:
                Consola.escriu("Exit application");
                break;
        }
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
        
        Consola.escriu("Insert the index of the option you want to do.");
        
        int index;
        index = Consola.llegeixInt();
        index = checkNumber(index, 8);
        selectOptionMenuClient(index);
    }
    
    private void selectOptionMenuClient(int index){
        switch (index){
            case 1: 
                Consola.escriu("Admin information");
                break;
            case 2: 
                Consola.escriu("Discharge account");
                break;
            case 3: 
                Consola.escriu("Book");
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
    }
    
    private void showManagerMenu(){
        Consola.escriu("1. Pick Up Moto");
        Consola.escriu("2. Give Moto");
        Consola.escriu("3. Change Moto Status");
        Consola.escriu("4. Log out");
        
        Consola.escriu("Insert the index of the option you want to do");
        
        int index;
        index = Consola.llegeixInt();
        index = checkNumber(index, 4);
        
        selectOptionMenuManager(index);
        
    }
    
    private void selectOptionMenuManager(int index){
        switch(index){
            case 1: 
                Consola.escriu("Pick up moto");
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
    }
    
    private void showAdminMenu(){
        Consola.escriu("1. Load data");
        Consola.escriu("2. See motos");
        Consola.escriu("3. Admin distribution");
        Consola.escriu("4. Add moto");
        Consola.escriu("5. Remove moto");
        Consola.escriu("6. Log out");
        
        Consola.escriu("Insert the index of the option you want to do");
        
        int index;
        index = Consola.llegeixInt();
        index = checkNumber(index, 6);
        
        selectOptionAdminMenu(index);
    }
    
    private void selectOptionAdminMenu(int index){
        switch(index){
            case 1: 
                Consola.escriu("Load data");
                break;
            case 2:
                Consola.escriu("See motos");
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
                
    }
    

    
    public int checkNumber(int index, int max){
        boolean correct = false;
        while (!correct){
            if (1 <= index && index <= max){
                correct = true;
            } else {
                System.out.println("Invalid index, please try again");
                
            }
        }
        return index;
    }
}
