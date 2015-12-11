package Controlador;

import Model.Client;
import Model.VipClient;
import Vista.Consola;

/**
 * Data manager per MotoRent
 * . Crea les estructures de dades necessàries
 * per a manegar l'aplicació de gestió d'MotoRent
 * .
 * @author Professors disseny de software
 */
public class MotoRentDataManager {

	/* -------------------------------------------------------------------
	 * Metodes a implementar per vosaltres. En aquests metodes creareu els
	 * vostres objectes a partir de la informacio obtinguda del fitxer XML
	 * 
	 * Podeu esborrar els prints si voleu. Tambe podeu canviar el tipus de
	 * dades que retorna el metode, es a dir que sou lliures de
	 * modificar-ho al gust, excepte les crides inicials que es fan.
	 * -------------------------------------------------------------------
	 */
	

	/**
	 * Obté les dades del fitxer XML passat per paràmetre
	 * 
	 * @param nomFitxer ruta del fitxer XML del que obtenir les dades
	 */
	public void obtenirDades(String nomFitxer) {
		MotoRentXMLParser parser = new MotoRentXMLParser(this);
		parser.parse(nomFitxer);
	}

	/**
	 * Crea a partir de la informacio obtinguda del fitxer XML
	 * 
	 * @param id id de l'local. El podeu utilitzar o no
	 * @param capacitat quantita màxima de motos
	 * @param gestorID gestorID del local
	 * @param adreca adreça del local
	 */
	
	public void crearLocal(String id, String capacitat, String gestorID, String adreca) {

		/*  TODO: A partir d'aqui creeu el vostre objecte que contingui la informacio
		 *
		 */

		Consola.escriu("\nlocal amb ID: " + id + "\n");
		Consola.escriu("--------------------------------------------------\n");
		Consola.escriu("Capacitat: " + capacitat + "\n");
		Consola.escriu("Gestor ID: " + gestorID + "\n");
		Consola.escriu("Adreça: " + adreca + "\n");
	}

	/**
	 * Crea una nova moto a partir de la informacio obtinguda del fitxer XML
	 * 
	 * @param id id de la moto. El podeu utilitzar o no
         * @param matricula matrícula de la moto
         * @param marca marca de la moto
         * @param model model de la moto
	 * @param color color de la moto
	 * @param estat estat de la moto: "true" representa moto activa i "false" per reparar
	 */

	public void crearMoto(String id, String matricula, String marca, String model, String color, String estat) {

		/* TODO: Aqui feu el necessari per a crear les vostres motos
		 */

		Consola.escriu("\nmoto amb ID: " + id + "\n");
		Consola.escriu("--------------------------------------\n");
		Consola.escriu("Matrícula: " + matricula + "\n");
                Consola.escriu("Marca: " + marca + "\n");
                Consola.escriu("Model: " + model + "\n");
                Consola.escriu("Color: " + color + "\n");
		Consola.escriu("Estat: " + estat + "\n");
	}

	/**
	 * Crea un trajecte a partir de la informacio obtinguda del fitxer XML
	 * 
	 * @param id id del trajecte. El podeu utilitzar o no
	 * @param client identificador del client. El podeu utilitzar o no
	 * @param moto identificador de la moto. El podeu utilitzar o no
	 * @param local_inici identificador del local d'inici
	 * @param hora_inici hora d'inici de trajecte
	 * @param local_fi identificador del local final de trajecte
	 * @param hora_fi hora de finalització de trajecte
	 */
	
	public void crearReserva(String id,String client,String moto, String cost, String falta, String local_inici,String hora_inici,String fecha_inici, String local_fi,String hora_fi,String fecha_fi) {

		/* TODO: A partir d'aqui creeu el trajecte
		 */
		Consola.escriu("\nReserva amb ID: " + id + "\n");
		Consola.escriu("--------------------------------------\n");
		Consola.escriu("Client: " + client + "\n");
		Consola.escriu("Moto: " + moto + "\n");
                Consola.escriu("Cost: " + cost + "\n");
                Consola.escriu("Faltes: " + falta + "\n");
		Consola.escriu("Local d'inici: " + local_inici + "\n");
		Consola.escriu("Hora d'inici: " + hora_inici + "\n");
		Consola.escriu("Data d'inici: " + fecha_inici + "\n");
		Consola.escriu("Local de finalització: " + local_fi + "\n");
		Consola.escriu("Hora de finalització: " + hora_fi + "\n");
		Consola.escriu("Data de finalització: " + fecha_fi + "\n");
	}

	/**
	 * Crea un nou admin a partir de la informacio obtinguda del fitxer XML
	 * 
	 * @param id id del administrador
	 * @param nom nom del administrador
	 * @param usuari usuari del administrador
	 * @param password password del administrador
	 */
	public void crearAdmin(String id, String nom, String usuari, String password) {

		/* TODO: Creeu aqui el vostre admin
		 */

		Consola.escriu("\nAdmin ID: " + id + "\n");
		Consola.escriu("-----------------\n");
		Consola.escriu("Nom: " + nom + "\n");
		Consola.escriu("Usuari: " + usuari + "\n");
		Consola.escriu("Password: " + password + "\n");
	}
	
	/**
	 * Crea un nou gestor a partir de la informacio obtinguda del fitxer XML
	 * 
	 * @param id id del gestor
	 * @param nom nom del gestor
	 * @param usuari usuari del gestor
	 * @param password password del gestor
	 */
	public void crearGestor(String id, String nom, String usuari, String password) {

		/* TODO: Creeu aqui el vostre admin
		 */

		Consola.escriu("\nGestor ID: " + id + "\n");
		Consola.escriu("-----------------\n");
		Consola.escriu("Nom: " + nom + "\n");
		Consola.escriu("Usuari: " + usuari + "\n");
		Consola.escriu("Password: " + password + "\n");
	}

	/**
	 * Crea un nou client a partir de la informacio obtinguda del fitxer XML
	 * 
	 * @param id id del client
	 * @param nom nom del client
	 * @param dni dni del client
	 * @param adreca adreça del client
	 * @param usuari usuari al sistema del client
	 * @param password password del client
	 * @param vip true si el client es vip. false si no
	 * @param renovacio true si el client renova automaticament. false si no
	 * @param faltes nombre de faltes
     * @return 
	 */

	public void crearClient(String id, String nom, String dni, String adreca, String usuari, String password, String vip, String renovacio, String faltes) {

		/* TODO: Creeu aqui el vostre client
		 */
		
                Motorentals motorent = Motorentals.getInstance();
             
                Consola.escriu("\nClient ID: " + id + "\n");
		Consola.escriu("-----------------\n");
		Consola.escriu("Nom: " + nom + "\n");
		Consola.escriu("Usuari: " + usuari + "\n");
		Consola.escriu("Dni: " + dni + "\n");
		Consola.escriu("Adreça: " + adreca + "\n");
		Consola.escriu("Password: " + password + "\n");
		Consola.escriu("Es VIP: " + vip + "\n");
		Consola.escriu("Renovació automàtica: " + renovacio + "\n");
		Consola.escriu("Nombre de faltes: " + faltes + "\n");
                
                String name=nom.split(" ")[0];
                String surname=nom.split(" ")[1];
                Client c;
                if("true".equals(vip)){
                    c = new VipClient(usuari,password,id,name,surname,dni,Integer.parseInt(faltes),adreca);
                }else{
                    c = new Client(usuari,password,id,name,surname,dni,Integer.parseInt(faltes),adreca);
                }
                motorent.addClient(c);
        }
}
