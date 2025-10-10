/**
 * Esercizio QuadratoNumero
 * Permette di sapere di quale numero è il quadrato.
 *
 * @author Nicola Galeano
 * @version 10.09.2025
 */
public class Main {
    public static void main(String[] args) {
        for(String arg :args){
            try {
                double numeroDouble = Double.parseDouble(arg);
                try {
                    String numeroString = Double.toString(numeroDouble);
                    int numero = Integer.parseInt(numeroString);
                    if (numero < 0) {
                        System.out.println(numero + " --> Errore: il numero deve essere positivo .");
                    }
                    System.out.println(numero + " → quadrato: " + numero * numero);
                }catch(NumberFormatException nfe){
                    System.out.println(arg + " → Errore: input non intero  ");
                }
            }catch(NumberFormatException nfe){
                System.out.println(arg + " → Errore: input non numerico  ");
            }
        }
    }
}