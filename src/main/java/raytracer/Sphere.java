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

    public Color diffuseShading(Vector3d normal, Vector3d light){
        double iDiffuse = Math.max(0, normal.dot(light));

        Color color = this.material.kDiffuse;

        Color kDiffuse = new Color(color.r * iDiffuse, color.g * iDiffuse, color.b * iDiffuse);

        return kDiffuse;
    }

    public Color specularShading(Vector3d normal, Vector3d light, Vector3d view){
        Vector3d r = light.reflect(normal);
        double shininess = 100;
        double iSpecular = Math.pow(Math.max(0, -r.dot(view)), shininess);

        double kSpecular = this.material.kSpecular;

        return new Color(kSpecular * iSpecular, kSpecular * iSpecular,kSpecular * iSpecular);
    }

    public Color shading(Vector3d p, Light lightSource, Vector3d view){
        Vector3d normal = normal(p);
        Vector3d light = lightSource.position.minus(p).normalized();

        Color dColor = diffuseShading(normal, light);
        Color sColor = specularShading(normal, light, view);

        return new Color(dColor.r + sColor.r, dColor.g + sColor.g, dColor.b + sColor.b);
    }

    public double[] makeUVs(Vector3d normal){
        double theta = Math.atan2(normal.z, normal.x);
        double rho = Math.acos(-normal.y);

        return new double[]{theta/(2*Math.PI), rho/Math.PI};
    }

    public String toString(){
        return this.center.toString() + " " + this.radius;
    }
}
