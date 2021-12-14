package edu.cornellcollege;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Random;
import javax.swing.JPanel;

public class RayOfSunPanel extends  JPanel implements ActionListener {

    private static final Color BG_COLOR = new Color(12, 12, 12);
    private final Color FG_COLOR = new Color(200, 200, 200);

    private static final double MARGIN = 0.5;
    private static final double SPEED = 64;
    private static final float LINE_THICKNESS = 2;
    private int numberOfLines;
    private double Step;
    private double Angle;
    private double angle;
    private Color[] colors;
    private Stroke stroke;
    /*
    Below method used to generate the colors and the variables in this method
    */
    public RayOfSunPanel(int numberOfLines){
        this.setBackground(BG_COLOR);
        this.setForeground(FG_COLOR);
        this.numberOfLines = numberOfLines;
        this.Step = -2.0 * Math.PI / (SPEED * numberOfLines);
        this.Angle = 0.0;
        this.stroke = new BasicStroke(LINE_THICKNESS,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        this.colors = new Color[numberOfLines];

        Random random = new Random();
        for (int i = 0; i < numberOfLines; i++){
            int red = 64 + random.nextInt(192);
            int green = 64 + random.nextInt(192);
            int blue = 64 + random.nextInt(192);
            this.colors[i] = new Color(red, green, blue);
        }// for
    }// RayOfSunPanel( int )

    /*
    Below is the method that is defining the graphics of the program.
    Using the different variables given to generate the numbers we want.
    */

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();

        RayOfSun[] line = new RayOfSun[this.numberOfLines];
        double goldenRatio = 2.0 /(Math.sqrt(5.0) + 1);

        for(int i = 0; i < this.numberOfLines; i++) {
            double fraction = ((double) i) / this.numberOfLines;
            this.angle = fraction * 2.0 * Math.PI;
            double x = w/2 + w/2 * Math.cos(angle);
            double y = h/2 + h/2 * Math.sin(angle);
            line[i] = new RayOfSun(goldenRatio * x,
                    goldenRatio * y);
        }//for

        double scaleX = (1.0 - 2.0 * MARGIN) * w / 2;
        double scaleY = (1.0 - 2.0 * MARGIN) * h / 2;

        double deltaX = w / 2;
        double deltaY = h / 2;

        g2D.setColor(FG_COLOR);

        for (int i = 0; i < line.length; i++){
            RayOfSun u = line[i];

            //Used to debug program;
            //System.out.println("scaleX = " + scaleX + "\n" + "scaleY = " + scaleY + "\n" + "deltaX = " + deltaX + "\n" + "deltaY = " + deltaY);

            u = u.scaleTranslate(scaleX, scaleY, deltaX, deltaY);
            RayOfSun v = line[(i + 1) % this.numberOfLines];
            v = v.scaleTranslate(scaleX, scaleY, deltaX, deltaY);

            double x0 = u.getX();
            double y0 = u.getY();
            double x1 = v.getX();
            double y1 = v.getY();

            g2D.setColor(this.colors[i]);

            //Also used to debug program;
            //System.out.println("x0 = " + x0 + "\n" + "y0 = " + y0 + "\n" + "x1 = " + x1 + "\n" + "y1 = " + y1);

            Line2D lineOne = new Line2D.Double(w/2, h/2, line[i].getX(), line[i].getY());
            Line2D lineTwo = new Line2D.Double(w/2 + 20, h/2 + 20, line[i].getX(), line[i].getY());
            //g2D.setStroke(this.stroke);
            g2D.draw(lineOne);
            g2D.draw(lineTwo);
        }// for

        g2D.setStroke(this.stroke);

        for (int i = 0; i < this.numberOfLines; i++){
            RayOfSun u = line[i];
            u = u.scaleTranslate( scaleX, scaleY, deltaX, deltaY);
            double x0 = u.getX();
            double y0 = u.getY();
            for(int j = i + 1; j < this.numberOfLines; j++) {
                RayOfSun v = line[j];
                v = v.scaleTranslate(scaleX, scaleY, deltaX, deltaY);
                double x1 = v.getX();
                double y1 = v.getY();

                int index = Math.abs(i - j);
                g2D.setColor(this.colors[index]);

                Line2D lineTwo = new Line2D.Double(x0, y0, x1, y1);
                g2D.draw(lineTwo);
            }// for
        }// for
    }// paintComponent( Graphics )

    @Override
    public void actionPerformed(ActionEvent e){
        this.repaint();
    }// actionPerformed( ActionEvent )
}// RayOfSunPanel