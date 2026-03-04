/**
 * Esercizio Indovina
 * Programma che ti chiede di indovinare il numero
 *
 * @author Nicola Galeano
 * @version 17.10.2025
 */
import java.awt.image.ImagingOpException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Scanner imput = new Scanner(System.in);
        Random rand = new Random();
        int numeroDaIndovinare = rand.nextInt(100);
        int numeroTentativi = 0;

        while(true){
            numeroTentativi++;
            try {
                System.out.println("Indovina il numero tra 1 e 100:");
                System.out.print("Il tuo tentativo: ");
                int tentativo = imput.nextInt();
                if(tentativo < 0 || tentativo > 100){
                    throw new InputMismatchException();
                }
                if (tentativo < numeroDaIndovinare){
                    System.out.println("Troppo basso!");
                }else if(tentativo > numeroDaIndovinare){
                    System.out.println("Troppo alto!");
                }else {
                    System.out.println("Bravo! Hai indovinato in " + numeroTentativi +  " tentativi.");
                    break;
                }
            }catch (InputMismatchException ime){
                System.out.println("Inserire un intero compreso tra 1 e 100 in cifre.");
                imput.nextLine();
                numeroTentativi--;
            }
        }
    }
}