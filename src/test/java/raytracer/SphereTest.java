package raytracer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SphereTest {

    @Test
    void testConstructor() {
        Sphere sphere = new Sphere(new Vector3d(0,0,1), 1);

        assertEquals("(0.0, 0.0, 1.0) 1.0", sphere.toString());
    }

    @Test
    void testIntersection() {
        Sphere sphere = new Sphere(new Vector3d(0,0,1), 1);
        Ray ray = new Ray(new Vector3d(0,0,-1), new Vector3d(0,0,1));

        assertEquals(1.0, sphere.intersection(ray));
        assertEquals("(0.0, 0.0, 0.0)", ray.at(1.0).toString());
    }

    @Test
    void testIntersection2() {
        Sphere sphere = new Sphere(new Vector3d(0,0,1), 1);
        Ray ray2 = new Ray(new Vector3d(0,0,-1), new Vector3d(0,1,1).normalized());

        assertEquals(-1.0, sphere.intersection(ray2));
    }

    @Test
    void testIntersection3() {
        Sphere sphere = new Sphere(new Vector3d(0,0,1), 1);
        Ray ray3 = new Ray(new Vector3d(0,0,-1), new Vector3d(0,0.5,1).normalized());

        assertEquals("(0.0, 0.6000000000000001, 0.20000000000000018)", ray3.at(sphere.intersection(ray3)).toString());
    }
}