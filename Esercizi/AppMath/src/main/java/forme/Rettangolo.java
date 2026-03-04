package main.java.forme;

public class Rettangolo extends Forma{
    private double base;
    private double altezza;

    public double getBase() {
        return base;
    }

    public void setBase(double _base) {
        this.base = _base;
    }

    public double getAltezza() {
        return altezza;
    }

    public void setAltezza(double _altezza) {
        this.altezza = _altezza;
    }

    public Rettangolo(double base, double _altezza) {
        this.base = base;
        this.altezza = _altezza;
    }

    @Override
    public double area() {
        return base * altezza;
    }
    @Override
    public double perimetro() {
        return 2 * (base + altezza);
    }
}
