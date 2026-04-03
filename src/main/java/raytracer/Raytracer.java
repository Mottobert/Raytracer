package raytracer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Raytracer extends JFrame {

    public Raytracer() {
        JPanel panel = new JPanel() {
            @Override public void paint(Graphics g) {
                // TODO:
                // - Cameraposition erstellen
                Vector3d camera = new Vector3d(0, 0, -1);
                // - Objekt(e) erstellen
                // - Lichtquelle erstellen
                for(int y_wnd = 0; y_wnd < getHeight(); y_wnd++) {
                    for(int x_wnd = 0; x_wnd < getWidth(); x_wnd++) {
                        double x_ndc = (x_wnd + 0.5) / getWidth();
                        double y_ndc = (y_wnd + 0.5) / getHeight();
                        double x = (2*x_ndc - 1);
                        double y = (1 - 2*y_ndc);
                        // TODO:
                        // - Strahl erzeugen
                        // - Strahl gegen Objekt(e) testen
                        // - Pixel ggf. einfaerben
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