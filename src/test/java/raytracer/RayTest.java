package raytracer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RayTest {

    @Test
    void testConstructor() {
        Ray r = new Ray(new Vector3d(0,0,-1), new Vector3d(0,0,1));

        assertEquals("(0.0, 0.0, -1.0), (0.0, 0.0, 1.0)", r.toString());
    }

    @Test
    void testAt1() {
        Ray r = new Ray(new Vector3d(0,0,-1), new Vector3d(0,0,1));

        assertEquals("(0.0, 0.0, -1.0)", r.at(0).toString());
    }

    @Test
    void testAt2() {
        Ray r = new Ray(new Vector3d(0,0,-1), new Vector3d(0,0,1));

        assertEquals("(0.0, 0.0, 0.0)", r.at(1).toString());
    }

    @Test
    void testAt3() {
        Ray r = new Ray(new Vector3d(0,0,-1), new Vector3d(0,0,1));

        assertEquals("(0.0, 0.0, 1.0)", r.at(2).toString());
    }

    @Test
    void testNormalized() {
        Ray r = new Ray(new Vector3d(0,0,-1), new Vector3d(1,1,0).normalized());

        assertEquals("(0.0, 0.0, -1.0), (0.7071067811865475, 0.7071067811865475, 0.0)", r.toString());
    }

    @Test
    void testNormalized1() {
        Ray r = new Ray(new Vector3d(0,0,-1), new Vector3d(1,1,0).normalized());

        assertEquals("(0.0, 0.0, -1.0)", r.at(0).toString());
    }

    @Test
    void testNormalized2() {
        Ray r = new Ray(new Vector3d(0,0,-1), new Vector3d(1,1,0).normalized());

        assertEquals("(0.7071067811865475, 0.7071067811865475, -1.0)", r.at(1).toString());
    }

    @Test
    void testNormalized3() {
        Ray r = new Ray(new Vector3d(0,0,-1), new Vector3d(1,1,0).normalized());

        assertEquals("(1.414213562373095, 1.414213562373095, -1.0)", r.at(2).toString());
    }
}