package test.java.forme;

import main.java.forme.Cerchio;
import main.java.forme.Rettangolo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CerchioTest {

    @Test
    public void shouldReturnArea() {
        double raggio = 5;
        double expectedResult = 78.539;
        double delta = 0.001;
        Cerchio c = new Cerchio(raggio);

        double actualResult = c.area();
        assertEquals(expectedResult,actualResult,delta);
    }
    @Test
    public void shouldReturnPerimetro() {
        double raggio = 5;
        double expectedResult = Math.PI * 2 * raggio;
        double delta = 0.001;

        Cerchio c = new Cerchio(raggio);

        double actualResult = c.perimetro();
        assertEquals(expectedResult,actualResult,delta);
    }
}