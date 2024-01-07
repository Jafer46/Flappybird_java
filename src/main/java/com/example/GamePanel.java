package com.example;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH ,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PILLAR_WIDTH = 40;    
    Thread gameThread;
    Image image;
    Random random;
    Graphics graphics;
    Bird bird;
    Score score; 
    Pillar pillarTop[] = new Pillar[5];
    Pillar pillarBottom[] = new Pillar[5];
    boolean run = true;
    Btn btn;
    GamePanel(){ 
        for(int i=0; i<pillarTop.length; i++){
            pillarTop[i] = new Pillar(0, 0, 0, 0);
        }  
        for(int i=0; i<pillarBottom.length; i++){
            pillarBottom[i] = new Pillar(0, 0, 0, 0);
        }      
        newPillar(0);
        newBird();
        btn = new Btn();
        score = new Score(GAME_WIDTH ,GAME_HEIGHT); 
        this.setFocusable(true);
        this.addKeyListener(new AL()); 
        this.setPreferredSize(SCREEN_SIZE);   
        this.add(btn);
        btn.setVisible(false);
        gameThread = new Thread(this);
        gameThread.start();        

    }
    public void newPillar(int n){
        random = new Random();
        int x = random.nextInt(5);
        int PILLAR_HEIGHT = 100;
        if(x==0)
           x=1;
        PILLAR_HEIGHT = PILLAR_HEIGHT*x;
        Pillar pil = new Pillar(GAME_WIDTH, 0, PILLAR_WIDTH, PILLAR_HEIGHT);
        pillarTop[n] = pil;
        Pillar pil1 = new Pillar(GAME_WIDTH, (PILLAR_HEIGHT + 100), PILLAR_WIDTH, (GAME_HEIGHT-PILLAR_HEIGHT)-100);
        pillarBottom[n] = pil1;
    }

    public void newBird(){
        bird = new Bird(((GAME_WIDTH-400)/2 - BALL_DIAMETER/2), (GAME_HEIGHT/2 - BALL_DIAMETER/2), BALL_DIAMETER*2, BALL_DIAMETER*2);
    }

    public void draw(Graphics g){        
        bird.draw(g);
        for(int i=0; i<pillarTop.length; i++){
            pillarTop[i].draw(g);
            pillarBottom[i].draw(g);
        }
        
    }

    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void move(){
        bird.move();
        for(int i=0; i<pillarTop.length; i++){
            pillarTop[i].move();
            pillarBottom[i].move();
        }
        
    }
    public void generatePillars(){
        int difference = 208;
        if(pillarTop[0].x == (GAME_WIDTH - difference))
            newPillar(1);
        if(pillarTop[0].x == (GAME_WIDTH - difference*2))
            newPillar(2);
        if(pillarTop[0].x == (GAME_WIDTH - difference*3))
            newPillar(3);
        if(pillarTop[0].x == (GAME_WIDTH - difference*4))
            newPillar(4);
        if(pillarTop[0].x == (GAME_WIDTH - difference*5))
            newPillar(0);
    }
    public void checkCollision(){
        for(int i=0; i<pillarTop.length; i++){
            if(bird.intersects(pillarTop[i])||bird.intersects(pillarBottom[i])){
                this.run = false;
                this.btn.setVisible(true);
                break;
            }
            if(bird.y<0||bird.y+bird.height>GAME_HEIGHT){
                this.run = false;
                break;
            }
                
        }
       
           
    }

    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(run){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                generatePillars();                
                checkCollision();
                repaint();
                delta--;
            }
            
        }
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            bird.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            bird.KeyReleased(e);
        }
    }
    public class Btn extends JButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            run = true;
            
        }

    }
}
