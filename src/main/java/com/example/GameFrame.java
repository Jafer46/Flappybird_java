package com.example;
import java.awt.*;
import javax.swing.*;
public class GameFrame extends JFrame {
    
    GamePanel panel;

    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setResizable(false);
        this.setTitle("Pong Game.");
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    
}
