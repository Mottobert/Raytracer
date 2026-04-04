package raytracer;

public class Material {
    Color kDiffuse;
    double kSpecular;

    public Material(Color kDiffuse, double kSpecular){
        this.kDiffuse = kDiffuse;
        this.kSpecular = Math.max(0.0, Math.min(1.0, kSpecular)); // Clamp specular between 0-1
    }
}
