import java.time.LocalDate;
import java.util.ArrayList;

public class EventoBase extends Evento implements Prenotabile{
    private double costoEventoBase = 20;
    private ArrayList<String> partecipanti = new ArrayList<String>();

    public EventoBase(String titolo, LocalDate data, int maxPartecipanti) {
        super(titolo, data, maxPartecipanti);
    }
    public ArrayList<String> getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(ArrayList<String> partecipanti) {
        this.partecipanti = partecipanti;
    }

    public void aggiungiPartecipanti(String nomePartecipante){
        partecipanti.add(nomePartecipante);
    }

    @Override
    double calcolaCosto() {
        return costoEventoBase * getPartecipanti().size();
    }

    @Override
    public boolean prenota(String nomePartecipante) throws PrenotazioneException {
        if (getPartecipanti().size() == getMaxPartecipanti() || getData().isBefore(LocalDate.now()) || nomePartecipante == null || nomePartecipante.isEmpty()) {
            throw new PrenotazioneException();
        }
        partecipanti.add(nomePartecipante);
        return true;
    }
}
