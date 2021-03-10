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

    int x = 200;int y  = 200;
    Dir dir = Dir.LIFT;
    private static final int SPEED = 3;

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

    @Override
    public void paint(Graphics g) {

        g.fillRect(x,y,50,50);

        switch (dir){
            case LIFT:
                x-= SPEED;
                break;
            case RIGHT:
                x+= SPEED;
                break;
            case UP:
                y-= SPEED;
                break;
            case DOWN:
                y+= SPEED;
                break;
        }

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
                default:
                    break;
            }
            setMainTankDire();
        }

        public void setMainTankDire() {
            if(bL) {
                dir =  Dir.LIFT;
            }
            if(bR) {
                dir =  Dir.RIGHT;
            }
            if(bU) {
                dir =  Dir.UP;
            }
            if(bD) {
                dir =  Dir.DOWN;
            }
        }
    }
}
