package raytracer;

public class CheckeredMaterial extends Material{
    public final Color kDiffuse2;
    public final int repeatU;
    public final int repeatV;

    public CheckeredMaterial(Color kDiffuse, double kSpecular, double shininess, Color kDiffuse2, int repeatU, int repeatV){
        super(kDiffuse, kSpecular, shininess);
        this.kDiffuse2 = kDiffuse2;
        this.repeatU = repeatU;
        this.repeatV = repeatV;
    }

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
