package raytracer;

public class Sphere {
    public final Vector3d center;
    public final double radius;
    public final Material material;

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

        double epsilon = 0.000001;
        double t1 = -b - Math.sqrt(root);

        if(t1 > epsilon) {
            return t1;
        }
        else{
            double t2 = -b + Math.sqrt(root);

            if(t2 > epsilon){
                return t2;
            } else {
                return -1;
            }
        }
    }

    public Vector3d normal(Vector3d p){
        return p.minus(this.center).scaled(1/this.radius);
    }

    public Color diffuseShading(Vector3d normal, Vector3d light){
        double iDiffuse = Math.max(0, normal.dot(light));

        double[] uv = this.makeUVs(normal);

        Color color = this.material.getDiffuseColor(uv[0], uv[1]);

        return new Color(
                color.r * iDiffuse,
                color.g * iDiffuse,
                color.b * iDiffuse
        );
    }

    public Color specularShading(Vector3d normal, Vector3d light, Vector3d view){
        Vector3d r = light.reflect(normal);
        double shininess = this.material.shininess;
        double iSpecular = Math.pow(Math.max(0, -r.dot(view)), shininess);

        double kSpecular = this.material.kSpecular;

        return new Color(
                kSpecular * iSpecular,
                kSpecular * iSpecular,
                kSpecular * iSpecular
        );
    }

    public Color shading(Vector3d p, Light lightSource, Vector3d view){
        Vector3d n = normal(p);
        Vector3d l = lightSource.position.minus(p).normalized();

        Color dColor = diffuseShading(n, l);
        Color sColor = specularShading(n, l, view);

        return new Color(
                dColor.r + sColor.r,
                dColor.g + sColor.g,
                dColor.b + sColor.b
        );
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
