/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;


import java.util.Scanner;

/**
 *
 * @author Marcos
 */
public class MainMenu {

    static private enum OpcionesMenuPrincipal {Sign_up,Log_in,Exit};
    static private String[] descMenuPrincipal = {"Sign up", "Log in", "Exit application"};
    
    static private enum OptionsClientMenu {AdminInfo, DischargeAccount, Book, Modify, CancelReserve, SeeCode,  SeeReserve, Exit}
    static private String[] descClientMenu = {"Admin your info", "DischargeAccount", "Book a moto", "Modify arrival local", "Cancel your reserve",
                                             "See reserve code", "See your reserve", "Log out"};
    
    static private enum OptionsManagerMenu {Enlist, Discharge, Update, Exit};
    static private String[] descManagerMenu = {"Enlist a moto", "Discharge a moto", "Update moto status", "Log out"};
    
    static private enum OptionsAdminMenu {SeeMotos, AdminDistribution, AddMoto, DischargeMoto, SeeReport, Exit};
    static private String[] descAdminMenu = {"See your database motos", "Admin the moto distribution", "Add a moto to the database",
                                             "Discharge a moto from the database", "See monthly reports", "Log out"};
    
    private String client;
    private String manager;
    private String admin;
    
    public static void main(String[] args){
        MainMenu menu = new MainMenu();
        menu.initNames();
        menu.runMainMenu();
    }
    
    
    public void runMainMenu() {
        Scanner sc = new Scanner(System.in);
        GeneralMenu<OpcionesMenuPrincipal> menu = new GeneralMenu<OpcionesMenuPrincipal>("Menu principal",OpcionesMenuPrincipal.values(),descMenuPrincipal);
        OpcionesMenuPrincipal op = null;
        do{
            try{
            menu.runMenu();
            op = menu.readOption(sc);
            switch(op){
                
                case Sign_up:
                    System.out.println("You have selected the sign up option.");
                    askSignUp(sc);
                    break;
                    
                case Log_in:
                    System.out.println("You have selected the log in option.");
                    int status = askLogIn(sc);
                    if (status == 1){
                        runClientMenu(sc);
                    } else if (status == 2){
                        runManagerMenu(sc);
                    } else if (status == 3){
                        runAdminMenu(sc);
                    } else {
                        System.out.println("This user doesn't exist.");
                    }
                    
                    
                    break;
                    
                case Exit:
                    System.out.println("You are now exiting the application.");
                    break;
            }
            }catch(Exception ex){
                System.out.println("Your input is invalid. Try again with a number from 1 to 3.");
                sc.next();
            }
        
        }while(op!=OpcionesMenuPrincipal.Exit);
    }
    
    
    private void askSignUp(Scanner sc){
        System.out.println("");
        
    }
    
    private int askLogIn(Scanner sc){

        System.out.println("Input your username: ");
        String username = sc.next();
        System.out.println("Input your password: ");
        String password = sc.next();
        
        /**
         * Needed: harass control: does the user exist?
         */

        System.out.println("You are now logged in as " + username +" .");
        
        if(client.equals(username)){
            return 1;
        } else if (manager.equals(username)){
            return 2;
        } else if (admin.equals(username)){
            return 3;
        }
        return 0;
        
    }
    
    private void runClientMenu(Scanner sc){
        GeneralMenu<OptionsClientMenu> menu = new GeneralMenu<OptionsClientMenu>("Possible actions.",OptionsClientMenu.values(),descClientMenu);
        OptionsClientMenu op = null;
        do{
            try{
            menu.runMenu();
            op = menu.readOption(sc);
            switch(op){
                
                case AdminInfo:
                    System.out.println("You can now change your information.");
                    break;
                    
                case Book:
                    System.out.println("You can now book a moto.");
                    break;
                
                case SeeReserve:
                    System.out.println("Your reserve information will now be displayed.");
                    break;
                    
                case Modify:
                    System.out.println("You may now modify your arrival local.");
                    break;
                    
                case CancelReserve:
                    System.out.println("You may now cancel your reserve.");
                    break;
                    
                case SeeCode:
                    System.out.println("Your reserve code will be desplayed.");
                    break;
                  
                case DischargeAccount:
                    System.out.println("You may now discharge your account.");
                    break;
                    
                case Exit:
                    System.out.println("You are now logging out.");
                    break;
            }
            }catch(Exception ex){
                System.out.println("Your input is invalid. Try again with a number from 1 to 3.");
                sc.next();
            }
        
        }while(op!=OptionsClientMenu.Exit);
    }
        
    private void runManagerMenu(Scanner sc){
        GeneralMenu<OptionsManagerMenu> menu = new GeneralMenu<OptionsManagerMenu>("Possible actions.",OptionsManagerMenu.values(),descManagerMenu);
        OptionsManagerMenu op = null;
        do{
            try{
            menu.runMenu();
            op = menu.readOption(sc);
            switch(op){
                
                case Enlist:
                    System.out.println("You may now fulfill the moto information.");
                    break;
                    
                case Discharge:
                    System.out.println("You may now give away the moto.");
                    break;
                
                case Update:
                    System.out.println("You may now select a moto.");
                    break;
      
                case Exit:
                    System.out.println("You are now logging out.");
                    break;
            }
            }catch(Exception ex){
                System.out.println("Your input is invalid. Try again with a number from 1 to 3.");
                sc.next();
            }
        
        }while(op!=OptionsManagerMenu.Exit);
        
    }
    
    private void runAdminMenu(Scanner sc){
        GeneralMenu<OptionsAdminMenu> menu = new GeneralMenu<OptionsAdminMenu>("Possible actions.",OptionsAdminMenu.values(),descAdminMenu);
        OptionsAdminMenu op = null;
        do{
            try{
            menu.runMenu();
            op = menu.readOption(sc);
            switch(op){
                
                case SeeMotos:
                    System.out.println("The information of your moto database will now be displayed.");
                    break;
                    
                case AdminDistribution:
                    System.out.println("You may now change your moto distribution.");
                    break;
                
                case AddMoto:
                    System.out.println("You can now fulfill all the new moto information.");
                    break;
                    
                case DischargeMoto:
                    System.out.println("You may now select a moto to delete it from the database.");
                    break;
                    
                case SeeReport:
                    System.out.println("You can choose a monthly report.");
                    break;
                    
                case Exit:
                    System.out.println("You are now logging out.");
                    break;
            }
            }catch(Exception ex){
                System.out.println("Your input is invalid. Try again with a number from 1 to 3.");
                sc.next();
            }
        
        }while(op!=OptionsAdminMenu.Exit);
    }
    
    private void initNames(){
        client = "client1";
        manager = "manager1";
        admin = "admin1";
    }
        
    }
    

