/**
 * Esercizio VerificaCodiceProdotto
 * Permette di verificare il codice di un prodotto.
 *
 * @author Nicola Galeano
 * @version 10.09.2025
 */
public class Main {
    public static void main(String[] args) {
        for (String arg : args){
            if (!arg.startsWith("PROD-")){
                System.out.println("Codice non valido: " + arg);
                System.out.println("Motivo: Il codice deve iniziare con 'PROD-'. ");
            } else if (arg.length() < 9) {
                System.out.println("Codice non valido: " + arg);
                System.out.println("Motivo: Il codice deve contenere almeno 4 cifre numeriche dopo 'PROD-'.");
            } else {
                String codiceProdotto = arg.substring(5);
                try {
                    int numeroCodiceProdotto = Integer.parseInt(codiceProdotto);
                    System.out.println("Codice valido: " + arg);
                }catch (NumberFormatException nfe){
                    System.out.println("Codice non valido: " + arg);
                    System.out.println("Motivo: Il codice deve contenere almeno 4 cifre numeriche dopo 'PROD-'.");
                }
            }
        }
    }
}