package lab11;

import java.text.NumberFormat;
import java.util.Formatter;

import static lab11.Teilor.calculateNormal;
import static org.junit.jupiter.api.Assertions.*;

class TeilorTest {

    @org.junit.jupiter.api.Test
    void calculateNormalTest1() {
        double res = calculateNormal(0.99, 3);
        assertEquals(res, 0.8444681651330553);
    }
    @org.junit.jupiter.api.Test
    void calculateNormalTest2() {
        double res = calculateNormal(-10, 3);
        assertEquals(res, -0.05441272770061881);
    }

    @org.junit.jupiter.api.Test
    void calculateBigTest1() {
        float res = Teilor.calculateBig(-0.99,3);
        assertEquals(res, 0.8445000052452087);
    }
    @org.junit.jupiter.api.Test
    void calculateBigTest2() {
        float res = Teilor.calculateBig(10,3);
        assertEquals(res, -0.05429999902844429);
    }
}