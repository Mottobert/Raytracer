/**
 * @author Max Otto - 867497
 */

package raytracer;

/**
 * Represents a very basic version of light in 3D space.
 */
public class Light {
    public final Vector3d position;

    /**
     * Creates a new light at a given position.
     *
     * @param position the 3D position of the light.
     */
    public Light(Vector3d position){
        this.position = position;
    }
}
