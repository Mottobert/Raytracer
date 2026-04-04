package raytracer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Raytracer extends JFrame {
    Color raytrace(Ray ray, java.util.List<Sphere> objects, Light light){
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
            return new Color(0, 0, 0.92);
        } else {
            Vector3d p = ray.at(tMin);
            return intersectionObject.shading(p, light, ray.direction);
        }
    }

    public Raytracer() {
        JPanel panel = new JPanel() {
            @Override public void paint(Graphics g) {
                // TODO:
                // - Cameraposition erstellen
                Vector3d camera = new Vector3d(0, 0, -1);

                // - Objekt(e) erstellen
                java.util.List<Sphere> objects = new java.util.ArrayList<Sphere>();
                CheckeredMaterial checkeredMaterial = new CheckeredMaterial(new Color(0,1,0), 0.5, 100,
                        new Color(1,0,1), 16, 8);
                Sphere sphere = new Sphere(new Vector3d(0,0,1), 0.5, checkeredMaterial);
                objects.add(sphere);

                Material earthMaterial = new Material(new Color(0.96, 0.86, 0.74), 0.1, 100);
                Sphere earth = new Sphere(new Vector3d(0, -1001, 0), 1000, earthMaterial);
                objects.add(earth);

                objects.add(new Sphere(new Vector3d(-0.5, 0, 0.25), 0.25, new Material(
                        new Color(1, 0, 0), 0.8, 25)));

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
                        Color c = raytrace(ray, objects, light);

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