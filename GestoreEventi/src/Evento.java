import java.time.LocalDate;
import java.util.ArrayList;

abstract class Evento {
    private String titolo;
    private LocalDate data;
    private int maxPartecipanti;

    public Evento(String titolo, LocalDate data, int maxPartecipanti) {
            this.titolo = titolo;
            this.data = data;
            this.maxPartecipanti = maxPartecipanti;
            if (titolo.isEmpty() || data == null || getData().isBefore(LocalDate.now()) || maxPartecipanti <= 0){
                throw new IllegalArgumentException();
            }
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getMaxPartecipanti() {
        return maxPartecipanti;
    }

    public void setMaxPartecipanti(int maxPartecipanti) {
        this.maxPartecipanti = maxPartecipanti;
    }



    abstract double calcolaCosto();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Evento{");
        sb.append("titolo='").append(titolo).append('\'');
        sb.append(", data=").append(data);
        sb.append(", maxPartecipanti=").append(maxPartecipanti);
        sb.append('}');
        return sb.toString();
    }
}