import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            EventoBase eventoBasico = new EventoBase("mostra 1", LocalDate.of(2026, 6, 5), 1);
            EventoPremium eventoPremiumico = new EventoPremium("sfilata 1", LocalDate.of(2026, 6, 5), 7);
            GestioneEventi eventi = new GestioneEventi();
            eventi.aggiungiEventi(eventoBasico);
            eventi.aggiungiEventi(eventoPremiumico);


            Scanner input = new Scanner(System.in);
            System.out.println("Scegli un evento. ");
            String evento = input.nextLine();


            if (evento.equals( eventoBasico.getTitolo())) {
                System.out.println("Nome del prenotato. ");
                String prenotato = input.nextLine();
                eventoBasico.prenota(prenotato);
            }else if (evento.equals(eventoPremiumico.getTitolo())){
                System.out.println("Nome del prenotato. ");
                String prenotato = input.nextLine();
                eventoPremiumico.prenota(prenotato);
            }else {
                System.out.println("l'evento non esiste.");
            }
            eventi.stampaEventi();
            // System.out.println(eventi.stampaPrenotazioni());

        }catch (PrenotazioneException pe){
            System.out.println(pe);
        }catch (IllegalArgumentException iae){
            System.out.println(iae);
        }
    }
}