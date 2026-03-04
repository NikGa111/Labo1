/**
 * Esercizio che vede il funzionamento dello stack negli errori
 *
 * @author Nicola Galeano
 * @version 10.10.2025
 */
public class Main {
    public static void main(String[] args) {
        metodoA(0);
    }
    public static void  metodoA(int n){
        metodoB(n);
    }
    public static void  metodoB(int n){
        metodoC(n);
    }
    public static void  metodoC(int n){
        int result = 1/n;
        System.out.println("risultato: " + result);
    }
}