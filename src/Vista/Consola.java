// Poseu la consola al paquet que us vagi millor
package Vista;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Interfície d'interacció amb l'usuari mitjançant el terminal.
 *
 * @author Professors disseny de software
 */
public class Consola {

    /** Utilitat per llegir línies senceres de la pantalla */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Mostra un text per pantalla (sense salt de línia al final).
     *
     * @param s text a mostrar.
     */
    public static void escriu(String s) {
        System.out.print(s);
    }

    /**
     * Mostra un enter per pantalla.
     *
     * @param i enter a mostrar.
     */
    public static void escriu(int i) {
        System.out.print(i);
    }

    /**
     * Mostra un nombre flotant per pantalla.
     *
     * @param f nombre flotant a mostrar.
     */
    public static void escriu(float f) {
        System.out.print(f);
    }

    public static void escriu(Calendar date){
        System.out.print(date);
    }
    /**
     * Llegeix un enter per teclat.
     * <p>
     * Aquesta funció no retorna fins que l'usuari ha introduït un enter. En
     * cas que l'usuari introdueixi una cadena, la funció torna a demanar un
     * enter fins que la dada introduïda sigui vàlida.
     * </p>
     *
     * @return enter introduit per l'usuari
     */
    public static int llegeixInt() {
        while (true) {
            // Llegim una cadena, per així consumir salts de línia també
            String buffer = llegeixString();
            try {
                return Integer.valueOf(buffer);
            }
            // Rebem aquesta excepció si les dades no són correctes
            catch (NumberFormatException ex) {
                escriu("Entrada incorrecta. Sisplau poseu un enter: ");
            }
        }
    }

    /**
     * Llegeix una cadena per teclat.
     * <p>
     * Es considera una cadena qualsevol entrada fins a un salt de línia. El
     * salt de línia no s'inclou a la cadena retornada.
     * </p>
     *
     * @return cadena introduida per l'usuari
     */
    public static String llegeixString() {
        return scanner.nextLine();
    }

    /**
     * Obtenir la data actual segons el sistema.
     *
     * @return data actual
     */
    public static Calendar llegeixDataSistema() {
        Calendar date = Calendar.getInstance();
        return date;
    }

}


