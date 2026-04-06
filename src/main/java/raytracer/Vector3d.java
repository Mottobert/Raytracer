package raytracer;

public class Vector3d {
    double x, y, z;

    public Vector3d(double x, double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d plus(Vector3d other){
        return new Vector3d(
                this.x + other.x,
                this.y + other.y,
                this.z + other.z
        );
    }

    public Vector3d minus(Vector3d other){
        return new Vector3d(
                this.x - other.x,
                this.y - other.y,
                this.z - other.z
        );
    }

    public double dot(Vector3d other){
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3d scaled(double s){
        return new Vector3d(
                this.x * s,
                this.y * s,
                this.z * s
        );
    }

    public Vector3d normalized(){
        double length = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);

        if(length == 0){
            System.out.println("The Vector has a length of 0");
            return new Vector3d(0, 0, 0);
        }
        return new Vector3d(
                this.x / length,
                this.y / length,
                this.z / length
        );
    }

    public Vector3d reflect(Vector3d n){
        double dot = this.dot(n);
        return n.scaled(2 * dot).minus(this);
    }

    public Vector3d refract(Vector3d normal, double r){
        double d = dot(normal);
        if(d < 0) { // incoming
            r = 1 / r;
        } else {
            normal = normal.scaled(-1);
            d =- d;
        }

        double w = -r * d;
        double k = Math.sqrt(1 + (w-r) * (w+r));

        return scaled(r).plus(normal.scaled(w-k));
    }

    public String toString(){
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
