/**
 * @author Max Otto - 867497
 */

package raytracer;

/**
 * Represents a color with r, g, and b values.
 */
public class Color {
    public final double r;
    public final double g;
    public final double b;

    /**
     * Creates a new light at a given position.
     *
     * @param r the red component of the color.
     * @param g the green component of the color.
     * @param b the blue component of the color.
     */
    public Color(double r, double g, double b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Adds two colors component by component.
     *
     * @param other the second color.
     * @return the addition of the two colors.
     */
    public Color plus(Color other){
        return new Color(
                this.r + other.r,
                this.g + other.g,
                this.b + other.b
        );
    }

    /**
     * Scales the color by a scalar.
     *
     * @param s the scalar.
     * @return scaled color.
     */
    public Color scaled(double s){
        return new Color(
                this.r * s,
                this.g * s,
                this.b * s
        );
    }

    /**
     * Converts the color from the internal color class to the java.awt.Color class.
     *
     * @return the converted color as a java.awt.Color.
     */
    public java.awt.Color toAwtColor(){
        return new java.awt.Color(
                (float)Math.min(this.r, 1),
                (float)Math.min(this.g, 1),
                (float)Math.min(this.b, 1)
        );
    }

    public String toString(){
        return "(" + this.r + ", " + this.g + ", " + this.b + ")";
    }
}
