package raytracer;

public class Material {
    protected final Color kDiffuse;
    public final double kSpecular;
    public final double shininess;

    public Material(Color kDiffuse, double kSpecular, double shininess){
        this.kDiffuse = kDiffuse;
        this.kSpecular = Math.max(0.0, Math.min(1.0, kSpecular)); // Clamp specular between 0-1
        this.shininess = shininess;
    }

    public Color getDiffuseColor(double u, double v){
        return this.kDiffuse;
    }
}
