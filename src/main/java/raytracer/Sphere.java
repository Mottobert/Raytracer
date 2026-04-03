package raytracer;

public class Sphere {
    Vector3d center;
    double radius;
    Material material;

    public Sphere(Vector3d center, double radius, Material material){
        this.center = center;
        this.radius = radius;
        this.material = material;
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

    public Vector3d normal(Vector3d p){
        return p.minus(this.center).scaled(1/this.radius);
    }

    public Color diffuseShading(Vector3d p, Light lightSource){
        Vector3d l = lightSource.position.minus(p).normalized();
        double iDiffuse = Math.max(0, normal(p).dot(l));

        Color color = this.material.kDiffuse;

        Color kDiffuse = new Color(color.r * iDiffuse, color.g * iDiffuse, color.b * iDiffuse);

        return kDiffuse;
    }

    public String toString(){
        return this.center.toString() + " " + this.radius;
    }
}
