package raytracer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Raytracer extends JFrame {
    Color recursiveRaytrace(Ray ray, List<Sphere> objects, Light light, int depth){
        if(depth == 0){
            return new Color(0, 0, 0);
        }

        Sphere intersectionObject = null;
        double tMin = Double.MAX_VALUE;

        for (Sphere object : objects) {
            double t = object.intersection(ray);
            if(t >= 0 && t < tMin){
                tMin = t;
                intersectionObject = object;
            }
        }

        if(intersectionObject == null){
            return new Color(0, 0, 0.92); // background color
        }

        Color color = new Color(0, 0, 0);
        Vector3d p = ray.at(tMin);

        // If point is in shadow color stays at black
        // If point is not in shadow we calculate the color
        if(!inShadow(p, objects, light)){
            color = intersectionObject.shading(p, light, ray.direction);
        }

        Vector3d d = ray.direction.normalized();
        Vector3d n = intersectionObject.normal(p).normalized();

        if(intersectionObject.material instanceof RefractiveMaterial){
            double e = 0.0001;
            RefractiveMaterial material = (RefractiveMaterial)intersectionObject.material;
            Vector3d dRefracted = d.refract(n, material.refractiveIndex);

            double brokenRay = dRefracted.dot(dRefracted);

            if(brokenRay > e){
                color = computeRefraction(p, dRefracted, objects, light, depth, material, color);
            } else {
                // calculate new ray and call recursiveRaytrace again
                color = computeReflection(p, d, n, objects, light, depth, intersectionObject.material, color);
            }
        } else {
            // calculate new ray and call recursiveRaytrace again
            color = computeReflection(p, d, n, objects, light, depth, intersectionObject.material, color);
        }

        return color;
    }

    Color computeRefraction(Vector3d hitPoint, Vector3d direction, List<Sphere> objects, Light light, int depth,
                            RefractiveMaterial material, Color color){
        Ray refractedRay = new Ray(hitPoint, direction);

        Color recursiveColor = recursiveRaytrace(refractedRay, objects, light, depth - 1);
        recursiveColor = recursiveColor.scaled(material.kTransmissive);
        return color.plus(recursiveColor);
    }

    Color computeReflection(Vector3d hitPoint, Vector3d direction, Vector3d normal, List<Sphere> objects, Light light, int depth,
                            Material material, Color color){
        Vector3d dReflected = direction.minus(normal.scaled(2 * direction.dot(normal)));

        Ray reflectedRay = new Ray(hitPoint, dReflected);

        Color recursiveColor = recursiveRaytrace(reflectedRay, objects, light, depth - 1);
        recursiveColor = recursiveColor.scaled(material.kSpecular);
        return color.plus(recursiveColor);
    }

    boolean inShadow(Vector3d p, List<Sphere> objects, Light light){
        Vector3d l = light.position.minus(p).normalized();
        Ray shadowRay = new Ray(p, l);

        for(Sphere object : objects){
            if(object.material instanceof RefractiveMaterial){
                continue;
            }

            if(object.intersection(shadowRay) >= 0){
                return true;
            }
        }

        return false;
    }

    public Raytracer() {
        JPanel panel = new JPanel() {
            @Override public void paint(Graphics g) {
                // TODO:
                // - Cameraposition erstellen
                Vector3d camera = new Vector3d(0, 0, -1);

                // - Objekt(e) erstellen
                List<Sphere> objects = new ArrayList<Sphere>();
                CheckeredMaterial checkeredMaterial = new CheckeredMaterial(new Color(0,1,0), 0.5, 100,
                        new Color(1,0,1), 16, 8);
                Sphere sphere = new Sphere(new Vector3d(0,0,1), 0.5, checkeredMaterial);
                objects.add(sphere);

                Material earthMaterial = new Material(new Color(0.96, 0.86, 0.74), 0.1, 100);
                Sphere earth = new Sphere(new Vector3d(0, -1001, 0), 1000, earthMaterial);
                objects.add(earth);

                objects.add(new Sphere(new Vector3d(-0.5, 0, 0.25), 0.25, new Material(
                        new Color(1, 0, 0), 0.8, 25)));

                RefractiveMaterial refractiveMaterial = new RefractiveMaterial(new Color(0, 0, 0), 1, 150,
                        1, 1.5);
                Sphere transparentSphere = new Sphere(new Vector3d(0.25, 0, 0), 0.2, refractiveMaterial);
                objects.add(transparentSphere);

                RefractiveMaterial refractiveMaterial2 = new RefractiveMaterial(new Color(0, 0, 0), 1,
                        150, 1, 1/1.5);
                Sphere innerSphere = new Sphere(new Vector3d(0.25, 0, 0), 0.18, refractiveMaterial2);
                objects.add(innerSphere);

                // - Lichtquelle erstellen
                Light light = new Light(new Vector3d(0,2,-1));

                for(int y_wnd = 0; y_wnd < getHeight(); y_wnd++) {
                    for(int x_wnd = 0; x_wnd < getWidth(); x_wnd++) {
                        double x_ndc = (x_wnd + 0.5) / getWidth();
                        double y_ndc = (y_wnd + 0.5) / getHeight();
                        double x = (2*x_ndc - 1);
                        double y = (1 - 2*y_ndc);
                        // TODO:
                        // - Strahl erzeugen
                        Vector3d normalizedDirection = new Vector3d(x, y, 0).minus(camera).normalized();
                        Ray ray = new Ray(camera, normalizedDirection);

                        // - Strahl gegen Objekt(e) testen
                        Color c = recursiveRaytrace(ray, objects, light, 10);

                        // - Pixel ggf. einfaerben
                        g.setColor(c.toAwtColor());
                        g.fillRect(x_wnd, y_wnd, 1, 1);
                    }
                }
                System.out.println("Done !");
            }
        };
        add(panel);
        pack();
        setSize(500 ,500);
        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String args []) {
        new Raytracer().setTitle("Raytracer");
    }
}
