package raytracer;

/**
 * Represents a basic material with diffuse and specular properties.
 */
public class Material {
    protected final Color kDiffuse;
    public final double kSpecular;
    public final double shininess;

    /**
     * Creates a new light at a given position.
     *
     * @param kDiffuse the diffuse color of the material.
     * @param kSpecular the intensity of the specular lighting.
     * @param shininess the highlight rolloff of the specular lighting.
     */
    public Material(Color kDiffuse, double kSpecular, double shininess){
        this.kDiffuse = kDiffuse;
        this.kSpecular = Math.max(0.0, Math.min(1.0, kSpecular)); // Clamp specular between 0-1
        this.shininess = shininess;
    }

    /**
     * Base implementation for an uv coloring system.
     * Can be overriden by extended classes to color the object based on the uv coordinates.
     *
     * @param u the u coordinate of the object.
     * @param v the v coordinate of the object.
     * @return the color diffuse color.
     */
    public Color getDiffuseColor(double u, double v){
        return this.kDiffuse;
    }
}
