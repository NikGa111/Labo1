import java.util.ArrayList;

public class GestioneEventi {
    private ArrayList<Evento> eventi;

    public GestioneEventi() {
        this.eventi = new ArrayList<Evento>();
    }

    public ArrayList<Evento> getEventi() {
        return eventi;
    }

    public void setEventi(ArrayList<Evento> eventi) {
        this.eventi = eventi;
    }

    public void aggiungiEventi(Evento evento){
        eventi.add(evento);
    }

    public void stampaEventi (){
        for (int i = 0; i < getEventi().size(); i++) {
            System.out.println(getEventi().get(i).toString());
        }
    }
    public void stampaPrenotazioni(){
        for (int i = 0; i < getEventi().size(); i++) {
            if (getEventi().get(i) instanceof EventoPremium) {
                EventoPremium eventoP = (EventoPremium) getEventi().get(i);
                if (eventoP.getPartecipanti().size() < eventoP.getMaxPartecipanti()){
                    System.out.println(eventoP.getPartecipanti());
                }
            }
            if (getEventi().get(i) instanceof EventoBase) {
                EventoBase eventoB = (EventoBase) getEventi().get(i);
                if (eventoB.getPartecipanti().size() < eventoB.getMaxPartecipanti()){
                    System.out.println(eventoB.getPartecipanti());
                }
            }
        }
    }

    public void prenota(String nomePartecipante, int indice){
        if (getEventi().get(indice) instanceof EventoPremium) {
            EventoPremium eventoP = (EventoPremium) getEventi().get(indice);
            if (eventoP.getPartecipanti().size() < eventoP.getMaxPartecipanti()){
                eventoP.aggiungiPartecipanti(nomePartecipante);
            }
        }else if (getEventi().get(indice) instanceof EventoBase) {
            EventoBase eventoB = (EventoBase) getEventi().get(indice);
            if (eventoB.getPartecipanti().size() < eventoB.getMaxPartecipanti()){
            eventoB.aggiungiPartecipanti(nomePartecipante);
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GestioneEventi{");
        sb.append("eventi=").append(eventi);
        sb.append('}');
        return sb.toString();
    }

}
