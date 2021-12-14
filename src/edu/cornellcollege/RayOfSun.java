package edu.cornellcollege;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class RayOfSun {

    private double x;
    private double y;

    public RayOfSun(double x, double y){
        this.x = x;
        this.y = y;
    } // RayOfSun( double, double )

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }

    public String toString(){
        return "(" + this.getX() + ", " + this.getY() + ")";
    } // toString()

    public RayOfSun scale(double xFactor, double yFactor){
        return new RayOfSun(0.0, 0.0);
    } //scale( double )

    public RayOfSun scaleTranslate(double scaleX,
                                   double scaleY, double deltaX, double deltaY){
        RayOfSun u = this.scale(scaleX, scaleY);
        return new RayOfSun(u.x * deltaX, u.y * deltaY);

    } // scaleTranslate(double, double, double, double)

    public static void main(String[] args) {

        Point2D a = new Point2D.Double(3.0, 4.0);
        Line2D b = new Line2D.Double(3.0, 4.0, 6.0, 10.0);

    }//main(String[])
}//RayOfSun
