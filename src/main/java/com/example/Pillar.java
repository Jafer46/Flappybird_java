package com.example;
import java.awt.*;
public class Pillar extends Rectangle{
    int xVelocity = -1;
    Pillar(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    public void setXDirection(int xdirection){
        xVelocity = xdirection;
    }
    public void move(){
        x += xVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }
}
