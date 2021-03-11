package com.hou.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author 天涯如浅
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200,200,Dir.LIFT,this);
    Bullet b = new Bullet(250,250,Dir.RIGHT);

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    public TankFrame(){
        setSize(800,600);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               System.exit(0);
            }
        });

    }
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics goffScreen = offScreenImage.getGraphics();
        Color color = goffScreen.getColor();
        goffScreen.setColor(Color.BLACK);
        goffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        goffScreen.setColor(color);
        paint(goffScreen);
        g.drawImage(offScreenImage,0,0,null);

    }

    @Override
    public void paint(Graphics g) {
            myTank.paint(g);
            b.paint(g);

    }

    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
        int key  = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
               bL =true;
            break;
            case KeyEvent.VK_RIGHT :
                bR = true;
            break;
            case KeyEvent.VK_UP :
                bU = true;
                break;
            case KeyEvent.VK_DOWN :
                bD = true;
                break;
            default:
                break;
        }
            setMainTankDire();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key  = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT :
                    bR = false;
                    break;
                case KeyEvent.VK_UP :
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN :
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
           setMainTankDire();
        }

        public void setMainTankDire() {
            if(!bL && !bR && !bD && !bU){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
                if(bL) {
                    myTank.setDir(Dir.LIFT);
                }
                if(bR) {
                    myTank.setDir(Dir.RIGHT);
                }
                if(bU) {
                    myTank.setDir(Dir.UP);
                }
                if(bD) {
                    myTank.setDir(Dir.DOWN);
                }
            }
        }

    }
}
