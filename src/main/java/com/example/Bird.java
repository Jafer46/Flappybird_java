package com.example;
import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
public class Bird extends Rectangle{
    int yVelocity = 1;
    int xVelocity;

    Bird(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        Image image1 = new ImageIcon("src/main/java/com/example/hello.png").getImage();
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(image1,x, y,null);
        g2d.drawImage(image1, x, y, width, height, null, null);
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W){
            int n = -2;
            setYDirection(n);
        }

    }
    public void KeyReleased(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W){
            int n = 3;
            setYDirection(n);
        }
    }
}
