package Controlador;

import org.xml.sax.helpers.DefaultHandler;

/**
 * Test de carrega de fitxer XML dw MotoRent
 * 
 * @author Professors disseny de software
 */
public class MotoRentXMLTest extends DefaultHandler {

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MotoRentXMLTest("data/MotoRent.xml");
	}

	/**
	 * Parseja el fitxer XML i guarda les dades
	 * 
	 * @param nomFitxer fitxer XML a parsejar
	 */
	public MotoRentXMLTest(String nomFitxer) {
		MotoRentDataManager dataManager = new MotoRentDataManager();
		dataManager.obtenirDades(nomFitxer);
	}
}
