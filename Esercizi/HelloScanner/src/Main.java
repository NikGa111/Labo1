/**
 * Esercizio Scanner
 * Programma che permette di capire il funzionamento degli input con la classe scanner
 *
 * @author Nicola Galeano
 * @version 17.10.2025
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int eta = 0;
        String nome = "";
        String cognome = "";
        Scanner input = new Scanner(System.in);// Creato un oggetto scanner

        try {
        System.out.print("Insrisci il tuo nome: ");
        nome = input.nextLine();// Inserisce nella variabile nome ciò che viene inserito da tastiera.

        System.out.print("Inserisci il tuo cognome: ");
        cognome = input.nextLine();


        System.out.print("Inserisci la tua età: ");
        eta = input.nextInt();

        }catch (InputMismatchException ime ) {
            System.out.println("Inserire la tua età in formato numerico.");
        }
        System.out.println("Nome: " + nome + " Cognome: " + cognome + " eta: " + eta);
    }
}