package raytracer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector3dTest {

    @Test
    void testConstructor() {
        Vector3d v = new Vector3d(1, 0, 0);

        assertEquals("(1.0, 0.0, 0.0)", v.toString());
    }

    @Test
    void testPlus() {
        Vector3d v1 = new Vector3d(1, 0, 0);
        Vector3d v2 = new Vector3d(0, 1, 0);

        Vector3d p = v1.plus(v2);

        assertEquals("(1.0, 1.0, 0.0)", p.toString());
    }

    @Test
    void testMinus() {
        Vector3d v1 = new Vector3d(1, 0, 0);
        Vector3d v2 = new Vector3d(0, 1, 0);

        Vector3d p = v1.minus(v2);

        assertEquals("(1.0, -1.0, 0.0)", p.toString());
    }

    @Test
    void testDot() {
        Vector3d v1 = new Vector3d(1, 0, 0);
        Vector3d v2 = new Vector3d(0, 1, 0);

        double p = v1.dot(v2);

        assertEquals(0.0, p);
    }

    @Test
    void testDot2() {
        Vector3d v1 = new Vector3d(1, 0, 0);

        double p = v1.dot(v1);

        assertEquals(1.0, p);
    }

    @Test
    void testNormalized() {
        Vector3d v1 = new Vector3d(1, 0, -1);

        Vector3d p = v1.normalized();

        assertEquals("(0.7071067811865475, 0.0, -0.7071067811865475)", p.toString());
    }

    @Test
    void testToDegrees() {
        Vector3d v1 = new Vector3d(1, 0, 0);
        Vector3d v = new Vector3d(0.7071067811865475, 0.0, -0.7071067811865475);

        double e = Math.toDegrees(Math.acos(v1.dot(v)));

        assertEquals(45.00000000000001, e);
    }

    @Test
    void testPlusPlusScaled() {
        Vector3d v1 = new Vector3d(1, 0, 0);
        Vector3d v2 = new Vector3d(0, 1, 0);
        Vector3d v3 = new Vector3d(0, 0, 1);

        Vector3d e = v1.plus(v2).plus(v3).scaled(2);

        assertEquals("(2.0, 2.0, 2.0)", e.toString());
    }

    @Test
    void testReflect() {
        Vector3d n = new Vector3d(0, 1, 0);
        Vector3d l = new Vector3d(-1, 1, 0).normalized();

        assertEquals("(0.7071067811865475, 0.7071067811865475, 0.0)", l.reflect(n).toString());
    }

    @Test
    void testRefract() {
        Vector3d n = new Vector3d(0, 1, 0);
        Vector3d d = new Vector3d(1, -1, 0).normalized();

        assertEquals(45.00000000000001, Math.toDegrees(Math.acos(-d.dot(n))));

        Vector3d refractedRayIncoming = d.refract(n, 1.5);

        assertEquals("(0.4714045207910316, -0.8819171036881969, 0.0)", refractedRayIncoming.toString());

        assertEquals(28.125505702055708, Math.toDegrees(Math.acos(-refractedRayIncoming.dot(n))));

        assertEquals("(0.7071067811865475, -0.7071067811865477, 0.0)", refractedRayIncoming.refract(n.scaled(-1), 1.5).toString());

        assertEquals("(0.7071067811865475, -0.7071067811865477, 0.0)", refractedRayIncoming.refract(n, 1/1.5).toString());
    }
}