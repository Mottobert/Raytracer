package raytracer;

/**
 * Represents a three-dimensional vector.
 * Has basic vector operations like addition, subtraction, scalar product, normalization, reflection and refraction.
 */
public class Vector3d {
    public final double x;
    public final double y;
    public final double z;

    /**
     * Creates a new vector with x, y, z coordinates.
     *
     * @param x the x component.
     * @param y the y component.
     * @param z the z component.
     */
    public Vector3d(double x, double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Adds two vectors component by component.
     *
     * @param other the second vector.
     * @return added vector.
     */
    public Vector3d plus(Vector3d other){
        return new Vector3d(
                this.x + other.x,
                this.y + other.y,
                this.z + other.z
        );
    }

    /**
     * Subtracts two vectors component by component.
     *
     * @param other the second vector.
     * @return subtracted vector.
     */
    public Vector3d minus(Vector3d other){
        return new Vector3d(
                this.x - other.x,
                this.y - other.y,
                this.z - other.z
        );
    }

    /**
     * Calculates the scalar product of two vectors.
     *
     * @param other the second vector.
     * @return Scalar product of the two vectors.
     */
    public double dot(Vector3d other){
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Scales the vector by a scalar.
     *
     * @param s the scalar.
     * @return scaled vector.
     */
    public Vector3d scaled(double s){
        return new Vector3d(
                this.x * s,
                this.y * s,
                this.z * s
        );
    }

    /**
     * Normalizes the vector to have a length of 1.
     *
     * @return normalized vector.
     */
    public Vector3d normalized(){
        double length = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);

        if(length == 0){
            System.out.println("The Vector has a length of 0");
            return new Vector3d(0, 0, 0);
        }
        return new Vector3d(
                this.x / length,
                this.y / length,
                this.z / length
        );
    }

    /**
     * Reflects a vector by the normal vector.
     *
     * @param normal the normal vector.
     * @return reflected vector.
     */
    public Vector3d reflect(Vector3d normal){
        double dot = this.dot(normal);
        return normal.scaled(2 * dot).minus(this);
    }

    /**
     * Refracts a vector at a given normal with a given refractive index.
     *
     * @param normal the normal vector.
     * @param r the refractive index.
     * @return refracted vector.
     */
    public Vector3d refract(Vector3d normal, double r){
        double d = dot(normal);
        if(d < 0) { // incoming
            r = 1 / r;
        } else {
            normal = normal.scaled(-1);
            d =- d;
        }

        double w = -r * d;
        double k = Math.sqrt(1 + (w-r) * (w+r));

        return scaled(r).plus(normal.scaled(w-k));
    }

    public String toString(){
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
