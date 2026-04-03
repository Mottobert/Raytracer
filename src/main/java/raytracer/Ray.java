package raytracer;

public class Ray {
    Vector3d origin;
    Vector3d direction;

    public Ray(Vector3d origin, Vector3d direction){
        this.origin = origin;
        this.direction = direction;
    }

    public Vector3d at(double t){
        return this.origin.plus(direction.scaled(t));
    }

    public String toString(){
        return "(" + this.origin.x + ", " + this.origin.y + ", " + this.origin.z + "), " +
                "(" + this.direction.x + ", " + this.direction.y + ", " + this.direction.z + ")";
    }
}
