/**
 * Esercizio che vede il try, catch e finally
 *
 * @author Nicola Galeano
 * @version 10.10.2025
 */
public class Main {
    public static void main(String[] args) {
        //Cicliamo args per verificare se sono presenti solo numeri
        for ( String arg : args ) {
            try {
                double numero = Double.parseDouble(arg);
                System.out.println(numero);
            }catch (NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
            }finally {
                System.out.println("eseguito");
            }
        }
    }
}