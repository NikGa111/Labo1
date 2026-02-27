import java.time.LocalDate;
import java.util.ArrayList;

public class EventoPremium extends Evento implements Prenotabile{
    private double costoEventoPremium;
    private ArrayList<String> partecipanti = new ArrayList<String>();

    public EventoPremium(String titolo, LocalDate data, int maxPartecipanti) {
        super(titolo, data, maxPartecipanti);
        if (maxPartecipanti <= 5) {
            throw new IllegalArgumentException("Minimo 6 partecipanti.");
        }
        setMaxPartecipanti(getMaxPartecipanti() -5);
        this.costoEventoPremium = 50;
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
        return costoEventoPremium * getPartecipanti().size();
    }

    @Override
    public boolean prenota(String nomePartecipante) throws PrenotazioneException {
        if (getPartecipanti().size() == getMaxPartecipanti() || getData().isBefore(LocalDate.now()) || nomePartecipante == null || nomePartecipante.isEmpty()) {
            return false;
        }
        partecipanti.add(nomePartecipante);
        return true;
    }

}
