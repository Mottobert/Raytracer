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

    public String toString(){
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
