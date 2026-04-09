package raytracer;

/**
 * Represents a refractive material that extends the base material.
 * It adds the transmissive and refractive index property.
 */
public class RefractiveMaterial extends Material{
    public final double kTransmissive;
    public final double refractiveIndex;

    /**
     * Creates a new refractive material.
     *
     * @param kDiffuse the diffuse color of the material.
     * @param kSpecular the intensity of the specular lighting.
     * @param shininess the highlight rolloff of the specular lighting.
     * @param kTransmissive the transmissive value (how transparent is the object).
     * @param refractiveIndex the refractive index (how much does the lightray is bend).
     */
    public RefractiveMaterial(Color kDiffuse, double kSpecular, double shininess, double kTransmissive,
                              double refractiveIndex){
        super(kDiffuse, kSpecular, shininess);
        this.kTransmissive = kTransmissive;
        this.refractiveIndex = refractiveIndex;
    }
}