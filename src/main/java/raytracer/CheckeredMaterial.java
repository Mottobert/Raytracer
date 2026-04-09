package raytracer;

/**
 * Represents a material with a checkered appearance that extends the base material.
 * The checkered effect is calculated with a math formula.
 */
public class CheckeredMaterial extends Material{
    public final Color kDiffuse2;
    public final int repeatU;
    public final int repeatV;

    /**
     * Creates a new checkered material.
     *
     * @param kDiffuse the first diffuse color of the material.
     * @param kSpecular the intensity of the specular lighting.
     * @param shininess the highlight rolloff of the specular lighting.
     * @param kDiffuse2 the second diffuse color of the material.
     * @param repeatU the repetition of the checkered texture in the u direction.
     * @param repeatV the repetition of the checkered texture in the v direction.
     */
    public CheckeredMaterial(Color kDiffuse, double kSpecular, double shininess, Color kDiffuse2, int repeatU,
                             int repeatV){
        super(kDiffuse, kSpecular, shininess);
        this.kDiffuse2 = kDiffuse2;
        this.repeatU = repeatU;
        this.repeatV = repeatV;
    }

    /**
     * Calculate the checkered diffuse color based on the uv coordinate.
     *
     * @param u the u coordinate of the object.
     * @param v the v coordinate of the object.
     * @return the checkered color diffuse color with a repetition of repeatU and repeatV.
     */
    @Override
    public Color getDiffuseColor(double u, double v) {
        int a = (int) (u * this.repeatU);
        int b = (int) (v * this.repeatV);

        if((a+b) % 2 == 0){
            return kDiffuse;
        } else{
            return kDiffuse2;
        }
    }
}
