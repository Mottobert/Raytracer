/**
 * @author Max Otto - 867497
 */

package raytracer;

/**
 * Represents a Sphere that can be placed in the 3D scene with the given size and material properties.
 */
public class Sphere {
    public final Vector3d center;
    public final double radius;
    public final Material material;

    /**
     * Creates a new Sphere at the center position, with the given radius and specified material.
     *
     * @param center the position of the sphere origin.
     * @param radius the radius of the sphere.
     * @param material the material the sphere should be rendered with.
     */
    public Sphere(Vector3d center, double radius, Material material){
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    /**
     * Checks if the given ray intersects with the Sphere and if so returns the closest intersection point from the rays origin.
     *
     * @param ray the position of the sphere origin.
     * @return the closest point of intersection with the ray, returns -1 when no intersection occurs.
     */
    public double intersection(Ray ray){
        Vector3d originMinusCenter = ray.origin.minus(this.center);
        double b = ray.direction.dot(originMinusCenter);
        double c = originMinusCenter.dot(originMinusCenter) - (this.radius * this.radius);

        double root = b * b - c;

        if(root < 0) return -1;

        double epsilon = 0.000001;
        double t1 = -b - Math.sqrt(root);

        if(t1 > epsilon) {
            return t1;
        }
        else{
            double t2 = -b + Math.sqrt(root);

            if(t2 > epsilon){
                return t2;
            } else {
                return -1;
            }
        }
    }

    /**
     * Calculates the normal vector at the given position.
     *
     * @param position the position the normal vector should be calculated at.
     * @return the normal vector at the given position.
     */
    public Vector3d normal(Vector3d position){
        return position.minus(this.center).scaled(1/this.radius);
    }

    /**
     * Calculates the diffuse color depending on the angle between the normal vector and the light position and the
     * diffuse color specified in the material.
     *
     * @param normal the normal vector.
     * @param light the light position.
     * @return the diffuse color.
     */
    public Color diffuseShading(Vector3d normal, Vector3d light){
        double iDiffuse = Math.max(0, normal.dot(light));

        double[] uv = this.makeUVs(normal);

        Color color = this.material.getDiffuseColor(uv[0], uv[1]);

        return color.scaled(iDiffuse);
    }

    /**
     * Calculates the specular color depending on the angle between the normal vector, the light position and the view
     * direction and the specular shininess and specular intensity specified in the material.
     *
     * @param normal the normal vector.
     * @param light the light position.
     * @param view the view direction.
     * @return the diffuse color.
     */
    public Color specularShading(Vector3d normal, Vector3d light, Vector3d view){
        Vector3d r = light.reflect(normal);
        double shininess = this.material.shininess;
        double iSpecular = Math.pow(Math.max(0, -r.dot(view)), shininess);

        double kSpecular = this.material.kSpecular;

        return new Color(
                kSpecular * iSpecular,
                kSpecular * iSpecular,
                kSpecular * iSpecular
        );
    }

    /**
     * Calculates the diffuse and specular contribution to the shading.
     *
     * @param position the position on the sphere.
     * @param lightSource the light source.
     * @param view the view direction.
     * @return the combined color for diffuse and specular lighting.
     */
    public Color shading(Vector3d position, Light lightSource, Vector3d view){
        Vector3d n = normal(position);
        Vector3d l = lightSource.position.minus(position).normalized();

        Color dColor = diffuseShading(n, l);
        Color sColor = specularShading(n, l, view);

        return dColor.plus(sColor);
    }

    /**
     * Calculates the uv-coordinates for a sphere depending on the normal vector.
     *
     * @param normal the normal vector.
     * @return the normalized uv-coordinates.
     */
    public double[] makeUVs(Vector3d normal){
        double theta = Math.atan2(normal.z, normal.x);
        double rho = Math.acos(-normal.y);

        return new double[]{
                theta/(2*Math.PI),
                rho/Math.PI
        };
    }

    public String toString(){
        return this.center.toString() + " " + this.radius;
    }
}
