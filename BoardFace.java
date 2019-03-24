import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.Scanner;

public class BoardFace  extends JPanel implements Runnable, MouseListener
{

    private Dimension d;
    int BOARD_WIDTH=500;
    int BOARD_HEIGHT=500;
    int x = 0;
    int y = 0;
    boolean mousePressed = false;//is true when mouse is pressed
    BufferedImage img;
    private Thread animator;

    public BoardFace()
    {
        addMouseListener(this);
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);

        if (animator == null ) {
            animator = new Thread(this);
            animator.start();
        }

        setDoubleBuffered(true);
    }



    public void paint(Graphics g){
        super.paint(g);
        Color consoleGrey = new Color (207, 202, 198);//main console color
        Color screenGreen = new Color (168, 164, 5);//aka puke green
        Color screenGrey = new Color(103, 104, 122);//for cover around screen
        Color buttonRed = new Color (138, 1, 71);//for action buttons and dim led
        Color buttonBlack = new Color (10, 11, 16);//for d pad
        Color ledRed = new Color(222, 51, 31);//for bright led
        Color textBlue = new Color(39, 43, 88);//for logo/text
        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
        
        //create general gameboy shape
        g.setColor(consoleGrey);
        g.fillRect(100, 15, d.width/2+50, d.height-60);
        //create buttons
        g.setColor(buttonRed);
        g.fillOval(325, 305, 35, 35);
        g.fillOval(275, 345, 35, 35);
        g.setColor(buttonBlack);
        g.setFont (new Font("TimesRoman", Font.PLAIN, 175));
        g.drawString("+",  120,  400);//this draws the dpad
        //still need to do the start and select buttons
        //create screen with panel and led
        int coverWidth = 250;//this is the width of the screen cover 
        g.setColor(screenGrey);
        g.fillRect((d.width/2) - (coverWidth/2), 40, coverWidth, 200);//draws screen cover
        g.setColor(screenGreen);//aka puke green
        int screenWidth = 150;//this is the width of the screen
        g.fillRect((d.width/2) - (screenWidth/2), 65, screenWidth, 150);//draws screen
        g.setColor(buttonRed);//for dim led
        g.fillOval(145, 115, 10, 10);//draws dim led
        g.setColor(Color.black);
        //create gameboy logo
        g.setColor(textBlue);//this is for gameboy logo/text
        g.setFont (new Font("SansSerif", Font.BOLD, 12));//nintendo logo
        g.drawString("DAN JOSHWA  ", (d.width/2) - (coverWidth/2), 270);//draws my name
        g.setFont (new Font("SansSerif", Font.ITALIC + Font.BOLD, 25));
        g.drawString("GAMEBOY", 210, 270);//draws "gameboy logo"
        try {
            img = ImageIO.read(this.getClass().getResource("tetris.png"));//load the tetris img
        } catch (IOException e) {
            System.out.println("Image could not be read");
        }
        if (mousePressed)//if the mouse is pressed
        {
            g.drawImage(img,(d.width/2) - (screenWidth/2), 65, screenWidth, 150, null);//draws the tetris img over the screen
            g.setColor(ledRed);
            g.fillOval(145, 115, 10, 10);//and makes the led "light up"
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        int animationDelay = 50;
        long time = 
            System.currentTimeMillis();
        while (true) {//infinite loop
            // spriteManager.update();
            repaint();
            try {
                time += animationDelay;
                Thread.sleep(Math.max(0,time - 
                        System.currentTimeMillis()));
            }catch (InterruptedException e) {
                System.out.println(e);
            }//end catch
        }//end while loop


    }//end of run

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        mousePressed = true;//if the mouse is pressed this is true

    }
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;//once the mouse is released the bool is now false
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
}//end of class
  