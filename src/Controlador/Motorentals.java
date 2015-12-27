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
    
    
    
    /*-------------------------------------------------------------*/
    /* ----------- Constructor, Singleton and Run -----------------*/
    /*-------------------------------------------------------------*/
    
    
    private Motorentals(){
        lstAdmin = new ArrayList<Admin>();
        lstManager = new ArrayList<Manager>();
        lstClient = new ArrayList<Client>();
        lstDriving =  new ArrayList<Moto>();
        lstLocal = new ArrayList<Local>();
        current = null;
    }
    
    /**
     * Gets an instance from MotoRentals. Ensuring MotoRentals is a singleton. 
     * @return 
     */
    public static Motorentals getInstance(){
        if(instance == null){
            instance = new Motorentals();
            MotoRentDataManager dm = new MotoRentDataManager();
            dm.obtenirDades("data/MotoRent.xml");
        }
        return instance;
    }
    

    /**
     * Calls the first menu, first thing that we show. 
     */
    public void run(){
        this.selectOptionMenuUser();
    }

    /*-------------------------------------------------------------*/
    /* ------------ Needed functions to load data -----------------*/
    /*-------------------------------------------------------------*/
    
    /**
     * Adds a client from the DataManager to our client list.
     * @param c client to be added
     */
    public void addClient(Client c) {
       lstClient.add(c);
    }
    
    /**
     * Adds a local from the DataManager to our local list.
     * @param l local to be added.
     */
    public void addLocal(Local l) {
        lstLocal.add(l);
    }
    
    /**
     * Adds a moto to a local from the DataManager.
     * @param m moto to be added.
     */
    public void addMotoToLocalFromParser(Moto m) {
        //We checked how the DataManager and the Parser work.
        //First of all, we load the local.
        //And then, we load the motos of this concrete local.
        //So the process is load local -> full this local with its motos.
        //Parsing an adding that is simple, because the arrayList of locals increases its size
        //progressively. We can just add the motos to the local in the position size-1.
        //This is even true for the first local because first we put the local and then the motos.
        lstLocal.get(lstLocal.size()-1).addMoto(m);
    }
    
    /**
     * Searches a moto by its id in all locals.
     * This method has reserve purposes. We can't assure where the moto will be
     * (because maybe there are a lot of reserves using this moto). So we don't
     * know if a moto is in the starter local, the ending local, or whatever local.
     * We have to search the moto in all locals to be sure we find it.
     * @param moto
     * @return Moto
     */
    public Moto searchMotoByIdInAllLocals(String moto) {
        Moto m;
        for(Local l:lstLocal){
            m=l.getMotoById(moto);
            if(m!=null){
                return m;
            }
        }
        System.out.println("ESTO NO DEBERIA OCURRIR");
        return null;
    }
    
    /**
     * Adds a manager from the DataManager to our list.
     * @param m Manager to be added.
     */
    public void addManager(Manager m) {
        lstManager.add(m);
    }

    /**
     * Adds an Admin from the DataManater to our list.
     * @param a Admin to be added.
     */
    public void addAdmin(Admin a) {
        lstAdmin.add(a);
    }

    /**
     * Obtains a local searching by id.
     * @param id
     * @return Local
     */
    public Local getLocalById(String id) {
        for(Local l:lstLocal){
            if(l.compareLocalById(id)){
                return l;
            }
        }
        //This shouldn't happen because it is used in the DataManager.
        //We already know that the id is correct and that we will find a local.
        return null;
    }

    /**
     * Adds a done reserve to the list of the client. 
     * @param client id that we use to find the client.
     * @param r reserve that we will add
     * @param falta The reserve can have admonishes
     */
    public void addReserveToClient(String client, Reserve r,int falta) {
        for(Client c:lstClient){
            if(c.compareById(client)){
                c.addReserveDone(r);
                c.updateAdmonish(falta);
            }
        }
    }

    /**
     * Adds an active reserve to client.
     * Maybe is not used now (because all the reserves given in the XML are done)
     * but we contemplate the possibility of parsing active reserves.
     * @param client
     * @param r 
     */
    public void addActiveReserveToClient(String client, Reserve r) {
        for(Client c:lstClient){
            if(c.compareById(client)){
                c.setActiveReserve(r);
            }
        }
    }

    
    /*-------------------------------------------------------------*/
    /* -------------------- LogIn & SignUp ------------------------*/
    /*-------------------------------------------------------------*/
    
    /**
     * A supposed existent user tries to login to our system.
     */
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
    /**
     * An user can give his/her information and sign up to our application.
     */
    private void signUp (){
        String name,surname,street,DNI, pobl, email, iban;
        String DNINumber;
        String user,pass1,pass2;
        DNI = null;
        user = null;
        pass1 = null;
        
        int dniNumber = 0, tlphNumber, numStreet, numDoor, CP;
        int entity, office, control, accNumber;
        
        
        boolean check = false,exists = false,comprobacion = false;
        
        Consola.escriu("Real name: ");
        name = Consola.llegeixString();
        
        Consola.escriu("Surname: ");
        surname = Consola.llegeixString();
        
        while (!check) {
            while (!comprobacion){
                comprobacion = true;
                Consola.escriu("DNI(with letter): ");
                DNI = Consola.llegeixString();
                if (DNI.length()==9){
                    DNINumber = DNI.substring(0, DNI.length()-1);
                    dniNumber = Integer.parseInt(DNINumber);
                } else {
                    Consola.escriu("Invalid DNI. Please type it again");
                    comprobacion = false;
                }
            }
           
            if (dniNumber - 100000001  < 0 && dniNumber >= 0 ){
                check = true;
            } else {
                Consola.escriu("Invalid number");
            }
            
        }
        
        check = false;
        while (!check){
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
        
        while (!check){
            Consola.escriu("Write the iban of your bank account (First 4 digits)");
            iban = Consola.llegeixString();
            if (iban.length() != 4){
                Consola.escriu("Invalid iban");
            } else {
                check = true;
            }
        }
        check = false;
        while (!check){
            Consola.escriu("Write the entity of your bank account (Next 4 digits): ");
            entity = Consola.llegeixInt();
            if (entity >= 0 && entity <= 9999){
                check = true;
            } else{
                Consola.escriu("Invalid entity");
            }   
            
        }
        check = false;
        while (!check){
            Consola.escriu("Write the office of your bank account (Next 4 digits): ");
            office = Consola.llegeixInt();
            if (office >= 0 && office <= 9999){
                check = true;
            } else{
                Consola.escriu("Invalid office");
            }   
            
        }
        check = false;
        while (!check){
            Consola.escriu("Write the DC of your bank account (Next 2 digits): ");
            control = Consola.llegeixInt();
            if (control >= 0 && control <= 99){
                check = true;
            } else{
                Consola.escriu("Invalid DC");
            }   
            
        }
        check = false;
        while (!check){
            Consola.escriu("Write the account number of your bank account (Last 10 digits): ");
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
            if (check) {
                Consola.escriu("You have already have an acoount");
            }
        }
        
        if (!check){
            while (!check){
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
            while (!check) {
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
    
    
    /*-------------------------------------------------------------*/
    /* ---------- Chore system methods implemented ----------------*/
    /*-------------------------------------------------------------*/
    
    
    /**
     * Manager method. It allows the admin to recieve a moto from a client.
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
        
        if (!code.equals("")){
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
    
    /**
     * Remove the moto from the driving list and adds it to the arrival local.
     * @param m moto that is returned.
     * @param lend local to which the moto is added. 
     */
    private void pickUpMakeChange(Moto m, Local lend) {
        lend.addMoto(m);
        this.lstDriving.remove(m);
    }
    
    
    /**
     * The manager gives a moto to a client. The system updates its info.
     */
    public void giveMoto(){
        String code;
        boolean condition = false;
        Local localS;
        Moto moto;
        
        Consola.escriu("Insert the reserve code");
        code = Consola.llegeixString();
        
        code = this.checkCode(code);
        
        if (!code.equals("")){
            for (int i = 0; i <= lstClient.size() && !condition; i++){
                condition = lstClient.get(i).compareCode(code);
                
                if (condition){
                    lstClient.get(i).printActiveReserve();
                    localS = lstClient.get(i).getLocalSFromActiveReserve();
                    
                    moto = lstClient.get(i).getMotoFromActiveReserve();
                    
                    this.makeGiveChanges(localS,moto);
                }
            }
        }
    }
    /**
     * Removes a moto from a local and adds it to the driving list.
     * @param l local from which we remove the moto
     * @param m moto that is now being driven. 
     */
    private void makeGiveChanges(Local l, Moto m) {
        l.removeMoto(m);
        lstDriving.add(m);
    }
    
    
    /**
     * The admin is allowed to switch motos between some locals. 
     */
    private void adminMoto(){
        Moto m;
        Local lStart, lEnd;
        Boolean bool = true;
        ArrayList lstLocal;
        int size, num;
        String answer;
        
        /**
         * TO DO: Si las listas están vacías, hay que salir de la opción porque no se pueden mover motos.
         */
        
        //We will do this until the admin doesn't want to move more motos.
        while (bool){
            //From which locals can we move motos? Take them and put them in a list.
            lstLocal = getStartAvailableLocals();
            //Same error control here. If the locals are empty, we can't move anything!
            if (!lstLocal.isEmpty()){
                Consola.escriu("\n You can move motos from these locals: ");
                printLocalList(lstLocal);
                Consola.escriu("\n Insert the number of the index of your prefered local");
                num = Consola.llegeixInt();
                size = lstLocal.size();
                //Let's check if the index inserted is correct:
                num = checkNumber(num, size);
                lStart = getLocalByIndex(lstLocal, num);
                
                //Obtain the moto the admin wants to move:
                Consola.escriu("\n --->These are the motos you can move: ");
                lStart.printMotoList();
                Consola.escriu("\n Insert the number of the index of the moto you want to move: ");
                num = Consola.llegeixInt();
                size = lStart.getQuantityMotos();
                //Check that the index is correct
                num = checkNumber(num, size);
                m = lStart.getMotoByIndex(num);

                //To which locals can we move motos? Take them and put them in list.
                lstLocal= getEndAvailableLocals();
                if (!lstLocal.isEmpty()){
                    Consola.escriu("\n You can move motos to these locals: ");
                    printLocalList(lstLocal);
                    Consola.escriu("\n Insert the number of the index of your prefered local");
                    num = Consola.llegeixInt();
                    size = lstLocal.size();
                    //Let's check if the index inserted is correct:
                    num = checkNumber(num, size);
                    lEnd = getLocalByIndex(lstLocal, num);

                    /**
                     * LOTS of prints!!
                     */
                    Consola.escriu("*********************");
                    Consola.escriu("These are the changes you are about to do: ");
                    Consola.escriu("*********************");

                    Consola.escriu("\n You will move a moto from the local: ");
                    lStart.printInfoLocal();

                    Consola.escriu("*********************");

                    Consola.escriu("\n You will move the moto: ");
                    m.printInfoMoto();

                    Consola.escriu("*********************");

                    Consola.escriu("\n You will move the moto to the local: ");
                    lEnd.printInfoLocal();

                    Consola.escriu("*********************");

                    Consola.escriu("Insert an S to confirm the changes or an N to discard them ");
                    answer = Consola.llegeixString();
                    //Let's check if the admin wants to confirm the changes.
                    //If we have a true, we keep doing it.
                    //If we have a false, the admin doesn't want to make changes.
                    bool = checkYesNo(answer);
                    if (bool){
                        changeMoto(lStart, lEnd, m);

                        Consola.escriu("\n *** The change has been done correctly! ***");
                        Consola.escriu("Insert an S to keep moving motos, an N otherwise");
                        answer = Consola.llegeixString();
                        //Same scenario here. If we have a true, the admin wants to make more changes.
                        bool = checkYesNo(answer);
                    }
                }else {
                    Consola.escriu("Sorry, but you can't move motos right now. Your locals are almost full.");
                }
            } else {
                Consola.escriu("Sorry, but you can't move motos right now. Your locals are almost empty.");
            }
        }    
    }
    
    /**
     * Simple function, removes a moto from a Local and adds it to another local.
     * @param lStart local from which we remove a moto.
     * @param lEnd local to which we add a moto.
     * @param m moto we are moving.
     */
    private void changeMoto(Local lStart, Local lEnd, Moto m){
        lStart.removeMoto(m);
        lEnd.addMoto(m);
    }
    
    /**
     * The client can book a moto if he/she hasn't an active reserve.
     */
    private void book(){
        boolean r = ((Client)current).hasActiveReserve();
        boolean correct = false;
        boolean check = false;
        int hours = 0;
        int days = 0;
        int month = 0;
        int year = 0;
        Calendar date = null;
        if(!r){
            while(!correct){
                Consola.escriu("Insert a date (hh/dd/mm/yyyy)");
                String fecha = Consola.llegeixString();
                String[] parser = fecha.split("/");
                date = Consola.llegeixDataSistema();
                try{
                    hours = Integer.parseInt(parser[0]);
                    days = Integer.parseInt(parser[1]);
                    month = Integer.parseInt(parser[2])-1;
                    year = Integer.parseInt(parser[3]);

                while (!check){
                    check = true;
                    if (hours > 20 || hours <= 8){
                        check = false;
                        Consola.escriu("The inserted hour is incorrect (8-20 hours only accepted). Please insert it again");
                        hours = Consola.llegeixInt();
                    }
                }
                check = false;
                while(!check){
                    check = true;
                    if (days > 31 || days <= 0){
                        check = false;
                        Consola.escriu("The inserted day is incorrect. Please insert it again");                    
                        days = Consola.llegeixInt();
                    }
                }
                check = false;
                while(!check){
                    check = true;
                    if (month > 12 || month <= 0){
                        check = false;
                        Consola.escriu("The inserted month is incorrect. Please insert it again");
                        month = Consola.llegeixInt();
                    }
                }
                check = false;
                while(!check){
                    check = true;
                    if (year >= 2017 || year <= 0){
                        check = false;
                        Consola.escriu("The inserted year is incorrect. Please insert it again");
                        year = Consola.llegeixInt();
                    }
                }    
                
                date.set(year, month, days, hours, 0);
                Calendar currentDate = Consola.llegeixDataSistema();
                correct = currentDate.before(date);

                if(!correct){
                    Consola.escriu("Invalid date, insert again");
                }   
                }catch(Exception ex){
                    Consola.escriu("There's a invalid character in the date or an enmpy value, please, write again the date:");
                }
            }
    
            //Trying to get our starter local.
            Local localS = bookGetLocalS();
            //If it is different from null, then we can keep going.
            if (localS != null){
                //We can try to get our ending local.
                Local localE = bookGetLocalE();
                //And if indeed we can reach up to an ending local, we can keep going.
                if (localE != null){
                //We DON'T have to check the same for the motos, because it is considered that a local is available
                //If it has a X amount of motos. So if we reach here, then our starting local is HAS motos for sure.
                Moto motoreta = bookGetMoto(localS);

                Consola.escriu("Insert number of days you would like to reserve:");
                int cantDias = Consola.llegeixInt();
                Consola.escriu("Insert number of hours you would like to reserve:");
                int cantHoras = Consola.llegeixInt();
                this.bookPrintInfo(localS,localE,motoreta,date,cantDias,cantHoras);
                Consola.escriu("Insert an S to confirm or an N to cancel the reserve");
                String ans = Consola.llegeixString();
                correct = checkYesNo(ans);
                if(correct){
                    ((Client)current).addActiveReserve(localS, localE, date, cantHoras, cantDias, motoreta);
                    String code = new String();
                    code = ((Client)current).getId()+localS.getId()+localE.getId()+motoreta.getId()+Integer.toString(cantHoras)+Integer.toString(cantDias);
                    code = code + date.get(Calendar.HOUR_OF_DAY) + date.get(Calendar.DAY_OF_MONTH) + Integer.toString(date.get(Calendar.MONTH)+1) + date.get(Calendar.YEAR);
                    ((Client)current).setCodeToActiveReserve(code);
                    Consola.escriu("Code: "+code);
                    Consola.escriu("The reserve has been done and saved");
                        }  
                }
            }
        }
    }

    /**
     * Gets a correct starting local or informs that there aren't such locals.
     * @return Local if it is possible, null if it isn't.
     */
    private Local bookGetLocalS() {
        ArrayList<Local> auxLstLocals = getStartAvailableLocals();
        
        //So let's take over the possible errors.
        //What if the application is overload and doesn't have motos?
        //The user would be stuck and won't be allowed to leave the option.
        //Simple solution: let's check that the available locals isn't an empty list.
        //If it isn't an empty list... then we can proceed normally.
        //But if it is an empty list, the user can't do anything and we will return to the menu.
        if (!auxLstLocals.isEmpty()){
            printLocalList(auxLstLocals);
            Consola.escriu("Insert the index of your prefered local (exit) .");
            int indice = Consola.llegeixInt();
            int lenght = auxLstLocals.size();
            indice = checkNumber(indice,lenght);
            Local localS = getLocalByIndex(auxLstLocals,indice);
            return localS;
        } else {
            Consola.escriu("Sorry, the application is overload and doesn't have enough motos.");
            Consola.escriu("Try it again later, thank you for your patience.");
            return null;
        }
    }

    /**
     * Makes an arrayList of available starter locals.
     * @return arrayList of starter locals.
     */
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
    
    /**
     * Given a list of locals, print its elements. 
     * @param auxLstLocals list to print.
     */
    private void printLocalList(ArrayList<Local> auxLstLocals) {
        for(int i=0;i<auxLstLocals.size();i++){
            Consola.escriu("Local " + Integer.toString(i+1));
            Consola.escriu("-----------------------");
            auxLstLocals.get(i).printInfoLocal();
            Consola.escriu("-----------------------");
        }
    }

    /**
     * Gets a local of a list based on an index. 
     * @param auxLstLocal
     * @param indice
     * @return 
     */
    private Local getLocalByIndex(ArrayList<Local> auxLstLocal, int indice) {
        return auxLstLocal.get(indice-1);
    }

    /**
     * Gets a correct ending local. 
     * @return Local if it's possible, null if it isn't.
     */
    private Local bookGetLocalE() {
        ArrayList<Local> auxLstLocals = getEndAvailableLocals();
        
        //The exact same process here, if the list is empty we don't have to keep with the process.
        if (!auxLstLocals.isEmpty()){
        printLocalList(auxLstLocals);
        Consola.escriu("Insert the index of your prefered local (arrival).");
        int indice = Consola.llegeixInt();
        int lenght = auxLstLocals.size();
        indice = checkNumber(indice,lenght);
        Local localS = getLocalByIndex(auxLstLocals,indice);
        return localS;
        } else {
            Consola.escriu("Sorry, the application is overload and doesn't have enough motos.");
            Consola.escriu("Try it again later, thank you for your patience.");
            return null;
        }
    }

    /**
     * Makes an arraylist of ending locals. 
     * @return list of ending locals. 
     */
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

    /**
     * Gets a Moto of a starting local. 
     * @param localS local to extract the moto.
     * @return moto to be rented.
     */
    private Moto bookGetMoto(Local localS) {
        ArrayList<Moto> aux = new ArrayList<Moto>();
        aux = localS.getAvailableMotos();
        printMotoList(aux);
        Consola.escriu("Insert the index of your prefered moto.");
        int indice = Consola.llegeixInt();
        indice = checkNumber(indice, aux.size());
        return aux.get(indice-1);
    }

    /**
     * Method that allows the admin to see all the motos.
     */
    private void seeMotos(){
        int i=0;
        //Print the motos located in locals.
        for(Local l: lstLocal){
            //First, let the admin know in which local are the motos.
            i++;
            Consola.escriu("------------");
            Consola.escriu("Local " + i);
            l.printInfoLocal();
            Consola.escriu("------------");
            //Second, lets print all the motos
            l.printMotoList();
        }
        
        //Print the motos currently being driven.
        for(Moto m: lstDriving){
            m.printInfoMoto();
        }
    }
    
    /**
     * The admin can see a report of a given month and year. 
     */
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
            
            //This can be changed. If the application is running for years, then
            //sure some years past 2017 would be correct years too.
            if (0 < a && 2017>=a){
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




    
    /*------------------------------------------------------------*/
    /* --------------   Menus and Select Options -----------------*/
    /*------------------------------------------------------------*/
    
    /**
     * Prints for the user menu.
     */
    private void showUserMenu(){
        Consola.escriu("1. Log in");
        Consola.escriu("2. Sign up");
        Consola.escriu("3. Exit application");
    }
    
    /**
     * Shows the user menu and gets an option.
     */
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
    
    /**
     * Prints for the client menu
     */
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
    
    /**
     * Shows the client menu and gets an option.
     */
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
                    Consola.escriu("***** YNever change, stay awesome! ***** ");
                    break;
                case 2: 
                    Consola.escriu("Discharge account");
                    Consola.escriu("***** This option is not available, the app is awesome, don't discharge your account! ***** ");
                    break;
                case 3: 
                    Consola.escriu("Book");
                    book();
                    break;
                case 4: 
                    Consola.escriu("Modify Arrival");
                    Consola.escriu("***** Under construction, sorry for the inconvenience ***** ");
                    break;
                case 5: 
                    Consola.escriu("Cancel Reserve");
                    Consola.escriu("***** Under construction, sorry for the inconvenience ***** ");
                    break;
                case 6:
                    Consola.escriu("See code");
                    Consola.escriu("***** Your code is corrupted... joking, just under construction! ***** ");
                    break;
                case 7:
                    Consola.escriu("See Reserve");
                    Consola.escriu("***** Under construction, sorry for the inconvenience ***** ");
                    break;
                case 8:
                    Consola.escriu("Log Out");
                    break;
            }
        } while (index!=8);
    }
    
    /**
     * Prints for the manager menu.
     */
    private void showManagerMenu(){
        Consola.escriu("1. Pick Up Moto");
        Consola.escriu("2. Give Moto");
        Consola.escriu("3. Change Moto Status");
        Consola.escriu("4. Log out");  
    }
    /**
     * Shows the manager menu and gets an option.
     */
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
                    giveMoto();
                    break;
                case 3:
                    Consola.escriu("Change Moto Status");
                    Consola.escriu("***** Under construction, sorry for the inconvenience ***** ");
                    break;
                case 4:
                    Consola.escriu("Log out");
                    break;
            }
        }while(index!=4);
    }
    /**
     * Prints for the admin menu
     */
    private void showAdminMenu(){
        Consola.escriu("1. Load data");
        Consola.escriu("2. See motos");
        Consola.escriu("3. Admin distribution");
        Consola.escriu("4. Add moto");
        Consola.escriu("5. Remove moto");
        Consola.escriu("6. See report");
        Consola.escriu("7. Log out");
    }
    /**
     * Shows the admin menu and gets an option.
     */
    private void selectOptionMenuAdmin(){
        int index = 0;
        do{
            showAdminMenu();
            Consola.escriu("Insert the index of the option you want to do");
            index = Consola.llegeixInt();
            index = checkNumber(index, 7);
            switch(index){
                case 1: 
                    Consola.escriu("Load data");
                    Consola.escriu("***** Under construction, sorry for the inconvenience ***** ");
                    break;
                case 2:
                    Consola.escriu("See motos");
                    seeMotos();
                    break;
                case 3:
                    Consola.escriu("Admin distribution");
                    adminMoto();
                    break;
                case 4: 
                    Consola.escriu("Add moto");
                    Consola.escriu("***** Under construction, sorry for the inconvenience ***** ");
                    break;
                case 5:
                    Consola.escriu("Remove moto");
                    Consola.escriu("***** Under construction, sorry for the inconvenience ***** ");
                    break;
                case 6:
                    Consola.escriu("See report");
                    seeReport();
                    break;
                case 7:
                    Consola.escriu("Log out");
                    break;
            }
        }while(index!=7);
                
    }
    
    /**
     * Prints a certain information about the book. 
     * @param localS
     * @param localE
     * @param motoreta
     * @param date
     * @param cantDias
     * @param cantHoras 
     */
    private void bookPrintInfo(Local localS, Local localE, Moto motoreta, Calendar date, int cantDias, int cantHoras) {
       String strDays;
       String strHours;
       
       localS.printInfoLocal();
       localE.printInfoLocal();
       motoreta.printInfoMoto();
       
       Consola.escriu("Time to reserve");
       strDays = Integer.toString(cantDias);
       strHours = Integer.toString(cantHoras);
       Consola.escriu("Days:"+ strDays + "Hours:" + strHours);
    }

    /**
     * Prints the motos of a list. 
     * @param aux  list to be printed out. 
     */
    private void printMotoList(ArrayList<Moto> aux) {
        int i = 0;
        for(Moto m:aux){
            Consola.escriu("Moto "+ Integer.toString(i+1));
            Consola.escriu("****************");
            m.printInfoMoto();
            Consola.escriu("****************");
            i++;
        }
    }
}
  
