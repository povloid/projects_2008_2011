/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package povloid;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.ArrayList;
import java.util.Random;

/**
 * Povloid super class  :)
 */
public class Povloid extends JComponent implements Runnable {

    BufferedImage image;
    private boolean wF = true;
    Font f2;
    int CAPTION_FONT_SIZE = 24;
    String pan = "а�аАаКаОаЙ б�аО PAN";
    String caption = "а�аАаКаОаЙ б�аО CAPTION";
    java.util.List<String> liastPANs;
    int offset = 0; // old i
    int counter = 0;
    BufferedImage bi = null;
    int bw = 0, bh = 0;

    /**
     * Create
     */
    public Povloid() {
        // local vars init here
        f2 = new Font("Arial", Font.BOLD, 55);


    //setDoubleBuffered(true); //todo mozhno poigratsja tut
    }

    /**
     * Start thread
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Thread method
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break; // break at interr
            }

            // repaint canvas
            this.repaint(1);

            // next line
            this.offset++;
            if (this.offset >= this.liastPANs.size()) {
                this.offset = 0;
            }

            // counter for color
            this.counter++;
        }
    }

    /**
     */
    @Override
    public void paintComponent(Graphics g) {
        //super.paint(g);

        int x = (getWidth() - image.getWidth()) / 2;
        int y = (getHeight() - image.getHeight()) / 2;

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //-------------------------------

        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout tl2b = new TextLayout(pan, f2, frc);
        float sw2 = (float) tl2b.getBounds().getWidth();
        float sh2 = (float) tl2b.getBounds().getHeight();

        //-------------------------------

        //g2d.setColor(Color.red);
        Shape s = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
        //g2d.draw(s);


        GradientPaint gradient = new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), Color.ORANGE, true);


        int xPan = (int) (getWidth() / 2 - sw2 / 2), yPan = 0;

        g2d.setPaint(gradient);
        g2d.fill(s);
        //   g2d.drawText(g2d, caption, new Font("Arial", Font.PLAIN, CAPTION_FONT_SIZE), CAPTION_Y, CAPTION_COLOR_1);


        g2d.drawImage(image, x, y, null);
        //g2d.drawString(s,getWidth() / 2 - sw2 / 2,getHeight()/2 - sh2 /2);


        g2d.setFont(f2);
        g2d.setColor(Color.BLACK);
        //g2d.drawImage(image, x, y, null);
        //g2d.fill(s);

        String tpan = "";
        for (int j = offset, ind = 0, jy = 130; j < liastPANs.size() && ind < 13; j++, ind++, jy += 45) {
            if (!wF) {
                return;
            }

            if (ind == 6) {
                //g2d.setColor((this.counter % 10 < 6) ? Color.RED : Color.ORANGE); // eto vmesto vtorogo cicla
                g2d.setColor(Color.RED);
                tpan = liastPANs.get(j);
                yPan = jy;
            } else {
                g2d.setColor(Color.GRAY);
            }

            g2d.drawString(liastPANs.get(j), xPan, jy);


        }

    // if (tpan.equals(pan)) {
    //     break;
    // }


    /* boolean cF = false;
    for (int i = 0; i < 10; i++) {
    if (!wF) {
    return;
    }


    if (cF) {
    g2d.setPaint(Color.ORANGE);
    } else {
    g2d.setPaint(Color.RED);
    }

    g2d.drawString(pan, xPan, yPan);
    cF = !cF;

    try {
    Thread.sleep(1000);
    } catch (InterruptedException ex) {
    }
    }*/

    }

    /**
     * Test run
     *
     * @param args Args
     * @throws Exception Exception
     */
    public static void main(String[] args) throws Exception {
        Povloid povloid = new Povloid();

        // img
        ClassLoader cl = Povloid.class.getClassLoader();
        povloid.image = javax.imageio.ImageIO.read(cl.getResourceAsStream("povloid/00.jpg"));

        // lIAst
        povloid.liastPANs = getPanList();
        Random random = new Random();
        //for (int i = 0; i < 2000; i++) povloid.liastPANs.add(Long.toOctalString(random.nextLong()));

        // frame
        JFrame frame = new JFrame("Povloid super star ***");
        frame.setSize(800, 600);
        frame.getContentPane().add(povloid);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // start
        povloid.start();
    }

    static java.util.List<String> getPanList() {

        java.util.List<String> list = new ArrayList<String>();

        for (int i = 0; i < 2000; i++) {
            long l = (long) (Math.random() * 9000000000000000d) + 1000000000000000l;
            list.add(l + "");
        }

        return list;

    }
}
