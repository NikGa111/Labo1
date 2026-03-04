package main.java.forme;

public class Cerchio extends Forma{
    private double raggio;

    public double getRaggio() {
        return raggio;
    }

    public void setRaggio(double _raggio) {
        this.raggio = _raggio;
    }

    public Cerchio(double _raggio) {
        this.raggio = _raggio;
    }

    @Override
    public double area() {
        return Math.PI * raggio * raggio;
    }
    @Override
    public double perimetro() {
        return 2 * Math.PI * raggio;
    }
}
