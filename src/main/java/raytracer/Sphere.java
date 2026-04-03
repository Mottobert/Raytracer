package raytracer;

public class Sphere {
    Vector3d center;
    double radius;

    public Sphere(Vector3d center, double radius){
        this.center = center;
        this.radius = radius;
    }

    public double intersection(Ray ray){
        Vector3d originMinusCenter = ray.origin.minus(this.center);
        double b = ray.direction.dot(originMinusCenter);
        double c = originMinusCenter.dot(originMinusCenter) - (this.radius * this.radius);

        double root = b * b - c;

        if(root < 0) return -1;

        double t1 = -b - Math.sqrt(root);

        if(t1 >= 0) {
            return t1;
        }
        else{
            double t2 = -b + Math.sqrt(root);

            return t2;
        }
    }

    public String toString(){
        return this.center.toString() + " " + this.radius;
    }
}
