package raytracer;

public class RefractiveMaterial extends Material{
    double kTransmissive;
    double refractiveIndex;

    public RefractiveMaterial(Color kDiffuse, double kSpecular, double shininess, double kTransmissive, double refractiveIndex){
        super(kDiffuse, kSpecular, shininess);
        this.kTransmissive = kTransmissive;
        this.refractiveIndex = refractiveIndex;
    }
}