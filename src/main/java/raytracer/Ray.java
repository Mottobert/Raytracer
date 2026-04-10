/**
 * @author Max Otto - 867497
 */

package raytracer;

/**
 * Represents a ray in 3D-space.
 * It consists of an origin and a direction.
 * In the context of raytracing it is used to calculate intersection points.
 */
public class Ray {
    public final Vector3d origin;
    public final Vector3d direction;

    /**
     * Creates a new ray with an origin and a direction.
     *
     * @param origin the origin of the ray.
     * @param direction the direction of the ray.
     */
    public Ray(Vector3d origin, Vector3d direction){
        this.origin = origin;
        this.direction = direction;
    }

    /**
     * Calculates a 3D point along the ray with the parameter t.
     *
     * @param t the distance along the ray.
     * @return 3D point on the ray.
     */
    public Vector3d at(double t){
        return this.origin.plus(direction.scaled(t));
    }

    public String toString(){
        return "(" + this.origin.x + ", " + this.origin.y + ", " + this.origin.z + "), " +
                "(" + this.direction.x + ", " + this.direction.y + ", " + this.direction.z + ")";
    }
}
