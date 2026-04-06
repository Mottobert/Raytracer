package raytracer;

public class Color {
    public final double r;
    public final double g;
    public final double b;

    public Color(double r, double g, double b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color plus(Color other){
        return new Color(this.r + other.r, this.g + other.g, this.b + other.b);
    }

    public Color scaled(double s){
        return new Color(this.r * s, this.g * s, this.b * s);
    }

    public java.awt.Color toAwtColor(){
        return new java.awt.Color((float)Math.min(this.r, 1), (float)Math.min(this.g, 1), (float)Math.min(this.b, 1));
    }

    public String toString(){
        return "(" + this.r + ", " + this.g + ", " + this.b + ")";
    }
}
