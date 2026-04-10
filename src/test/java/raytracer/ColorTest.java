/**
 * @author Max Otto - 867497
 */

package raytracer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorTest {

    @Test
    void testConstructor() {
        Color red = new Color(1, 0, 0);

        assertEquals("(1.0, 0.0, 0.0)", red.toString());
    }

    @Test
    void testYellow() {
        Color red = new Color(1, 0, 0);
        Color green = new Color(0, 1, 0);

        assertEquals("(1.0, 1.0, 0.0)", red.plus(green).toString());
    }

    @Test
    void testYellowScaled() {
        Color red = new Color(1, 0, 0);
        Color green = new Color(0, 1, 0);

        Color yellow = red.plus(green);

        assertEquals("(0.5, 0.5, 0.0)", yellow.scaled(0.5).toString());
    }

    @Test
    void testYellowToAwt() {
        Color red = new Color(1, 0, 0);
        Color green = new Color(0, 1, 0);

        Color yellow = red.plus(green);

        assertEquals("java.awt.Color[r=255,g=255,b=0]", yellow.toAwtColor().toString());
    }
}