public class PrenotazioneException extends Exception{
    Exception PrenotazioneException = new Exception();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrenotazioneException{");
        sb.append("Dati non validi, impossibile prenotare.");
        sb.append('}');
        return sb.toString();
    }
}
