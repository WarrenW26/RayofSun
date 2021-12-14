package edu.cornellcollege;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.Timer;


public class RayOfSunMethod extends JFrame{
    private static final int FRAME_WIDTH = 512;
    private static final int FRAME_HEIGHT = 512;
    private static final String FRAME_TITLE = "ROS Image";
    private static final int NUMBER_OF_LINES = 50;

    public RayOfSunMethod() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        RayOfSunPanel panel = new RayOfSunPanel(NUMBER_OF_LINES);
        pane.add(panel);

        Timer timer = new Timer(1, panel);
        timer.start();

        this.setVisible(true);
    }// RayOfSun( int, int )
    public static void main(String[] args){
        RayOfSunMethod rayOfSun = new RayOfSunMethod();
    } // main( String[] )
} // Ray of Sun Method